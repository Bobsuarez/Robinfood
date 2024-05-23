package com.robinfood.orderorlocalserver.entities.printingtemplate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class PrintingTemplateEntity {

    private List<TemplateEntity> templates;

    private List<PrintingTemplateBrandGroupsEntity> printingTemplateBrands;
}
