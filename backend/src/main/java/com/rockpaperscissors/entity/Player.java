package com.rockpaperscissors.entity;

import com.rockpaperscissors.entity.enums.Choice;

import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

public class Player {
    private String address;
    private boolean commited;
    private boolean revealed;
    private Choice choice;
//    bytes32 commitment;


    public Player(@NotNull String address) {
        this.address = address;
        this.commited = false;
        this.revealed = false;
        this.choice = Choice.NONE;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Player.class.getSimpleName() + "[", "]")
                .add("address='" + address + "'")
                .add("commited=" + commited)
                .add("revealed=" + revealed)
                .add("choice=" + choice)
                .toString();
    }
}
