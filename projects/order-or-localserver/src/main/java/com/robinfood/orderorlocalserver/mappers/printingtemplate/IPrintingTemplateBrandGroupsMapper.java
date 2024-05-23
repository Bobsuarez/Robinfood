package com.robinfood.orderorlocalserver.mappers.printingtemplate;

import com.robinfood.orderorlocalserver.dtos.printingtemplate.PrintingTemplateBrandGroupsDTO;
import com.robinfood.orderorlocalserver.entities.printingtemplate.PrintingTemplateBrandGroupsEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IPrintingTemplateBrandGroupsMapper {

    List<PrintingTemplateBrandGroupsDTO> entitiesToDtos(
            List<PrintingTemplateBrandGroupsEntity> entities
    );

}
