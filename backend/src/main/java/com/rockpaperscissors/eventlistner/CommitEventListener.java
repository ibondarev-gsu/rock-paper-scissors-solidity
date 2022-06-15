package com.rockpaperscissors.eventlistner;

import com.rockpaperscissors.contracts.RockPaperScissors;
import com.rockpaperscissors.dao.Dao;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.validation.constraints.NotNull;

import java.util.Optional;

import static org.web3j.protocol.core.DefaultBlockParameterName.EARLIEST;
import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;

@Component
public class CommitEventListener {
//    private final RockPaperScissors rockPaperScissors;
//    private final Dao dao;
//
//    private final Scheduler scheduler;
//    private Disposable firstdSubscribeDisposable;
//    private Disposable secondSubscribeDisposable;
//
//    public CommitEventListener(@NotNull RockPaperScissors rockPaperScissors,
//                               @NotNull Dao dao,
//                               @NotNull Scheduler scheduler) {
//        this.rockPaperScissors = rockPaperScissors;
//        this.dao = dao;
//        this.scheduler = scheduler;
//    }

//    @PostConstruct
//    private void postConstruct() {
//        firstdSubscribeDisposable = rockPaperScissors.firstPlayerCommitEventFlowable(EARLIEST, LATEST)
//                .subscribeOn(scheduler)
//                .subscribe(this::handle);
//    }
//
//    @PreDestroy
//    private void preDestroy() {
//        firstdSubscribeDisposable.dispose();
//        secondSubscribeDisposable.dispose();
//    }
//
//    private void handle(RockPaperScissors.SecondPlayerCommitEventResponse secondPlayerCommitEventResponse) {
//        Optional<RockPaperScissors.Room> room = dao.getRoomById(commitEventResponse._id);
//        if (room.isEmpty()) {
//            room = new RockPaperScissors.Room(commitEventResponse._id, )
//        }
//        System.out.println(Thread.currentThread() + " => " + stageChangedEventResponse.log);
//    }
}
