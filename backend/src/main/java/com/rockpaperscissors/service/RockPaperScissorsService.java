package com.rockpaperscissors.service;

import com.rockpaperscissors.entity.enums.Stage;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

public interface RockPaperScissorsService {

    CompletableFuture<TransactionReceipt> changeStage(@NotNull BigInteger roomId, @NotNull Stage stage);

    CompletableFuture<TransactionReceipt> distribute(@NotNull BigInteger roomId);
}
