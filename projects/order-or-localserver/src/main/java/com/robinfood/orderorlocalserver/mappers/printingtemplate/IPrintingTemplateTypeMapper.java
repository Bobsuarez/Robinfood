package com.robinfood.orderorlocalserver.mappers.printingtemplate;

import com.robinfood.orderorlocalserver.dtos.printingtemplate.PrintingTemplateTypesDTO;
import com.robinfood.orderorlocalserver.entities.printingtemplate.PrintingTemplateTypeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IPrintingTemplateTypeMapper {

    PrintingTemplateTypesDTO entityToDto(
            PrintingTemplateTypeEntity entity
    );
}
