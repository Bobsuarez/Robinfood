package com.robinfood.core.mappers;

import com.robinfood.core.dtos.FoodCoinsEntityRequestDTO;
import com.robinfood.core.dtos.RedeemFoodCoinsRequestDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductBrandDTO;
import com.robinfood.core.dtos.transactionrequestdto.StoreDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;

import java.math.BigDecimal;

import static com.robinfood.core.constants.GlobalConstants.FOOD_COINS_ENTITY_ORDER;

public final class FoodCoinsMappers {

    private FoodCoinsMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static FoodCoinsEntityRequestDTO toEntityRequestDTO(TransactionRequestDTO transactionRequestDTO) {
        return FoodCoinsEntityRequestDTO.builder()
                .id(FOOD_COINS_ENTITY_ORDER)
                .reference(transactionRequestDTO.getUuid().toString())
                .source(transactionRequestDTO.getId())
                .build();
    }

    public static RedeemFoodCoinsRequestDTO toRedeemRequestDTO(
            BigDecimal amount,
            FinalProductBrandDTO finalProductBrandDTO,
            Integer operationTypeId,
            StoreDTO storeDTO,
            TransactionRequestDTO transactionRequestDTO
    ) {
        return RedeemFoodCoinsRequestDTO.builder()
                .amount(amount)
                .countryId(transactionRequestDTO.getCompany().getId())
                .entity(toEntityRequestDTO(transactionRequestDTO))
                .extra1(finalProductBrandDTO.getId())
                .extra2(finalProductBrandDTO.getName())
                .issuerId(storeDTO.getId())
                .issuerName(storeDTO.getName())
                .operationType(operationTypeId)
                .sourceId(transactionRequestDTO.getId())
                .userId(transactionRequestDTO.getUser().getId())
                .build();
    }
}
