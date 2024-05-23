package com.robinfood.mappers;

import com.robinfood.dtos.sendordertosimba.request.ThirdPartyDTO;
import com.robinfood.entities.OrderThirdPartiesEntity;

public class ThirdPartyRequestDTOToEntityMapper {

    private ThirdPartyRequestDTOToEntityMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static OrderThirdPartiesEntity buildToThirdPartyEntity(
            Long orderId,
            ThirdPartyDTO thirdPartyDTO
    ) {

        return OrderThirdPartiesEntity.builder()
                .document_number(thirdPartyDTO.getDocumentNumber())
                .document_type(thirdPartyDTO.getDocumentType())
                .email(thirdPartyDTO.getEmail())
                .full_name(thirdPartyDTO.getFullName())
                .order_id(orderId)
                .phone(thirdPartyDTO.getPhone())
                .build();

    }
}
