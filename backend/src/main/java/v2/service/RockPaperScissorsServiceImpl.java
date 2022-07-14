package v2.service;

import v2.contracts.RockPaperScissors;
import v2.entity.enums.Stage;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

@Service
public class RockPaperScissorsServiceImpl implements RockPaperScissorsService {

    private final Web3j web3j;
    private final RockPaperScissors rockPaperScissors;

    public RockPaperScissorsServiceImpl(@NotNull Web3j web3j, @NotNull RockPaperScissors rockPaperScissors) {
        this.web3j = web3j;
        this.rockPaperScissors = rockPaperScissors;
    }

    @Override
    public CompletableFuture<TransactionReceipt> changeStage(@NotNull BigInteger roomId, @NotNull Stage stage) {
        return rockPaperScissors.changeStage(roomId, BigInteger.valueOf(stage.ordinal())).sendAsync();
    }

    @Override
    public CompletableFuture<TransactionReceipt> distribute(@NotNull BigInteger roomId) {
        return rockPaperScissors.distribute(roomId).sendAsync();
    }
}
