package com.robinfood.configurationslocalserverbc.dtos.template;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class TransformRulesDTO {

    private LocalDateTime createdAt;

    private String description;

    private Long id;

    private String name;

    private String params;

    private LocalDateTime updatedAt;

}
