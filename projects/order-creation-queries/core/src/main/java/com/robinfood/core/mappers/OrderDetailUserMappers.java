package com.robinfood.core.mappers;

import com.robinfood.core.dtos.OrderDetailUserDTO;
import com.robinfood.core.entities.OrderDetailUserEntity;
import java.util.Objects;

public final class OrderDetailUserMappers {

    private OrderDetailUserMappers() {
        // this constructor is empty because it is a mapper class
    }

    public static OrderDetailUserDTO toOrderDetailUserMappers(
            OrderDetailUserEntity orderDetailUserEntity
    ) {
        if (Objects.isNull(orderDetailUserEntity)) {
            return null;
        }

        return OrderDetailUserDTO.builder()
                .email(orderDetailUserEntity.getEmail())
                .firstName(orderDetailUserEntity.getFirstName())
                .id(orderDetailUserEntity.getId())
                .lastName(orderDetailUserEntity.getLastName())
                .mobile(orderDetailUserEntity.getMobile())
                .build();
    }
}
