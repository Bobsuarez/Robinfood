package com.robinfood.orderorlocalserver.mappers;

import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderFiscalIdentifierDTO;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderFiscalIdentifierEntity;

import java.util.Objects;

public final class OrderFiscalIdentifierMappers {

    private OrderFiscalIdentifierMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static OrderFiscalIdentifierDTO toOrderFiscalIdentifierDTOMappers(
            OrderFiscalIdentifierEntity orderFiscalIdentifierEntity
    ) {
        if (Objects.isNull(orderFiscalIdentifierEntity)) {
            return null;
        }

        return new OrderFiscalIdentifierDTO(
                orderFiscalIdentifierEntity.getIdentifier()
        );
    }
}
