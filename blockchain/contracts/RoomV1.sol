//SPDX-License-Identifier: Unlicense
pragma solidity ^0.8.7;

import "@openzeppelin/contracts/access/AccessControl.sol";

contract RoomV1 is AccessControl {

    bytes32 public constant OWNER_ROLE = keccak256(abi.encodePacked("OWNER_ROLE"));
    bytes32 public constant DISTRIBUTOR_ROLE = keccak256(abi.encodePacked("DISTRIBUTOR_ROLE"));

    address immutable distributor;
    uint public gameCounter;
    Player public player0;
    Player public player1;
    Stage public stage;

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

    struct Player {
        address playerAddress;
        bool commited;
        bool revealed;
        Choice choice;
        bytes32 commitment;
    }

    event Commit(address indexed room, address indexed player);
    event Reveal(address indexed room, address indexed player, Choice choice);
    event Distributed(uint indexed id, Stage stage);
    event StageChanged(address indexed room, Stage stage);
    event GameResult(address indexed room, address winner);

    error PlayerNotExist();
    error WrongStage();
    error WrongChoice();
    error AlreadyCommited();
    error AlreadyRevealed();

    constructor(address playerA, address playerB, address _distributor) {
        _setRoleAdmin(OWNER_ROLE, OWNER_ROLE);
        _setRoleAdmin(DISTRIBUTOR_ROLE, OWNER_ROLE);

        _grantRole(OWNER_ROLE, msg.sender);
        _grantRole(DISTRIBUTOR_ROLE, _distributor);

        distributor = _distributor;
        initizile(playerA, playerB);
    }

    //think about gas optimization
    function commit(bytes32 commitment) public {
        if(player0.playerAddress != msg.sender && player1.playerAddress != msg.sender){
            revert PlayerNotExist();
        }
        if (stage != Stage.Commit) {
            revert WrongStage();
        }
        // Only run during commit stages
        // Player memory player;
        if(player0.playerAddress == msg.sender) {
            setCommitment(player0, commitment);
        } else {
            setCommitment(player1, commitment);
        }

        emit Commit(address(this), msg.sender);
    }

    function reveal(Choice choice, bytes32 key) public {
        if(player0.playerAddress != msg.sender && player1.playerAddress != msg.sender){
            revert PlayerNotExist();
        }
        if (stage != Stage.Reveal) {
            revert WrongStage();
        }
        if(choice != Choice.Rock && choice != Choice.Paper && choice != Choice.Scissors){
            revert WrongChoice();
        }
        if(player0.playerAddress == msg.sender) {
            setReveal(player0.playerAddress, choice, key);
        } else {
            setReveal(player1.playerAddress, choice, key);
        }
        emit Reveal(address(this), msg.sender, choice);
    }

    function distribute() external onlyRole(DISTRIBUTOR_ROLE){
        if(player0.choice == player1.choice) {
            emit GameResult(address(this), address(0));
        } 
        else if(player0.choice == Choice.Rock) {            
            assert(player1.choice == Choice.Paper || player1.choice == Choice.Scissors);
            if(player1.choice == Choice.Paper) {
                // Rock loses to paper
                emit GameResult(address(this), player1.playerAddress);
            }
            else if(player1.choice == Choice.Scissors) {
                // Rock beats scissors
                emit GameResult(address(this), player0.playerAddress);
            }
        }
        else if(player0.choice == Choice.Scissors) {
            assert(player1.choice == Choice.Paper || player1.choice == Choice.Rock);
            if(player1.choice == Choice.Rock) {
                // Scissors lose to rock
                emit GameResult(address(this), player1.playerAddress);
            }
            else if(player1.choice == Choice.Paper) {
                // Scissors beats paper
                emit GameResult(address(this), player0.playerAddress);
            }
        }
        else if(player0.choice == Choice.Paper) {
            assert(player1.choice == Choice.Rock || player1.choice == Choice.Scissors);
            if(player1.choice == Choice.Scissors) {
                // Paper loses to scissors
                emit GameResult(address(this), player1.playerAddress);
            }
            else if(player1.choice == Choice.Rock) {
                // Paper beats rock
                emit GameResult(address(this), player0.playerAddress);
            }
        } else revert("Choice inccorect!");

        initilize(player0.playerAddress, player1.playerAddress);
        gameCounter++;
    }

    function initilize(address playerA, address playerB) public onlyRole(DISTRIBUTOR_ROLE) {
        player0 = Player(playerA, false, false, Choice.None, bytes32(0));
        player1 = Player(playerB, false, false, Choice.None, bytes32(0));
    }

    function changeStage(Stage _stage) external onlyRole(DISTRIBUTOR_ROLE) {
        if(stage == _stage) {
            revert WrongStage();
        }
        stage = _stage;
        emit StageChanged(address(this), stage);
    }

    function setCommitment(Player storage player, bytes32 commitment) private {
        if(player.commited){
            revert AlreadyCommited();
        }
        player.commitment = commitment;
        player.commited = true;
    }

    function setReveal(Player storage player, Choice choice, bytes32 key) private {
        if(player.revealed){
            revert AlreadyCommited();
        }
        require(
            keccak256(abi.encode(_player.playerAddress, _choice, _key)) == _player.commitment, 
            "Commitment: invalid hash!"
        );
        _player.revealed = true;
        return _player;
    }
}