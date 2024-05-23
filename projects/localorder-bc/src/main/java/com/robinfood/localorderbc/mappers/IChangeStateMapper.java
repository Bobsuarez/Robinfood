package com.robinfood.localorderbc.mappers;

import com.robinfood.localorderbc.dtos.request.ChangeStateDTO;
import com.robinfood.localorderbc.entities.changestate.ChangeStateEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IChangeStateMapper {

    ChangeStateEntity changeStateDTOChangeStateExecutionEntity(ChangeStateDTO changeStateDTO);

    ChangeStateDTO changeStateDEntityChangeStateExecutionDTO(ChangeStateEntity changeStateEntity);

}
