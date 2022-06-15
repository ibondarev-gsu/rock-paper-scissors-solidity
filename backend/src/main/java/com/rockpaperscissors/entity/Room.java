package com.rockpaperscissors.entity;

import com.rockpaperscissors.entity.enums.Stage;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.StringJoiner;

public class Room {
    private BigInteger id;
    private Player firstPlayer;
    private Player secondPlayer;
    private Stage stage;
    private BigInteger timestamp;
    private BigInteger blockNumber;

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
