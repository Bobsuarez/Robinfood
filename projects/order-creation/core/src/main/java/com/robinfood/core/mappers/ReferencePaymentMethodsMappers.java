package com.robinfood.core.mappers;

import com.robinfood.core.dtos.paymentmethodpaiddto.ReferencePaymentMethodsDTO;
import com.robinfood.core.entities.paymentmethodpaidentities.ReferencePaymentMethodsEntity;

public final class ReferencePaymentMethodsMappers {

    private ReferencePaymentMethodsMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static ReferencePaymentMethodsDTO buildReferencePaymentMethodsDTO(Long id) {

        return ReferencePaymentMethodsDTO.builder()
                .id(id)
                .build();
    }

    public static ReferencePaymentMethodsEntity referencePaymentMethodsDTOToEntity(
            ReferencePaymentMethodsDTO referencePaymentMethodsDTO
    ) {

        return ReferencePaymentMethodsEntity.builder()
               .id(referencePaymentMethodsDTO.getId())
               .build();
    }
}
