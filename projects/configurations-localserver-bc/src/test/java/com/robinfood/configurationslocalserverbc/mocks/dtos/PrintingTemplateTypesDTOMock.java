package com.robinfood.configurationslocalserverbc.mocks.dtos;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplateTypesDTO;

import java.time.LocalDateTime;

public class PrintingTemplateTypesDTOMock {

    public PrintingTemplateTypesDTO printingTemplateTypesDTOMock = PrintingTemplateTypesDTO
            .builder()
            .id(1L)
            .name("Ticket Kitchen")
            .slug("ticket_kitchen")
            .isPartial(Boolean.FALSE)
            .createdAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .updatedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .build();
}
