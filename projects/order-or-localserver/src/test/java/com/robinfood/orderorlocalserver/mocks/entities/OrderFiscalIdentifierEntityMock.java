package com.robinfood.orderorlocalserver.mocks.entities;

import com.robinfood.orderorlocalserver.entities.orderdetail.OrderFiscalIdentifierEntity;

public class OrderFiscalIdentifierEntityMock {

    public OrderFiscalIdentifierEntity getDataDefault() {

        OrderFiscalIdentifierEntity orderFiscalIdentifierEntity =
                new OrderFiscalIdentifierEntity();

        orderFiscalIdentifierEntity.setIdentifier("CPF123");

        return orderFiscalIdentifierEntity;
    }
}
