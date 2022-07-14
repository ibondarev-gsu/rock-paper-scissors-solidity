//SPDX-License-Identifier: Unlicense
pragma solidity ^0.8.7;

import "./RoomV1.sol";

contract RoomFactoryV1 {

    mapping(address => mapping(address => address)) public getRoom;
    address[] public allRooms;
    address immutable bot;  

    constructor(address _bot) {
        bot = _bot;
    }

    event RoomCreated(address indexed player0, address indexed player1, address room);

    function createRoom(address playerA, address playerB) external returns (address room) {
        require(playerA != playerB);
        (address player0, address player1) = playerA < playerB ? (playerA, playerB) : (playerB, playerA);
        require(player0 != address(0));
        require(getRoom[player0][player1] == address(0));
        room = address(new RoomV1{salt: keccak256(abi.encode(player0, player1, bot))}(player0, player1, bot));
        getRoom[player0][player1] = room;
        getRoom[player1][player0] = room;
        emit RoomCreated(player0, player1, room);
    }
}