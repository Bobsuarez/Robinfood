package com.robinfood.app.usecases.redeemfoodcoins;

import com.robinfood.app.usecases.redeemfoodcoinslogic.IRedeemFoodCoinsLogicUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationResult;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

import static com.robinfood.core.constants.GlobalConstants.FOOD_COINS_OPERATION_TYPE_REDEEM;
import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;

@Service
@Slf4j
public class RedeemFoodCoinsUseCase implements IRedeemFoodCoinsUseCase {

    private final IRedeemFoodCoinsLogicUseCase redeemFoodCoinsLogicUseCase;

    public RedeemFoodCoinsUseCase(
            IRedeemFoodCoinsLogicUseCase redeemFoodCoinsLogicUseCase
    ) {
        this.redeemFoodCoinsLogicUseCase = redeemFoodCoinsLogicUseCase;
    }

    @Override
    public CompletableFuture<TransactionCreationResult> invoke(
            @NotNull String token,
            @NotNull TransactionRequestDTO transactionRequest
    ) {
        log.info("Redeem foodcoins has started with request: {}", objectToJson(transactionRequest));

        if (Boolean.FALSE.equals(transactionRequest.getPaid())) {

            log.info("Not redeem foodcoins has paid: {}", transactionRequest.getPaid());
            return CompletableFuture.completedFuture(null);
        }

        final CompletableFuture<TransactionCreationResult> responseRedeemFoodCoinUseCase = CompletableFuture
                .completedFuture(redeemFoodCoinsLogicUseCase.invoke(
                        token,
                        transactionRequest,
                        FOOD_COINS_OPERATION_TYPE_REDEEM
                        )
                );

        return CompletableFuture.completedFuture(
                responseRedeemFoodCoinUseCase.join()
        );
    }
}
