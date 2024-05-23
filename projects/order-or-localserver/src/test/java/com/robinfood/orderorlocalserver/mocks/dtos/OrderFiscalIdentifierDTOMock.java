package com.robinfood.orderorlocalserver.mocks.dtos;

import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderFiscalIdentifierDTO;

public class OrderFiscalIdentifierDTOMock {

    public OrderFiscalIdentifierDTO getDataDefault() {
        return new OrderFiscalIdentifierDTO(
                "CPF123"
        );
    }
}
