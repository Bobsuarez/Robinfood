package com.robinfood.configurationslocalserverbc.mocks.entities;

import com.robinfood.configurationslocalserverbc.entities.PrintingTemplateTransformRulesEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class PrintingTemplateTransformRulesEntityMock {

    public List<PrintingTemplateTransformRulesEntity> printingTemplateTransformRulesEntityDefault = Arrays.asList(
            PrintingTemplateTransformRulesEntity
                    .builder()
                    .printingTemplateId(1L)
                    .transformRuleId(1L)
                    .id(1L)
                    .createdAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .updatedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .build(),
            PrintingTemplateTransformRulesEntity
                    .builder()
                    .printingTemplateId(1L)
                    .transformRuleId(2L)
                    .id(2L)
                    .createdAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .updatedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .build()
    );
}
