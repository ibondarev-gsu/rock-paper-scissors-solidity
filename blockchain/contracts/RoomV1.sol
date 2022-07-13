//SPDX-License-Identifier: Unlicense
pragma solidity ^0.8.7;

contract RoomV1 {
    address immutable bot;

    constructor(address _bot) {
        bot = _bot;
    }
}