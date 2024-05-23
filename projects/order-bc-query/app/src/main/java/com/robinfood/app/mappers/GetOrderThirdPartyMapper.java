package com.robinfood.app.mappers;

import com.robinfood.core.dtos.OrderThirdPartyDTO;
import com.robinfood.core.entities.OrderThirdPartyEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetOrderThirdPartyMapper {

    public static OrderThirdPartyDTO orderThirdPartyEntityToOrderThirdPartyDTO(
            OrderThirdPartyEntity orderThirdPartyEntity){

        return OrderThirdPartyDTO.builder()
                .documentNumber(orderThirdPartyEntity.getDocumentNumber())
                .documentType(orderThirdPartyEntity.getDocumentType())
                .email(orderThirdPartyEntity.getEmail())
                .fullName(orderThirdPartyEntity.getFullName())
                .phone(orderThirdPartyEntity.getPhone()).build();
    }
}
