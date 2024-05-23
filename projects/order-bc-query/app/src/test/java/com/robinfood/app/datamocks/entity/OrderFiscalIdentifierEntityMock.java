package com.robinfood.app.datamocks.entity;

import com.robinfood.core.entities.OrderFiscalIdentifierEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderFiscalIdentifierEntityMock {

    public OrderFiscalIdentifierEntity getDataDefault() {

        String localtime = "2022-04-30 00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(localtime, formatter);

        return new OrderFiscalIdentifierEntity(
                dateTime,
                1L,
                "CPF123",
                1L,
                dateTime
        );
    }
}
