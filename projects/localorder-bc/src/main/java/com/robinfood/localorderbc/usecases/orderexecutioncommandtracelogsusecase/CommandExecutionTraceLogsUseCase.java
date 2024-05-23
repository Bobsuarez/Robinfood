package com.robinfood.localorderbc.usecases.orderexecutioncommandtracelogsusecase;

import com.robinfood.localorderbc.dtos.request.OrderCommandExecutionRequestDTO;
import com.robinfood.localorderbc.entities.OrderCommandExecutionTraceLogsEntity;
import com.robinfood.localorderbc.mappers.IOrderCommandExecutionTraceLogsMapper;
import com.robinfood.localorderbc.repositories.IOrderCommandExecutionTraceLogsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CommandExecutionTraceLogsUseCase implements ICommandExecutionTraceLogsUseCase {

    private final IOrderCommandExecutionTraceLogsMapper orderCommandExecutionTraceLogsMapper;
    private final IOrderCommandExecutionTraceLogsRepository orderCommandExecutionTraceLogsRepository;

    public CommandExecutionTraceLogsUseCase(
            IOrderCommandExecutionTraceLogsMapper orderCommandExecutionTraceLogsMapper,
            IOrderCommandExecutionTraceLogsRepository orderCommandExecutionTraceLogsRepository) {

        this.orderCommandExecutionTraceLogsMapper = orderCommandExecutionTraceLogsMapper;
        this.orderCommandExecutionTraceLogsRepository = orderCommandExecutionTraceLogsRepository;
    }

    @Override
    public void invoke(OrderCommandExecutionRequestDTO orderCommandExecutionRequestDTO, Integer numberOfRetries) {

        log.info("Execute order execution command trace logs use case with {} ", orderCommandExecutionRequestDTO);

        OrderCommandExecutionTraceLogsEntity
                orderCommandExecutionTraceLogsEntity = orderCommandExecutionTraceLogsMapper
                .commandTraceLogsDTOToCommandExecutionTraceLogsEntity(
                        orderCommandExecutionRequestDTO
                );

        orderCommandExecutionTraceLogsEntity.setReprocessAttempt(numberOfRetries);

        OrderCommandExecutionTraceLogsEntity orderCommandExecutionTraceLogsResultEntity =
                orderCommandExecutionTraceLogsRepository.save(orderCommandExecutionTraceLogsEntity);

        log.info("Execute order execution command trace logs use case with id {} ",
                orderCommandExecutionTraceLogsResultEntity.getId());

    }

}
