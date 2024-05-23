package com.robinfood.app.mappers.orderstatushistory;

import com.robinfood.core.dtos.orderstatushistory.OrderUserDataDTO;
import com.robinfood.core.entities.OrderUserDataEntity;

public class OrderUserDataMapper {

    public static OrderUserDataDTO orderUserDataEntityToDTO(OrderUserDataEntity orderUserDataEntity) {
        
        return OrderUserDataDTO.builder()
                .firstName(orderUserDataEntity.getFirstName())
                .lastName(orderUserDataEntity.getLastName())
                .build();
    }
}
