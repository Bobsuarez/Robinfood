package com.robinfood.localprinterbc.entities;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PrintingTemplateEntity {

    private List<TemplateEntity> templates;

    private List<PrintingTemplateBrandGroupsEntity> printingTemplateBrands;

    public PrintingTemplateEntity(List<TemplateEntity> templates, List<PrintingTemplateBrandGroupsEntity> printingTemplateBrands) {
        this.templates = templates;
        this.printingTemplateBrands = printingTemplateBrands;
    }
}
