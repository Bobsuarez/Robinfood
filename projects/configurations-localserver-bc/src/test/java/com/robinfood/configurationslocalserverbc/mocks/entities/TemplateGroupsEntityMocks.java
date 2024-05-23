package com.robinfood.configurationslocalserverbc.mocks.entities;

import com.robinfood.configurationslocalserverbc.entities.TemplateGroupsEntity;

import java.time.LocalDateTime;

public class TemplateGroupsEntityMocks {

    public TemplateGroupsEntity templateGroupsEntityDefault = TemplateGroupsEntity
            .builder()
            .id(1L)
            .active(Boolean.TRUE)
            .description("Group Description")
            .createdAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .updatedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .build();
}
