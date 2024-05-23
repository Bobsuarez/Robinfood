package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.OrderFiscalIdentifierDTO;

public class OrderFiscalIdentifierDTODataMock {

    public OrderFiscalIdentifierDTO getDataDefault() {
        final OrderFiscalIdentifierDTO orderFiscalIdentifierDTO = new OrderFiscalIdentifierDTO();
        orderFiscalIdentifierDTO.setFiscalIdentifier("CPL123");
        return orderFiscalIdentifierDTO;
    }
}
