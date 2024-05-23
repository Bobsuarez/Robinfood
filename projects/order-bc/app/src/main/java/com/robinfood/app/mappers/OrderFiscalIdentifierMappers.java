package com.robinfood.app.mappers;

import com.robinfood.core.dtos.OrderFiscalIdentifierDTO;
import com.robinfood.core.entities.OrderFiscalIdentifierEntity;

public class OrderFiscalIdentifierMappers {

    public static OrderFiscalIdentifierEntity toOrderFiscalIdentifierEntity(
            OrderFiscalIdentifierDTO orderFiscalIdentifierDTO,
            Long transactionId
    ) {
        return new OrderFiscalIdentifierEntity(
                null,
                null,
                orderFiscalIdentifierDTO.getFiscalIdentifier(),
                transactionId,
                null
        );
    }

    public static OrderFiscalIdentifierDTO toOrderFiscalIdentifierDTO(
            OrderFiscalIdentifierEntity orderFiscalIdentifierEntity
    ) {
        OrderFiscalIdentifierDTO orderFiscalIdentifierDTO = new OrderFiscalIdentifierDTO();
        orderFiscalIdentifierDTO.setFiscalIdentifier(orderFiscalIdentifierEntity.getIdentifier());
        return orderFiscalIdentifierDTO;
    }
}
