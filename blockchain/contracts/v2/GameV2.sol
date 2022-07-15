//SPDX-License-Identifier: Unlicense
pragma solidity ^0.8.7;

import "@openzeppelin/contracts/access/AccessControl.sol";
import "./interfaces/IGameV2.sol";

contract GameV2 is IGameV2, AccessControl {
    bytes32 public constant OWNER_ROLE = keccak256(abi.encodePacked("OWNER_ROLE"));
    bytes32 public constant DISTRIBUTOR_ROLE = keccak256(abi.encodePacked("DISTRIBUTOR_ROLE"));

    mapping(address => mapping(address => uint256)) public getRoomByPlayerAddresses;
    mapping(uint256 => Room) public getRoomById;
    uint256 private roomCounter;

    address immutable public distributor;  

    constructor(address _distributor) {
        _setRoleAdmin(OWNER_ROLE, OWNER_ROLE);
        _setRoleAdmin(DISTRIBUTOR_ROLE, OWNER_ROLE);

        _grantRole(OWNER_ROLE, msg.sender);
        _grantRole(DISTRIBUTOR_ROLE, _distributor);

        distributor = _distributor;
    }

    function createRoom(address playerA, address playerB) external override returns (uint256){
        require(playerA != playerB);
        (address player0Address, address player1Address) = playerA < playerB ? (playerA, playerB) : (playerB, playerA);
        require(player0Address != address(0));
        require(getRoomByPlayerAddresses[player0Address][player1Address] == 0);

        Player memory player0 = Player(player0Address, false, false, Choice.None, bytes32(0));
        Player memory player1 = Player(player1Address, false, false, Choice.None, bytes32(0));

        getRoomById[roomCounter] = Room(roomCounter, player0, player1, Stage.Commit);
        getRoomByPlayerAddresses[player0Address][player1Address] = roomCounter;
        getRoomByPlayerAddresses[player1Address][player0Address] = roomCounter;
        emit RoomCreated(player0Address, player1Address, roomCounter);
        return roomCounter++;
    }
}