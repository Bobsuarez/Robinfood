package com.robinfood.localorderbc.mappers;

import com.robinfood.localorderbc.dtos.OrderCommandExecutionDTO;
import com.robinfood.localorderbc.dtos.request.OrderCommandExecutionRequestDTO;
import com.robinfood.localorderbc.entities.OrderCommandExecutionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IOrderCommandExecutionMapper {

    @Mappings({
            @Mapping(source = "responseStatusCode", target = "currentResponseStatusCode"),
            @Mapping(target = "reprocessAttempt", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true)
    })
    OrderCommandExecutionEntity commandDTOToCommandExecutionEntity(
            OrderCommandExecutionRequestDTO orderCommandExecutionRequestDTO);

    OrderCommandExecutionDTO commandEntityToCommandExecutionDTO(
            OrderCommandExecutionEntity orderCommandExecutionEntity);

    List<OrderCommandExecutionDTO> commandEntityLisToCommandExecutionDTOList(
            List<OrderCommandExecutionEntity> orderCommandExecutionEntityList
    );

}
