package com.robinfood.configurationslocalserverbc.mocks.dtos;

import com.robinfood.configurationslocalserverbc.dtos.template.TemplateGroupsStoresDTO;

import java.time.LocalDateTime;

public class TemplateGroupsStoresDTOMock {

    public TemplateGroupsStoresDTO templateGroupsStoresEntityDefault = TemplateGroupsStoresDTO
            .builder()
            .active(Boolean.TRUE)
            .groupId(1L)
            .storeId(1L)
            .groupId(1L)
            .createdAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .updatedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .build();
}
