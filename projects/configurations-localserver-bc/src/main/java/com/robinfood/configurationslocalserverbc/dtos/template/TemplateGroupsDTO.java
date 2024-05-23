package com.robinfood.configurationslocalserverbc.dtos.template;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class TemplateGroupsDTO {

    private Boolean active;

    private LocalDateTime createdAt;

    private String description;

    private Long id;

    private LocalDateTime updatedAt;

}
