package com.robinfood.configurationslocalserverbc.mocks.entities;

import com.robinfood.configurationslocalserverbc.entities.TemplateGroupsStoresEntity;

import java.time.LocalDateTime;

public class TemplateGroupsStoresEntityMock {

    public TemplateGroupsStoresEntity templateGroupsStoresEntityDefault = TemplateGroupsStoresEntity
            .builder()
            .active(Boolean.TRUE)
            .groupId(1L)
            .storeId(1L)
            .groupId(1L)
            .createdAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .updatedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .build();
}
