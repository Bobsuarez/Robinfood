package com.robinfood.repository.loyalty;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.RedeemFoodCoinsRequestEntity;
import com.robinfood.core.entities.RedeemFoodCoinsResponseEntity;
import com.robinfood.core.entities.ValidateFoodCoinsRequestEntity;
import com.robinfood.core.entities.ValidateFoodCoinsResponseEntity;
import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;

public interface IRedeemFoodCoinsRemoteDataSource {

    /**
     * Returns the redeem FoodCoins and coverts it into its RedeemFoodCoinsResponseDTO
     *
     * @param token                        the authorization token
     * @param redeemFoodCoinsRequestEntity the info for redeem FoodCoins
     * @return a future containing the converted redeem FoodCoins data
     */
    RedeemFoodCoinsResponseEntity redeemOrRollbackFoodCoins(
            String token,
            RedeemFoodCoinsRequestEntity redeemFoodCoinsRequestEntity
    );

    /**
     * Returns the validation of FoodCoins and coverts it into its ValidateFoodCoinsResponseDTO
     *
     * @param token the authorization token
     * @param validateFoodCoinsRequestEntity the info for validate FoodCoins
     * @return a future containing the converted validate FoodCoins data
     */
    @Async
    CompletableFuture<ApiResponseEntity<ValidateFoodCoinsResponseEntity>> validateFoodCoins(
            String token,
            ValidateFoodCoinsRequestEntity validateFoodCoinsRequestEntity
    );
}
