package com.robinfood.localprinterbc.dtos.printingtemplate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TemplateDTO {

    private Long groupId;

    private String groupName;

    private List<TransformRulesDTO> rules;

    private Long templateId;

    private String templateString;

    private PrintingTemplateTypesDTO templateType;

}
