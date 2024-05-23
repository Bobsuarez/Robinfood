package com.robinfood.localorderbc.usecases.orderexecutioncommandusecase;

import com.robinfood.localorderbc.dtos.OrderCommandExecutionDTO;
import com.robinfood.localorderbc.dtos.request.OrderCommandExecutionRequestDTO;
import com.robinfood.localorderbc.entities.OrderCommandExecutionEntity;
import com.robinfood.localorderbc.mappers.IOrderCommandExecutionMapper;
import com.robinfood.localorderbc.mocks.dtos.request.OrderCommandExecutionDTOMock;
import com.robinfood.localorderbc.mocks.dtos.request.OrderCommandExecutionRequestDTOMock;
import com.robinfood.localorderbc.mocks.entities.OrderCommandExecutionEntityMock;
import com.robinfood.localorderbc.repositories.IOrderCommandExecutionRepository;
import com.robinfood.localorderbc.usecases.orderexecutioncommandtracelogsusecase.ICommandExecutionTraceLogsUseCase;
import com.robinfood.localorderbc.usecases.orderupdateusecase.IOrderUpdateUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommandExecutionUseCaseTest {

    @Mock
    private IOrderCommandExecutionMapper orderCommandExecutionMapper;

    @Mock
    private IOrderCommandExecutionRepository orderCommandExecutionRepository;

    @Mock
    private ICommandExecutionTraceLogsUseCase commandExecutionTraceLogsUseCase;

    @Mock
    private IOrderUpdateUseCase orderUpdateUseCase;

    @InjectMocks
    private CommandExecutionUseCase commandExecutionUseCase;

    private Integer numberOfRetries = 1;
    private Integer numberOfRetriesNull = null;
    private Integer numberOfRetriesTraceLogs = 2;

    final OrderCommandExecutionRequestDTO orderCommandExecutionRequestDTOMock =
            new OrderCommandExecutionRequestDTOMock().orderCommandExecutionRequestDTO;

    final OrderCommandExecutionRequestDTO orderCommandExecutionTestWithoutPrintRequestMock =
            new OrderCommandExecutionRequestDTOMock().orderCommandExecutionTestWithoutPrintRequestDTO;

    final OrderCommandExecutionEntity orderCommandExecutionEntity =
            new OrderCommandExecutionEntityMock().orderCommandExecutionEntity;

    final OrderCommandExecutionEntity orderCommandExecutionWithoutEntity =
            new OrderCommandExecutionEntityMock().orderCommandExecutionWithoutPrintEntity;

    final OrderCommandExecutionEntity orderCommandExecutionResultEntity =
            new OrderCommandExecutionEntityMock().OrderCommandExecutionResultEntity;

    final OrderCommandExecutionEntity orderCommandExecutionWithoutPrintResultEntity =
            new OrderCommandExecutionEntityMock().orderCommandExecutionWithoutPrintResultEntity;

    final OrderCommandExecutionDTO orderCommandExecutionResultDTO =
            new OrderCommandExecutionDTOMock().orderCommandExecutionResultDTO;

    final OrderCommandExecutionDTO orderCommandExecutionWithoutPrintResultDTO =
            new OrderCommandExecutionDTOMock().orderCommandExecutionWithoutPrintResultDTO;

    final OrderCommandExecutionDTO orderCommandExecutionResultRetriesNullDTO =
            new OrderCommandExecutionDTOMock().orderCommandExecutionResultRetriesNullDTO;


    @Test
    void Command_Execution_Success() {

        when(orderCommandExecutionMapper.commandDTOToCommandExecutionEntity(
                orderCommandExecutionRequestDTOMock))
                .thenReturn(orderCommandExecutionEntity);

        when(orderCommandExecutionRepository.findByOrderIdAndCommandId(1L, 1L))
                .thenReturn(numberOfRetries);

        when(orderCommandExecutionRepository.save(orderCommandExecutionEntity))
                .thenReturn(orderCommandExecutionResultEntity);

        doNothing().when(commandExecutionTraceLogsUseCase).invoke(orderCommandExecutionRequestDTOMock,
                numberOfRetriesTraceLogs);

        when(orderCommandExecutionMapper.commandEntityToCommandExecutionDTO(
                orderCommandExecutionEntity))
                .thenReturn(orderCommandExecutionResultDTO);

        OrderCommandExecutionDTO orderCommandExecutionDTO = commandExecutionUseCase
                .invoke(orderCommandExecutionRequestDTOMock);

        assertNotNull(orderCommandExecutionDTO);
        assertEquals(orderCommandExecutionDTO.getCommandId(), orderCommandExecutionResultDTO.getCommandId());

    }

    @Test
    void Command_Execution_Without_Print_Success() {

        when(orderCommandExecutionMapper.commandDTOToCommandExecutionEntity(
                orderCommandExecutionTestWithoutPrintRequestMock))
                .thenReturn(orderCommandExecutionWithoutEntity);

        when(orderCommandExecutionRepository.findByOrderIdAndCommandId(1L, 2L))
                .thenReturn(numberOfRetries);

        when(orderCommandExecutionRepository.save(orderCommandExecutionWithoutEntity))
                .thenReturn(orderCommandExecutionWithoutPrintResultEntity);

        doNothing().when(commandExecutionTraceLogsUseCase).invoke(orderCommandExecutionTestWithoutPrintRequestMock,
                numberOfRetriesTraceLogs);

        doNothing().when(orderUpdateUseCase).invoke(1L, orderCommandExecutionTestWithoutPrintRequestMock
                .getRequest());

        when(orderCommandExecutionMapper.commandEntityToCommandExecutionDTO(
                orderCommandExecutionWithoutEntity))
                .thenReturn(orderCommandExecutionWithoutPrintResultDTO);

        OrderCommandExecutionDTO orderCommandExecutionDTO = commandExecutionUseCase
                .invoke(orderCommandExecutionTestWithoutPrintRequestMock);

        assertNotNull(orderCommandExecutionDTO);

    }

    @Test
    void Command_Execution_Null_Success() {

        when(orderCommandExecutionMapper.commandDTOToCommandExecutionEntity(
                orderCommandExecutionRequestDTOMock))
                .thenReturn(orderCommandExecutionEntity);

        when(orderCommandExecutionRepository.findByOrderIdAndCommandId(1L, 1L))
                .thenReturn(numberOfRetriesNull);

        when(orderCommandExecutionRepository.save(orderCommandExecutionEntity))
                .thenReturn(orderCommandExecutionResultEntity);

        doNothing().when(commandExecutionTraceLogsUseCase).invoke(orderCommandExecutionRequestDTOMock,
                numberOfRetriesNull);

        when(orderCommandExecutionMapper.commandEntityToCommandExecutionDTO(
                orderCommandExecutionEntity))
                .thenReturn(orderCommandExecutionResultRetriesNullDTO);

        OrderCommandExecutionDTO orderCommandExecutionDTO = commandExecutionUseCase
                .invoke(orderCommandExecutionRequestDTOMock);

        assertNotNull(orderCommandExecutionDTO);
        assertEquals(orderCommandExecutionDTO.getCommandId(), orderCommandExecutionResultRetriesNullDTO.getCommandId());

    }

}
