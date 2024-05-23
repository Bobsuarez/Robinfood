package com.robinfood.app.datamocks.entity;

import com.robinfood.core.entities.OrderDetailEntity;

import java.util.Arrays;
import java.util.List;

public class OrderDetailEntityMock {

    public OrderDetailEntity getDataDefault() {
        return new OrderDetailEntity(
                0.0,
                8900.0,
                0.0,
                1L,
                null,
                0.0,
                0.0,
                8900.0,
                true,
                "34343434",
                1L,
                "Anything",
                1L,
                0.0,
                0.0,
                0.0,
                null
        );
    }

    public List<OrderDetailEntity> getDataDefaultList() {
        return Arrays.asList(getDataDefault());
    }

    public List<OrderDetailEntity> getDataDefaultListTwo() {
        
        final OrderDetailEntity orderDetailEntityOne = getDataDefault();
        orderDetailEntityOne.setInvoice("34343434");
        orderDetailEntityOne.setNotes("Notes");

        final OrderDetailEntity orderDetailEntityTwo = getDataDefault();
        orderDetailEntityTwo.setOrderId(2L);
        orderDetailEntityTwo.setInvoice("34343435");
        orderDetailEntityTwo.setNotes("Notes");

        return Arrays.asList(orderDetailEntityOne, orderDetailEntityTwo);
    }
}
