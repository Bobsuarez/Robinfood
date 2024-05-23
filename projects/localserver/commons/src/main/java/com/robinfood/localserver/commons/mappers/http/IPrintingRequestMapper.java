package com.robinfood.localserver.commons.mappers.http;

import com.robinfood.localserver.commons.dtos.http.PrintingRequestDTO;
import com.robinfood.localserver.commons.entities.http.PrintingRequestEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IPrintingRequestMapper {

    PrintingRequestEntity printingRequestDtoToPrintingRequestEntity(PrintingRequestDTO printingRequestDTO);

    PrintingRequestDTO printingRequestEntityToPrintingRequestDto(PrintingRequestEntity printingRequestEntity);
}
