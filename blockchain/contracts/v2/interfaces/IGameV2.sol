//SPDX-License-Identifier: Unlicense
pragma solidity ^0.8.7;

interface IGameV2 {

    struct Player {
        address playerAddress;
        bool commited;
        bool revealed;
        Choice choice;
        bytes32 commitment;
    }

    enum Stage {
        Commit,
        Reveal,
        Distribute
    }

    enum Choice {
        None,
        Rock,
        Paper,
        Scissors
    }

    struct Room {
        uint256 id;
        Player firstPlayer;
        Player secondPlayer;
        Stage stage;
    }

    error PlayerNotExist();
    error WrongStage();
    error WrongChoice();
    error AlreadyCommited();
    error AlreadyRevealed();
    error InvalidHash();

    event RoomCreated(address indexed player0, address indexed player1, uint256 roomId);

    function createRoom(address playerA, address playerB) external returns (uint256);
}