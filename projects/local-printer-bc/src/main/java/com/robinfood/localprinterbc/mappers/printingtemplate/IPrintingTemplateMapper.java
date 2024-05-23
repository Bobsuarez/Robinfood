package com.robinfood.localprinterbc.mappers.printingtemplate;

import com.robinfood.localprinterbc.dtos.printingtemplate.PrintingTemplateDTO;
import com.robinfood.localprinterbc.entities.PrintingTemplateEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IPrintingTemplateMapper {

    PrintingTemplateDTO entityToDto(
            PrintingTemplateEntity entity
    );

}
