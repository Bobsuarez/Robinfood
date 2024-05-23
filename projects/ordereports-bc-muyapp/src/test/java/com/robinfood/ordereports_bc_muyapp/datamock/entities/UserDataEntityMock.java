package com.robinfood.ordereports_bc_muyapp.datamock.entities;

import com.robinfood.ordereports_bc_muyapp.models.entities.OrderUserDataEntity;


public class UserDataEntityMock {

    public static OrderUserDataEntity getDataDefault() {
        return OrderUserDataEntity.builder()
                .id(1L)
                .userId(1)
                .email("email@ordereports.com")
                .orderId(1L)
                .firstName("firstName")
                .lastName("lastName")
                .mobile("mobile")
                .build();
    }
}