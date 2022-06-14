package com.rockpaperscissors;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.web3j.EVMTest;
import org.web3j.NodeType;
import org.web3j.protocol.Web3j;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

//@SpringBootTest
@EVMTest(type = NodeType.GETH)
class RockPaperScissorsApplicationTests {

    @SneakyThrows
    @Test
    void contextLoads(Web3j web3j, TransactionManager transactionManager, ContractGasProvider gasProvider) {
//        RockPaperScissors rockPaperScissors = RockPaperScissors.deploy(web3j, transactionManager, gasProvider).send();
//        System.out.println(transactionManager.getFromAddress());
    }

}
