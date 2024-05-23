package com.robinfood.configurationslocalserverbc.mocks.dtos;

import com.robinfood.configurationslocalserverbc.dtos.template.TemplateGroupsDTO;

import java.time.LocalDateTime;

public class TemplateGroupsDTOMocks {

    public TemplateGroupsDTO templateGroupsDTODefault = TemplateGroupsDTO
            .builder()
            .id(1L)
            .active(Boolean.TRUE)
            .description("Group Description")
            .createdAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .updatedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .build();
}
