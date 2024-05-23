package com.robinfood.configurationslocalserverbc.mocks.entities;

import com.robinfood.configurationslocalserverbc.entities.PrintingTemplateTypesEntity;

import java.time.LocalDateTime;

public class PrintingTemplateTypesEntityMock {

    public PrintingTemplateTypesEntity printingTemplateTypesEntityDefault = PrintingTemplateTypesEntity
            .builder()
            .id(1L)
            .name("Ticket Kitchen")
            .slug("ticket_kitchen")
            .isPartial(Boolean.FALSE)
            .createdAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .updatedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .build();
}
