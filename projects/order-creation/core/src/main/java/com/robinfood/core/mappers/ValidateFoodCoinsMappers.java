package com.robinfood.core.mappers;

import com.robinfood.core.dtos.ValidateFoodCoinsRequestDTO;
import com.robinfood.core.dtos.ValidateFoodCoinsResponseDTO;
import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.ValidateFoodCoinsRequestEntity;
import com.robinfood.core.entities.ValidateFoodCoinsResponseEntity;

public final class ValidateFoodCoinsMappers {

    private ValidateFoodCoinsMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static ValidateFoodCoinsResponseDTO toValidateFoodCoinsResponseDTO(
            ApiResponseEntity<ValidateFoodCoinsResponseEntity> validateFoodcoinsResponseEntity
    ) {
        return new ValidateFoodCoinsResponseDTO(
                validateFoodcoinsResponseEntity.getMessage(),
                validateFoodcoinsResponseEntity.getData().getTransactionCredits(),
                validateFoodcoinsResponseEntity.getData().isTransactionStatus(),
                validateFoodcoinsResponseEntity.getData().getUserCurrentCredits()
        );
    }

    public static ValidateFoodCoinsRequestEntity toValidateFoodCoinsRequestEntity(
            ValidateFoodCoinsRequestDTO validateFoodCoinsRequestDTO
    ){
        return new ValidateFoodCoinsRequestEntity(
                validateFoodCoinsRequestDTO.getAmount(),
                validateFoodCoinsRequestDTO.getCountryId(),
                validateFoodCoinsRequestDTO.getOperationType(),
                validateFoodCoinsRequestDTO.getUserId()

        );
    }
}
