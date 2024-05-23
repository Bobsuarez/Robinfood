package com.robinfood.core.mappers;

import com.robinfood.core.dtos.OrderFiscalIdentifierDTO;
import com.robinfood.core.entities.OrderFiscalIdentifierEntity;

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
