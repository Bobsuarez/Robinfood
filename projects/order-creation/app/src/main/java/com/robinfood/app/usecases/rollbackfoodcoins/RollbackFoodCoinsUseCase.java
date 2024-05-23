package com.robinfood.app.usecases.rollbackfoodcoins;

import com.robinfood.app.usecases.redeemfoodcoinslogic.IRedeemFoodCoinsLogicUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationResult;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

import static com.robinfood.core.constants.GlobalConstants.FOOD_COINS_OPERATION_TYPE_REDEEM_ROLLBACK;

@Service
@Slf4j
public class RollbackFoodCoinsUseCase implements IRollbackFoodCoinsUseCase {

    private final IRedeemFoodCoinsLogicUseCase redeemFoodCoinsLogicUseCase;

    public RollbackFoodCoinsUseCase(IRedeemFoodCoinsLogicUseCase redeemFoodCoinsLogicUseCase) {
        this.redeemFoodCoinsLogicUseCase = redeemFoodCoinsLogicUseCase;
    }

    @Override
    public CompletableFuture<Void> invoke(
            @NotNull String token,
            @NotNull TransactionRequestDTO transactionRequest
    ) {
        log.info("Foodcoins rollback started");
        final CompletableFuture<TransactionCreationResult> responseRedeemFoodCoinUseCase = CompletableFuture
                .completedFuture(redeemFoodCoinsLogicUseCase.invoke(
                        token,
                        transactionRequest,
                        FOOD_COINS_OPERATION_TYPE_REDEEM_ROLLBACK
                        )
                );

        return CompletableFuture.allOf(responseRedeemFoodCoinUseCase);
    }
}
