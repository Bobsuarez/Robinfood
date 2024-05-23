package com.robinfood.repository.mocks;

import com.robinfood.core.entities.OrderDetailServiceEntity;

import java.util.List;

public class OrderDetailServiceEntityMock {

    public static final List<OrderDetailServiceEntity> getDefaultList(){

        return List.of(OrderDetailServiceEntity.builder()
                        .id(1L)
                        .name("Domicilio MU")
                        .quantity(1)
                        .unitPrice(1000.0)
                .build());
    }
}
