package com.robinfood.localorderbc.usecases.orderexecutioncommandusecase;

import com.robinfood.localorderbc.dtos.OrderCommandExecutionDTO;
import com.robinfood.localorderbc.dtos.request.OrderCommandExecutionRequestDTO;
import com.robinfood.localorderbc.entities.OrderCommandExecutionEntity;
import com.robinfood.localorderbc.mappers.IOrderCommandExecutionMapper;
import com.robinfood.localorderbc.repositories.IOrderCommandExecutionRepository;
import com.robinfood.localorderbc.usecases.orderexecutioncommandtracelogsusecase.ICommandExecutionTraceLogsUseCase;
import com.robinfood.localorderbc.usecases.orderupdateusecase.IOrderUpdateUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.robinfood.localorderbc.configs.constants.APIConstants.DEFAULT_INTEGER_ONE;
import static com.robinfood.localorderbc.configs.constants.APIConstants.PRINT_INVOICE_TICKET_V1;
import static com.robinfood.localorderbc.configs.constants.APIConstants.REPRINT_INVOICE_TICKET_V1;

@Slf4j
@Service
public class CommandExecutionUseCase implements ICommandExecutionUseCase {

    private final IOrderCommandExecutionMapper orderCommandExecutionMapper;
    private final IOrderCommandExecutionRepository orderCommandExecutionRepository;
    private final ICommandExecutionTraceLogsUseCase commandExecutionTraceLogsUseCase;
    private final IOrderUpdateUseCase orderUpdateUseCase;

    public CommandExecutionUseCase(
            IOrderCommandExecutionMapper orderCommandExecutionMapper,
            IOrderCommandExecutionRepository orderCommandExecutionRepository,
            ICommandExecutionTraceLogsUseCase commandExecutionTraceLogsUseCase,
            IOrderUpdateUseCase orderUpdateUseCase
    ) {

        this.orderCommandExecutionMapper = orderCommandExecutionMapper;
        this.orderCommandExecutionRepository = orderCommandExecutionRepository;
        this.commandExecutionTraceLogsUseCase = commandExecutionTraceLogsUseCase;
        this.orderUpdateUseCase = orderUpdateUseCase;
    }

    @Override
    public OrderCommandExecutionDTO invoke(
            OrderCommandExecutionRequestDTO orderCommandExecutionRequestDTO
    ) {

        log.info("Execute order execution command use case with request {} ", orderCommandExecutionRequestDTO);

        OrderCommandExecutionEntity orderCommandExecutionEntity = orderCommandExecutionMapper
                .commandDTOToCommandExecutionEntity(orderCommandExecutionRequestDTO);

        Integer numberOfRetries = orderCommandExecutionRepository
                .findByOrderIdAndCommandId(orderCommandExecutionRequestDTO.getOrderId(),
                        orderCommandExecutionRequestDTO.getCommandId());

        if (numberOfRetries != null) {
            orderCommandExecutionEntity.setReprocessAttempt(numberOfRetries + DEFAULT_INTEGER_ONE);
            numberOfRetries++;
        }

        OrderCommandExecutionEntity orderCommandExecutionResultEntity = orderCommandExecutionRepository
                .save(orderCommandExecutionEntity);

        log.info("Execute order execution command use case with result {} ", orderCommandExecutionResultEntity);

        commandExecutionTraceLogsUseCase.invoke(orderCommandExecutionRequestDTO, numberOfRetries);

        Set<Long> allowedCommandIds = Set.of(PRINT_INVOICE_TICKET_V1, REPRINT_INVOICE_TICKET_V1);
        if (allowedCommandIds.contains(orderCommandExecutionRequestDTO.getCommandId())) {
            orderUpdateUseCase.invoke(
                    orderCommandExecutionRequestDTO.getOrderId(),
                    orderCommandExecutionRequestDTO.getRequest()
            );
        }

        return orderCommandExecutionMapper.commandEntityToCommandExecutionDTO(orderCommandExecutionResultEntity);

    }
}
