package com.rockpaperscissors;

import com.rockpaperscissors.contracts.RockPaperScissors;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;

@SpringBootApplication
public class RockPaperScissorsApplication {

    @SneakyThrows
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(RockPaperScissorsApplication.class, args);

        run.getBean(Web3j.class).ethAccounts().sendAsync().get().getAccounts().forEach(System.out::println);

//        new EthFilter(DefaultBlockParameterName.EARLIEST,
//                DefaultBlockParameterName.LATEST, <contract-address>)
    }

}
