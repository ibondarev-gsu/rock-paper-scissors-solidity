package com.rockpaperscissors;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.request.EthFilter;

import java.util.concurrent.atomic.AtomicInteger;

import static org.web3j.protocol.core.DefaultBlockParameterName.EARLIEST;
import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;

@SpringBootApplication
public class RockPaperScissorsApplication {

    @SneakyThrows
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(RockPaperScissorsApplication.class, args);

        Web3j web3j = run.getBean(Web3j.class);

//        RockPaperScissors rockPaperScissors = RockPaperScissors.load("0x8464135c8f25da09e49bc8782676a84730c318bc", web3j,
//                Credentials.create("0x59c6995e998f97a5a0044966f0945389dc9e86dae88c7a8412f4603b6b78690d"),
//                new DefaultGasProvider());
//
//        System.out.println(rockPaperScissors.owner().send());

        EthFilter filter = new EthFilter(EARLIEST, LATEST, "0xe7f1725E7734CE288F8367e1Bb143E90bb3F0512");

        AtomicInteger atomicInteger = new AtomicInteger();

        web3j.ethLogFlowable(filter).subscribe(log -> {
                    System.out.println(atomicInteger.incrementAndGet() + " " + log);
                }
        );

//        Account #0: 0xf39Fd6e51aad88F6F4ce6aB8827279cffFb92266 (10000 ETH)
//        Private Key: 0xac0974bec39a17e36ba4a6b4d238ff944bacb478cbed5efcae784d7bf4f2ff80

//        web3j.replayPastTransactionsFlowable(EARLIEST, LATEST).subscribe(System.out::println);

//        RockPaperScissors send = RockPaperScissors.deploy(web3j, Credentials.create("0x59c6995e998f97a5a0044966f0945389dc9e86dae88c7a8412f4603b6b78690d"), new DefaultGasProvider()).send();
//        System.out.println(send.getContractAddress());

//        EthFilter filter = new EthFilter(EARLIEST, LATEST, "0xCf7Ed3AccA5a467e9e704C703E8D87F634fB0Fc9");
//        web3j.ethLogFlowable(filter).subscribe(System.out::println);
    }

}
