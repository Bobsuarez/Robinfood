package com.robinfood.orderorlocalserver.mappers.printingtemplate;

import com.robinfood.orderorlocalserver.dtos.printingtemplate.PrintingTemplateDTO;
import com.robinfood.orderorlocalserver.entities.printingtemplate.PrintingTemplateEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IPrintingTemplateMapper {

    PrintingTemplateDTO entityToDto(
            PrintingTemplateEntity entity
    );

}
