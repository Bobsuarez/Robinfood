package com.robinfood.repository.loyalty;

import com.robinfood.core.dtos.RedeemFoodCoinsRequestDTO;
import com.robinfood.core.dtos.RedeemFoodCoinsResponseDTO;
import com.robinfood.core.dtos.ValidateFoodCoinsRequestDTO;
import com.robinfood.core.dtos.ValidateFoodCoinsResponseDTO;
import com.robinfood.core.entities.RedeemFoodCoinsRequestEntity;
import com.robinfood.core.entities.RedeemFoodCoinsResponseEntity;
import com.robinfood.core.entities.ValidateFoodCoinsRequestEntity;
import com.robinfood.core.mappers.RedeemFoodCoinMappers;
import com.robinfood.core.mappers.ValidateFoodCoinsMappers;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;

import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;

/**
 * Implementation of IRedeemFoodCoinsRepository
 */
@Slf4j
public class RedeemFoodCoinsRepository implements IRedeemFoodCoinsRepository {

    private final IRedeemFoodCoinsRemoteDataSource redeemFoodCoinDataSource;

    public RedeemFoodCoinsRepository(IRedeemFoodCoinsRemoteDataSource redeemFoodCoinDataSource) {
        this.redeemFoodCoinDataSource = redeemFoodCoinDataSource;
    }

    public RedeemFoodCoinsResponseDTO redeemOrRollbackFoodCoins(
            String token,
            RedeemFoodCoinsRequestDTO redeemFoodCoinsRequestDTO
    ) {
        log.info("Going out to redeem FoodCoins with data: {}", objectToJson(redeemFoodCoinsRequestDTO));

        final RedeemFoodCoinsRequestEntity redeemFoodCoinsRequestEntity = RedeemFoodCoinMappers
                .toRedeemFoodCoinRequestEntity(redeemFoodCoinsRequestDTO);

        final RedeemFoodCoinsResponseEntity redeemFoodCoinsResponseEntity = redeemFoodCoinDataSource
                .redeemOrRollbackFoodCoins(token, redeemFoodCoinsRequestEntity);

        return RedeemFoodCoinMappers.toRedeemFoodCoinResponseDTO(redeemFoodCoinsResponseEntity);
    }

    @Override
    public CompletableFuture<ValidateFoodCoinsResponseDTO> validateFoodCoins(
            String token,
            ValidateFoodCoinsRequestDTO validateFoodCoinsRequestDTO
    ) {
        log.info("Going out to validate FoodCoins with data: {}", objectToJson(validateFoodCoinsRequestDTO));

        final ValidateFoodCoinsRequestEntity validateFoodCoinsRequestEntity =
                ValidateFoodCoinsMappers.toValidateFoodCoinsRequestEntity(validateFoodCoinsRequestDTO);

        return redeemFoodCoinDataSource.validateFoodCoins(
                        token, validateFoodCoinsRequestEntity)
                .thenApply(ValidateFoodCoinsMappers::toValidateFoodCoinsResponseDTO);
    }
}
