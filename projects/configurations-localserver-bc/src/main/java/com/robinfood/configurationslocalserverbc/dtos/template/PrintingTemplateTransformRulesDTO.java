package com.robinfood.configurationslocalserverbc.dtos.template;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class PrintingTemplateTransformRulesDTO {

    private LocalDateTime createdAt;

    private Long id;

    private Long printingTemplateId;

    private Long transformRuleId;

    private LocalDateTime updatedAt;

}
