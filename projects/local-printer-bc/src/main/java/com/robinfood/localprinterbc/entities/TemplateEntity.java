package com.robinfood.localprinterbc.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class TemplateEntity {

    private Long groupId;

    private String groupName;

    private List<TransformRulesEntity> rules;

    private Long templateId;

    private String templateString;

    private PrintingTemplateTypeEntity templateType;

}
