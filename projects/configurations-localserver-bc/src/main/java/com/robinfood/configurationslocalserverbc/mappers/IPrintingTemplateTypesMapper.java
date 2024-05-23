package com.robinfood.configurationslocalserverbc.mappers;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplateTypesDTO;
import com.robinfood.configurationslocalserverbc.entities.PrintingTemplateTypesEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IPrintingTemplateTypesMapper {

    PrintingTemplateTypesDTO printingTemplateTypeEntityToPrintingTemplateTypeDTO(
            PrintingTemplateTypesEntity printingTemplateTypesEntity);

}
