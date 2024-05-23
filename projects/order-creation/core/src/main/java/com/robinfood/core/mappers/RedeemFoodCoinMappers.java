package com.robinfood.core.mappers;

import com.robinfood.core.dtos.FoodCoinsEntityRequestDTO;
import com.robinfood.core.dtos.RedeemFoodCoinsRequestDTO;
import com.robinfood.core.dtos.RedeemFoodCoinsResponseDTO;
import com.robinfood.core.entities.FoodCoinsEntityRequestEntity;
import com.robinfood.core.entities.RedeemFoodCoinsRequestEntity;
import com.robinfood.core.entities.RedeemFoodCoinsResponseEntity;

import static com.robinfood.core.constants.GlobalConstants.FOOD_COINS_ENTITY_ORDER;

public final class RedeemFoodCoinMappers {

    private RedeemFoodCoinMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static FoodCoinsEntityRequestEntity toFoodCoinsEntityRequestEntity(
            FoodCoinsEntityRequestDTO foodCoinsEntityRequestDTO
    ) {
        return FoodCoinsEntityRequestEntity.builder()
                .id(FOOD_COINS_ENTITY_ORDER)
                .reference(foodCoinsEntityRequestDTO.getReference())
                .source(foodCoinsEntityRequestDTO.getSource())
                .build();
    }

    public static RedeemFoodCoinsRequestEntity toRedeemFoodCoinRequestEntity(
            RedeemFoodCoinsRequestDTO redeemFoodCoinsRequestDTO
    ) {
        return RedeemFoodCoinsRequestEntity.builder()
                .amount(redeemFoodCoinsRequestDTO.getAmount())
                .countryId(redeemFoodCoinsRequestDTO.getCountryId())
                .entity(toFoodCoinsEntityRequestEntity(redeemFoodCoinsRequestDTO.getEntity()))
                .extra1(redeemFoodCoinsRequestDTO.getExtra1())
                .extra2(redeemFoodCoinsRequestDTO.getExtra2())
                .issuerId(redeemFoodCoinsRequestDTO.getIssuerId())
                .issuerName(redeemFoodCoinsRequestDTO.getIssuerName())
                .operationType(redeemFoodCoinsRequestDTO.getOperationType())
                .sourceId(redeemFoodCoinsRequestDTO.getSourceId())
                .userId(redeemFoodCoinsRequestDTO.getUserId())
                .build();
    }

    public static RedeemFoodCoinsResponseDTO toRedeemFoodCoinResponseDTO(
            RedeemFoodCoinsResponseEntity redeemFoodCoinsResponseEntity
    ) {
        return RedeemFoodCoinsResponseDTO.builder()
                .amount(redeemFoodCoinsResponseEntity.getAmount())
                .currentCredits(redeemFoodCoinsResponseEntity.getCurrentCredits())
                .uuid(redeemFoodCoinsResponseEntity.getUuid())
                .build();
    }
}
