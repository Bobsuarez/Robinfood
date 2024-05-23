package com.robinfood.configurationslocalserverbc.dtos.templateResponse;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplateBrandGroupsDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class PrintingTemplateResponseDTO {

    private List<TemplateDTO> templates;

    private List<PrintingTemplateBrandGroupsDTO> printingTemplateBrands;
}
