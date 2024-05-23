package com.robinfood.configurationslocalserverbc.mocks.entities;

import com.robinfood.configurationslocalserverbc.entities.PrintingTemplatesEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class PrintingTemplatesEntityMock {

    public List<PrintingTemplatesEntity> printingTemplatesEntityDefault = Arrays.asList(
            PrintingTemplatesEntity
                    .builder()
                    .active(Boolean.TRUE)
                    .description("Ticket Kitchen")
                    .content("<template>{}<template>")
                    .id(1L)
                    .printingTemplateTypeId(1L)
                    .createdAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .updatedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .build(),
            PrintingTemplatesEntity
                    .builder()
                    .active(Boolean.TRUE)
                    .description("Ticket Kitchen")
                    .content("<template>{}<template>")
                    .id(2L)
                    .printingTemplateTypeId(1L)
                    .createdAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .updatedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .build()
    );
}
