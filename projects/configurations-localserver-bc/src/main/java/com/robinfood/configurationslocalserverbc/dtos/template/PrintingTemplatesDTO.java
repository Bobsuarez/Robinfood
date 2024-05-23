package com.robinfood.configurationslocalserverbc.dtos.template;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class PrintingTemplatesDTO {

    private Boolean active;

    private LocalDateTime createdAt;

    private String content;

    private String description;

    private Long id;

    private Long printingTemplateTypeId;

    private LocalDateTime updatedAt;

    private String version;

}
