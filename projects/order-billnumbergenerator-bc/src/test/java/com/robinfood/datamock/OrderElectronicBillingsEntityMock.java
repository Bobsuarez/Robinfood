package com.robinfood.datamock;

import com.robinfood.entities.OrderElectronicBillingsEntity;

public class OrderElectronicBillingsEntityMock {

    public static OrderElectronicBillingsEntity getDefault() {

        return OrderElectronicBillingsEntity.builder()
                .status_code(500)
                .response_payload("response_payload")
                .request_payload("request_payload")
                .store_name("Tienda order")
                .order_id(1234567L)
                .build();
    }
}
