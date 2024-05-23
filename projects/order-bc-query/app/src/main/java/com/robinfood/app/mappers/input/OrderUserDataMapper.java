package com.robinfood.app.mappers.input;

import com.robinfood.core.dtos.request.transaction.RequestUserDTO;
import com.robinfood.core.entities.OrderUserDataEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderUserDataMapper {

    public static OrderUserDataEntity toOrderUserDataEntity(RequestUserDTO userDTO) {

        return OrderUserDataEntity.builder()
                .email(userDTO.getEmail())
                .firstName(userDTO.getName())
                .lastName(userDTO.getLastName())
                .mobile(userDTO.getMobile())
                .orderId(userDTO.getOrderId())
                .storeId(userDTO.getStoreId())
                .userId(userDTO.getId())
                .build();
    }
}
