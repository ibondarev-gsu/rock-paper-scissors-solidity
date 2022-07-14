package v2.event_listner;

import v2.contracts.RockPaperScissors;
import v2.dao.Dao;
import v2.entity.Room;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.validation.constraints.NotNull;

import static org.web3j.protocol.core.DefaultBlockParameterName.EARLIEST;
import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;

@Slf4j
@Component
public class CreateRoomEventListener {

    private final Dao dao;
    private final RockPaperScissors rockPaperScissors;
    private final Scheduler scheduler;
    private Disposable disposable;

    public CreateRoomEventListener(@NotNull Dao dao,
                                   @NotNull RockPaperScissors rockPaperScissors,
                                   @NotNull Scheduler scheduler) {
        this.dao = dao;
        this.rockPaperScissors = rockPaperScissors;
        this.scheduler = scheduler;
    }

    @PostConstruct
    private void postConstruct() {
        disposable = rockPaperScissors.roomCreatedEventFlowable(EARLIEST, LATEST)// Тут нужно будет с бд брать последний обработанный EARLIEST
                .subscribeOn(scheduler)
                .subscribe(this::handle);
    }

    private void handle(RockPaperScissors.RoomCreatedEventResponse eventResponse) {
        if (dao.getRoomById(eventResponse.id).isPresent()) {
            throw new IllegalArgumentException("Room with id={" + eventResponse.id + "} already exist");
        }
        Room room = new Room(eventResponse.id,
                eventResponse.firstPlayer,
                eventResponse.secondPlayer,
                eventResponse.timestamp,
                eventResponse.blockNumber);
        dao.saveRoom(eventResponse.id, room);
        log.info("Created room = {}", room);
    }

    @PreDestroy
    private void preDestroy() {
        disposable.dispose();
    }
}
