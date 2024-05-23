package com.robinfood.configurationslocalserverbc.mappers;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplateBrandGroupsDTO;
import com.robinfood.configurationslocalserverbc.entities.PrintingTemplateBrandGroupsEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IPrintingTemplateBrandGroupsMapper {

    List<PrintingTemplateBrandGroupsDTO> printingTemplateBrandGroupsEntityToPrintingTemplateBrandGroupsDTO(
            List<PrintingTemplateBrandGroupsEntity> printingTemplateBrandGroupsEntities
    );
}
