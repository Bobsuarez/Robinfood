package com.robinfood.configurationslocalserverbc.mocks.dtos;

import com.robinfood.configurationslocalserverbc.dtos.template.TransformRulesDTO;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class TransformRulesDTOMock {

    public List<TransformRulesDTO> transformRulesDTOSMocks = Arrays.asList(
            TransformRulesDTO
                    .builder()
                    .id(1L)
                    .description("rule one")
                    .name("suggestedProducts")
                    .params("{}")
                    .createdAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .updatedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .build(),
            TransformRulesDTO
                    .builder()
                    .id(2L)
                    .description("rule two")
                    .name("suggestedProducts")
                    .params("{}")
                    .createdAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .updatedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .build()
    );
}
