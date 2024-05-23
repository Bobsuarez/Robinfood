package com.robinfood.configurationslocalserverbc.mappers;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplatesDTO;
import com.robinfood.configurationslocalserverbc.entities.PrintingTemplatesEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IPrintingTemplatesMapper {

    PrintingTemplatesDTO printingTemplatesEntityToPrintingTemplatesDTO(PrintingTemplatesEntity printingTemplatesEntity);

    List<PrintingTemplatesDTO> listPrintingTemplatesEntityToListPrintingTemplatesDTO(
            List<PrintingTemplatesEntity> printingTemplatesEntities);

}
