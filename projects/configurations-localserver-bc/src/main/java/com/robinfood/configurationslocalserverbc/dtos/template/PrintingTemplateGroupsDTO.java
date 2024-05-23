package com.robinfood.configurationslocalserverbc.dtos.template;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class PrintingTemplateGroupsDTO {

    private Boolean active;

    private LocalDateTime createdAt;

    private Long id;

    private Long groupId;

    private Long printingTemplateId;

    private LocalDateTime updatedAt;

}
