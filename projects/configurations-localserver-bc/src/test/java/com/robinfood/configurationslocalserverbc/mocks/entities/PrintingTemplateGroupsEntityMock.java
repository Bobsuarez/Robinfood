package com.robinfood.configurationslocalserverbc.mocks.entities;

import com.robinfood.configurationslocalserverbc.entities.PrintingTemplateGroupsEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class PrintingTemplateGroupsEntityMock {

    public List<PrintingTemplateGroupsEntity> printingTemplateGroupsEntityMocks = Arrays.asList(
            PrintingTemplateGroupsEntity
                    .builder()
                    .id(1L)
                    .groupId(1L)
                    .active(Boolean.TRUE)
                    .printingTemplateId(1L)
                    .createdAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .updatedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .build(),
            PrintingTemplateGroupsEntity
                    .builder()
                    .id(1L)
                    .groupId(1L)
                    .active(Boolean.TRUE)
                    .printingTemplateId(2L)
                    .createdAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .updatedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .build()
    );
}
