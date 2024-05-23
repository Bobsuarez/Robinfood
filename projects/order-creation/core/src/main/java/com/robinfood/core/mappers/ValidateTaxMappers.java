package com.robinfood.core.mappers;

import static kotlin.collections.CollectionsKt.map;

import com.robinfood.core.dtos.ValidateTaxItemRequestDTO;
import com.robinfood.core.dtos.ValidateTaxItemResponseDTO;
import com.robinfood.core.dtos.ValidateTaxRequestDTO;
import com.robinfood.core.dtos.ValidateTaxResponseDTO;
import com.robinfood.core.entities.ValidateTaxItemRequestEntity;
import com.robinfood.core.entities.ValidateTaxItemResponseEntity;
import com.robinfood.core.entities.ValidateTaxRequestEntity;
import com.robinfood.core.entities.ValidateTaxResponseEntity;
import java.util.List;

public final class ValidateTaxMappers {

    private ValidateTaxMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static List<ValidateTaxResponseDTO> toValidateTaxResponseDTOList(
            List<ValidateTaxResponseEntity> validateTaxResponseEntity) {
        return map(
                validateTaxResponseEntity,
                ValidateTaxMappers::toValidateTaxResponseDTO
        );
    }

    public static ValidateTaxResponseDTO toValidateTaxResponseDTO(
            ValidateTaxResponseEntity validateTaxResponseEntity) {
        return new ValidateTaxResponseDTO(
                validateTaxResponseEntity.getArticleId(),
                validateTaxResponseEntity.getArticleTypeId(),
                validateTaxResponseEntity.getDiscount(),
                validateTaxResponseEntity.getPrice(),
                validateTaxResponseEntity.getQuantity(),
                map(
                        validateTaxResponseEntity.getTaxes(),
                        ValidateTaxMappers::toValidateTaxItemResponseDTO
                ),
                validateTaxResponseEntity.getTotalTax()
        );
    }

    public static ValidateTaxItemResponseDTO toValidateTaxItemResponseDTO(
            ValidateTaxItemResponseEntity validateTaxItemResponseEntity) {
        return new ValidateTaxItemResponseDTO(
            validateTaxItemResponseEntity.getFamilyId(),
            validateTaxItemResponseEntity.getId(),
            validateTaxItemResponseEntity.getRate(),
            validateTaxItemResponseEntity.getSapId(),
            validateTaxItemResponseEntity.getTaxId(),
            validateTaxItemResponseEntity.getTaxTypeId(),
            validateTaxItemResponseEntity.getName(),
            validateTaxItemResponseEntity.getValue()

        );
    }

    public static ValidateTaxRequestEntity toValidateTaxRequestEntity(
            ValidateTaxRequestDTO validateTaxRequestEntity) {
        return new ValidateTaxRequestEntity(
                validateTaxRequestEntity.getCriteria(),
                map(
                        validateTaxRequestEntity.getItems(),
                        ValidateTaxMappers::toValidateTaxItemRequestEntity
                )
        );
    }

    public static ValidateTaxItemRequestEntity toValidateTaxItemRequestEntity(
            ValidateTaxItemRequestDTO validateTaxItemRequestDTO) {
        return new ValidateTaxItemRequestEntity(
                validateTaxItemRequestDTO.getArticleId(),
                validateTaxItemRequestDTO.getArticleTypeId(),
                validateTaxItemRequestDTO.getDiscount(),
                validateTaxItemRequestDTO.getPrice(),
                validateTaxItemRequestDTO.getQuantity()
        );
    }
}
