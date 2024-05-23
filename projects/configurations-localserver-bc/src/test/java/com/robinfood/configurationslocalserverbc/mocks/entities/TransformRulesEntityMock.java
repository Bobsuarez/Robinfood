package com.robinfood.configurationslocalserverbc.mocks.entities;

import com.robinfood.configurationslocalserverbc.entities.TransformRulesEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class TransformRulesEntityMock {

    public List<TransformRulesEntity> transformRulesEntitiesMocks = Arrays.asList(
            TransformRulesEntity
                    .builder()
                    .id(1L)
                    .description("rule one")
                    .name("suggestedProducts")
                    .params("{}")
                    .createdAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .updatedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .build(),
            TransformRulesEntity
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
