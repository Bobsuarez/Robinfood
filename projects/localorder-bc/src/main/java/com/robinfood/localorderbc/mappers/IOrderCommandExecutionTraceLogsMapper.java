package com.robinfood.localorderbc.mappers;

import com.robinfood.localorderbc.dtos.request.OrderCommandExecutionRequestDTO;
import com.robinfood.localorderbc.entities.OrderCommandExecutionTraceLogsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IOrderCommandExecutionTraceLogsMapper {

    @Mappings({
            @Mapping(target = "reprocessAttempt", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "updatedAt", ignore = true)
    })
    OrderCommandExecutionTraceLogsEntity commandTraceLogsDTOToCommandExecutionTraceLogsEntity(
            OrderCommandExecutionRequestDTO orderCommandExecutionRequestDTO);
}
