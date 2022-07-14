package v2.event_listner;

import v2.contracts.RockPaperScissors;
import v2.dao.Dao;
import v2.entity.Room;
import v2.entity.enums.Stage;
import v2.service.RockPaperScissorsService;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.validation.constraints.NotNull;

import java.util.Optional;

import static org.web3j.protocol.core.DefaultBlockParameterName.EARLIEST;
import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;

@Slf4j
@Component
public class CommitEventListener {

    private final Dao dao;
    private final RockPaperScissors rockPaperScissors;
    private final Scheduler scheduler;

    private final RockPaperScissorsService rockPaperScissorsService;
    private Disposable disposable;

    public CommitEventListener(@NotNull Dao dao,
                               @NotNull RockPaperScissors rockPaperScissors,
                               @NotNull Scheduler scheduler,
                               @NotNull RockPaperScissorsService rockPaperScissorsService) {
        this.dao = dao;
        this.rockPaperScissors = rockPaperScissors;
        this.scheduler = scheduler;
        this.rockPaperScissorsService = rockPaperScissorsService;
    }

    @PostConstruct
    private void postConstruct() {
        disposable = rockPaperScissors.commitEventFlowable(EARLIEST, LATEST)
                .subscribeOn(scheduler)
                .subscribe(this::handle);
    }

    private void handle(RockPaperScissors.CommitEventResponse eventResponse) {
        Optional<Room> roomOptional = dao.getRoomById(eventResponse.id);
        if (roomOptional.isEmpty()) {
            throw new IllegalArgumentException("Room with id={" + eventResponse.id + "} does not exist");
        }

        Room room = roomOptional.get();
        if (room.getFirstPlayer().getAddress().equals(eventResponse.player)) {
            room.getFirstPlayer().setCommited(true);
            log.info("Set commited={true} for player={}", room.getFirstPlayer().getAddress());
        } else if (room.getSecondPlayer().getAddress().equals(eventResponse.player)) {
            room.getSecondPlayer().setCommited(true);
            log.info("Set commited={true} for player={}", room.getSecondPlayer().getAddress());
        } else {
            throw new IllegalArgumentException("Player with address={" + eventResponse.player + "} does not exist");
        }

        if (room.getFirstPlayer().isCommited() && room.getSecondPlayer().isCommited()) {
            rockPaperScissorsService.changeStage(room.getId(), Stage.REVEAL);
        }
    }

    @PreDestroy
    private void preDestroy() {
        disposable.dispose();
    }
}
