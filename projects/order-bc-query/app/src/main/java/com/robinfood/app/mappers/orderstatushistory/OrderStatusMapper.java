package com.robinfood.app.mappers.orderstatushistory;

import com.robinfood.core.dtos.orderstatushistory.OrderStatusDTO;
import com.robinfood.core.entities.StatusEntity;

public class OrderStatusMapper {

    public static OrderStatusDTO orderStatusEntityToDTO(StatusEntity statusEntity) {

        return OrderStatusDTO.builder()
                .code(statusEntity.getCode())
                .id(statusEntity.getId())
                .build();
    }
}
