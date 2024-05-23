package com.robinfood.localprinterbc.dtos.printingtemplate;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PrintingTemplateDTO {

    private List<TemplateDTO> templates;

    private List<PrintingTemplateBrandGroupsDTO> printingTemplateBrands;

    public PrintingTemplateDTO(List<TemplateDTO> templates, List<PrintingTemplateBrandGroupsDTO> printingTemplateBrands) {
        this.templates = templates;
        this.printingTemplateBrands = printingTemplateBrands;
    }
}
