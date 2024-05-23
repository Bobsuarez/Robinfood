package com.robinfood.datamock;

import com.robinfood.entities.OrderThirdPartiesEntity;

public class OrderThirdPartiesEntityMock {

    public static OrderThirdPartiesEntity getDefault() {

        return OrderThirdPartiesEntity.builder()
                .order_id(123456L)
                .full_name("full_name")
                .email("correo@gmail.com")
                .document_type(1)
                .document_number("Cedula")
                .phone("123456789")
                .build();
    }
}
