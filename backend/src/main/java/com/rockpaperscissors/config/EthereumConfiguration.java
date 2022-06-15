package com.rockpaperscissors.config;

import com.rockpaperscissors.contracts.RockPaperScissors;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static okhttp3.internal.Util.threadFactory;

@Configuration
public class EthereumConfiguration {

    @Value("${eth.host}")
    private String host;
    @Value("${eth.port}")
    private Integer port;
    @Value("${eth.ownerWalletPrivateKey}")
    private String ownerPrivateKey;
    @Value("${eth.appWalletAddress}")
    private String appAddress;

    @Bean
    public Web3j web3j() {
        return Web3j.build(new HttpService(host + ':' + port));
    }

    @Bean
    public RockPaperScissors rockPaperScissors() {
        return RockPaperScissors.load(appAddress, web3j(),
                Credentials.create(ownerPrivateKey),
                new DefaultGasProvider());
    }

    @Bean
    public Scheduler scheduler() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        return Schedulers.from(fixedThreadPool);
    }

}
