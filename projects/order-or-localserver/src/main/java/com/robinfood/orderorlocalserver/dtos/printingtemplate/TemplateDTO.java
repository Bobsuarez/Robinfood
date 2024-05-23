package com.robinfood.orderorlocalserver.dtos.printingtemplate;

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

    private List<TransformRulesDTO> rules;

    private Long templateId;

    private String templateString;

    private PrintingTemplateTypesDTO templateType;

}
