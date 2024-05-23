package com.robinfood.configurationslocalserverbc.mocks.dtos;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplateTransformRulesDTO;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class PrintingTemplateTransformRulesDTOMock {

    public List<PrintingTemplateTransformRulesDTO> printingTemplateTransformRulesDTOSMocks = Arrays.asList(
            PrintingTemplateTransformRulesDTO
                    .builder()
                    .printingTemplateId(1L)
                    .transformRuleId(1L)
                    .id(1L)
                    .createdAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .updatedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .build(),
            PrintingTemplateTransformRulesDTO
                    .builder()
                    .printingTemplateId(1L)
                    .transformRuleId(2L)
                    .id(2L)
                    .createdAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .updatedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .build()
    );
}
