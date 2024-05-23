package com.robinfood.configurationslocalserverbc.mocks.entities;

import com.robinfood.configurationslocalserverbc.entities.PrintingTemplateBrandGroupsEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class PrintingTemplateBrandGroupsEntityMock {
    public List<PrintingTemplateBrandGroupsEntity> printingTemplateBrandGroupsEntitiesDefault = Arrays.asList(
            PrintingTemplateBrandGroupsEntity
                    .builder()
                    .active(Boolean.TRUE)
                    .brandId(1L)
                    .printingTemplateId(1L)
                    .groupId(1L)
                    .id(1L)
                    .createdAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .updatedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .build(),
            PrintingTemplateBrandGroupsEntity
                    .builder()
                    .active(Boolean.TRUE)
                    .brandId(2L)
                    .printingTemplateId(1L)
                    .groupId(1L)
                    .id(1L)
                    .createdAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .updatedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .build()
    );
}
