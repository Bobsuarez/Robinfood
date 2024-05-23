package com.robinfood.configurationslocalserverbc.mappers;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplateTransformRulesDTO;
import com.robinfood.configurationslocalserverbc.entities.PrintingTemplateTransformRulesEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IPrintingTemplateTransformRulesMapper {

    List<PrintingTemplateTransformRulesDTO> lisPrintingTemplateTransformRulesEntityToPrintingTemplateTransformRulesDTO(
            List<PrintingTemplateTransformRulesEntity> printingTemplateTransformRulesEntities);

}
