package com.robinfood.configurationslocalserverbc.dtos.templateResponse;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplateTypesDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class TemplateDTO {

    private Long groupId;

    private String groupName;

    private List<TransformRulesResponseDTO> rules;

    private Long templateId;

    private String templateString;

    private PrintingTemplateTypesDTO templateType;

}
