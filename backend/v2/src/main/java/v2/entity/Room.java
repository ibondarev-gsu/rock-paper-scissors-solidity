package v2.entity;

import v2.entity.enums.Stage;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.StringJoiner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class Room {
    private final BigInteger id;
    private final Player firstPlayer;
    private final Player secondPlayer;
    private Stage stage;
    private final BigInteger timestamp;
    private final BigInteger blockNumber;

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private final Lock readLock = readWriteLock.readLock();

    private final Lock writeLock = readWriteLock.writeLock();

    public Room(@NotNull BigInteger roomId,
                @NotNull String firstPlayerAddress,
                @NotNull String secondPlayerAddress,
                @NotNull BigInteger timestamp,
                @NotNull BigInteger blockNumber) {
        this.id = roomId;
        this.firstPlayer = new Player(firstPlayerAddress);
        this.secondPlayer = new Player(secondPlayerAddress);
        this.stage = Stage.COMMIT;
        this.timestamp = timestamp;
        this.blockNumber = blockNumber;
    }

    public BigInteger getId() {
        return id;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public BigInteger getTimestamp() {
        return timestamp;
    }

    public BigInteger getBlockNumber() {
        return blockNumber;
    }

    public Stage getStage() {
        readLock.lock();
        try {
            return stage;
        } finally {
            readLock.unlock();
        }
    }


    //для атомарности
    public void changeStage(@NotNull Stage newStage) {
        writeLock.lock();
        try {
            if (stage == newStage) {
                throw new IllegalArgumentException("Room with id=" + id
                        + " already at the stage=" + stage);
            }
            this.stage = newStage;
        } finally {
            writeLock.unlock();
        }
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", Room.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("firstPlayer=" + firstPlayer)
                .add("secondPlayer=" + secondPlayer)
                .add("stage=" + stage)
                .add("timestamp=" + timestamp)
                .add("blockNumber=" + blockNumber)
                .toString();
    }
}
