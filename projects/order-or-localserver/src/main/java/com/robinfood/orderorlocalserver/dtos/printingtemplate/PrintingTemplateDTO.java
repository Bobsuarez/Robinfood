package com.robinfood.orderorlocalserver.dtos.printingtemplate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class PrintingTemplateDTO {

    private List<TemplateDTO> templates;

    private List<PrintingTemplateBrandGroupsDTO> printingTemplateBrands;
}
