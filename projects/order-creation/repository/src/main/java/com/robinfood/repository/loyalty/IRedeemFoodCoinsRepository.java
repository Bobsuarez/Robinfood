package com.robinfood.repository.loyalty;

import com.robinfood.core.dtos.RedeemFoodCoinsRequestDTO;
import com.robinfood.core.dtos.RedeemFoodCoinsResponseDTO;
import com.robinfood.core.dtos.ValidateFoodCoinsRequestDTO;
import com.robinfood.core.dtos.ValidateFoodCoinsResponseDTO;
import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;

/**
 * Repository sends redeem FoodCoins data
 */
public interface IRedeemFoodCoinsRepository {

    /**
     * Returns the redeem FoodCoins and coverts it into its RedeemFoodCoinsResponseDTO
     *
     * @param token                     the authorization token
     * @param redeemFoodCoinsRequestDTO the info for redeem FoodCoins
     * @return a future containing the converted redeem FoodCoins data
     */
    RedeemFoodCoinsResponseDTO redeemOrRollbackFoodCoins(
            String token,
            RedeemFoodCoinsRequestDTO redeemFoodCoinsRequestDTO
    );

    /**
     * Returns the validation of FoodCoins and coverts it into its ValidateFoodCoinsResponseDTO
     *
     * @param token the authorization token
     * @param validateFoodCoinsRequestDTO the info for validate FoodCoins
     * @return a future containing the converted validate FoodCoins data
     */
    @Async
    CompletableFuture<ValidateFoodCoinsResponseDTO> validateFoodCoins(
            String token,
            ValidateFoodCoinsRequestDTO validateFoodCoinsRequestDTO
    );
}
