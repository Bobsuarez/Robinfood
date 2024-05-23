package com.robinfood.localserver.commons.mappers.orders;

import com.robinfood.localserver.commons.dtos.http.ChangeStatusRequestDTO;
import com.robinfood.localserver.commons.entities.http.ChangeStatusRequestEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IChangeOrderStatusMapper {

    ChangeStatusRequestDTO changeStatusRequestEntityToChangeStatusRequestDTO(
            ChangeStatusRequestEntity changeStatusRequestEntity);

    ChangeStatusRequestEntity changeStatusRequestDTOToChangeStatusRequestEntity(
            ChangeStatusRequestDTO changeStatusRequestDTO);
}
