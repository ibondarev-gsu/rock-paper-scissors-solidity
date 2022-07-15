//SPDX-License-Identifier: Unlicense
pragma solidity ^0.8.7;

import "@openzeppelin/contracts/access/AccessControl.sol";

contract GameV2 is AccessControl {
    bytes32 public constant OWNER_ROLE = keccak256(abi.encodePacked("OWNER_ROLE"));
    bytes32 public constant DISTRIBUTOR_ROLE = keccak256(abi.encodePacked("DISTRIBUTOR_ROLE"));

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

    event RoomCreated(address indexed player0, address indexed player1, address room);

    constructor(address playerA, address playerB, address _distributor) {
        _setRoleAdmin(OWNER_ROLE, OWNER_ROLE);
        _setRoleAdmin(DISTRIBUTOR_ROLE, OWNER_ROLE);

        _grantRole(OWNER_ROLE, msg.sender);
        _grantRole(DISTRIBUTOR_ROLE, _distributor);

        distributor = _distributor;
        player0 = Player(playerA, false, false, Choice.None, bytes32(0));
        player1 = Player(playerB, false, false, Choice.None, bytes32(0));
    }
}