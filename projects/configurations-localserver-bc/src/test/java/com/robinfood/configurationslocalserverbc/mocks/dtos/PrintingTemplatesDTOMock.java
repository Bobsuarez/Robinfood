package com.robinfood.configurationslocalserverbc.mocks.dtos;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplatesDTO;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class PrintingTemplatesDTOMock {

    public List<PrintingTemplatesDTO> printingTemplatesResponseDTOS = Arrays.asList(
            PrintingTemplatesDTO
                    .builder()
                    .active(Boolean.TRUE)
                    .description("Ticket Kitchen")
                    .content("<template>{}<template>")
                    .id(1L)
                    .printingTemplateTypeId(1L)
                    .createdAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .updatedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .build()
    );
}
