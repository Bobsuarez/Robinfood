package com.robinfood.repository.mocks;

import com.robinfood.core.entities.OrderFiscalIdentifierEntity;
import lombok.Data;

@Data
public class OrderFiscalIdentifierEntityMock {

    public OrderFiscalIdentifierEntity getDefaultData() {

        OrderFiscalIdentifierEntity orderFiscalIdentifierEntity = new OrderFiscalIdentifierEntity();
        orderFiscalIdentifierEntity.setIdentifier("CPF123");
        return orderFiscalIdentifierEntity;
    }
}
