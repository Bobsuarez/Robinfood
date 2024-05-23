package com.robinfood.repository.loyalty;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.RedeemFoodCoinsRequestEntity;
import com.robinfood.core.entities.RedeemFoodCoinsResponseEntity;
import com.robinfood.core.entities.ValidateFoodCoinsRequestEntity;
import com.robinfood.core.entities.ValidateFoodCoinsResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.enums.TransactionCreationErrors;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.network.api.LoyaltyAPI;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of IRedeemFoodCoinsDataSource
 */
public class RedeemFoodCoinsRemoteDataSource implements IRedeemFoodCoinsRemoteDataSource {

    private final LoyaltyAPI loyaltyAPI;

    public RedeemFoodCoinsRemoteDataSource(LoyaltyAPI loyaltyAPI) {
        this.loyaltyAPI = loyaltyAPI;
    }

    public RedeemFoodCoinsResponseEntity redeemOrRollbackFoodCoins(
            String token,
            RedeemFoodCoinsRequestEntity redeemFoodCoinsRequestEntity
    ) {

        final Result<ApiResponseEntity<RedeemFoodCoinsResponseEntity>> redeemFoodCoins = NetworkExtensionsKt
                .safeAPICall(loyaltyAPI.redeemOrRollbackFoodCoins(token, redeemFoodCoinsRequestEntity));

        if (redeemFoodCoins instanceof Result.Error) {

            final Result.Error error = (Result.Error) redeemFoodCoins;

            throw new TransactionCreationException(
                    error.getHttpStatus(),
                    error.getException().getLocalizedMessage(),
                    TransactionCreationErrors.FOODCOINS_REDEEM_OR_ROLLBACK_ERROR
            );
        }

        return ((Result.Success<ApiResponseEntity<RedeemFoodCoinsResponseEntity>>) redeemFoodCoins)
                .getData()
                .getData();
    }

    @Override
    public CompletableFuture<ApiResponseEntity<ValidateFoodCoinsResponseEntity>> validateFoodCoins(
            String token,
            ValidateFoodCoinsRequestEntity validateFoodCoinsRequestEntity
    ) {
        final Result<ApiResponseEntity<ValidateFoodCoinsResponseEntity>> validateFoodCoins = NetworkExtensionsKt
                .safeAPICall(loyaltyAPI.validateFoodcoins(
                        token,
                        validateFoodCoinsRequestEntity.getAmount(),
                        validateFoodCoinsRequestEntity.getCountryId(),
                        validateFoodCoinsRequestEntity.getOperationType(),
                        validateFoodCoinsRequestEntity.getUserId()
                ));
        if (validateFoodCoins instanceof Result.Error) {
            return CompletableFuture.failedFuture(((Result.Error) validateFoodCoins).getException());
        }
        return CompletableFuture.completedFuture(
                ((Result.Success<ApiResponseEntity<ValidateFoodCoinsResponseEntity>>) validateFoodCoins)
                        .getData()
        );
    }
}
