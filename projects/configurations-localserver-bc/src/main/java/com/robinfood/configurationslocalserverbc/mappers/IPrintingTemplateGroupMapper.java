package com.robinfood.configurationslocalserverbc.mappers;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplateGroupsDTO;
import com.robinfood.configurationslocalserverbc.entities.PrintingTemplateGroupsEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IPrintingTemplateGroupMapper {

    PrintingTemplateGroupsDTO printingTemplatesGroupEntityToPrintingTemplatesGroupDTO(
            PrintingTemplateGroupsEntity printingTemplateGroupsEntity);

    List<PrintingTemplateGroupsDTO> listPrintingTemplatesGroupEntityToListPrintingTemplatesGroupDTO(
            List<PrintingTemplateGroupsEntity> printingTemplateGroupsEntities);

}
