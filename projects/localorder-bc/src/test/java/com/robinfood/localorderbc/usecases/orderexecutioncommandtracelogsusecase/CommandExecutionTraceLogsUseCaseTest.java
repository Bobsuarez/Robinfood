package com.robinfood.localorderbc.usecases.orderexecutioncommandtracelogsusecase;

import com.robinfood.localorderbc.dtos.request.OrderCommandExecutionRequestDTO;
import com.robinfood.localorderbc.entities.OrderCommandExecutionTraceLogsEntity;
import com.robinfood.localorderbc.mappers.IOrderCommandExecutionTraceLogsMapper;
import com.robinfood.localorderbc.mocks.dtos.request.OrderCommandExecutionRequestDTOMock;
import com.robinfood.localorderbc.mocks.entities.OrderCommandExecutionTraceLogsEntityMock;
import com.robinfood.localorderbc.repositories.IOrderCommandExecutionTraceLogsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommandExecutionTraceLogsUseCaseTest {

    @Mock
    private IOrderCommandExecutionTraceLogsMapper orderCommandExecutionTraceLogsMapper;

    @Mock
    private IOrderCommandExecutionTraceLogsRepository orderCommandExecutionTraceLogsRepository;

    @InjectMocks
    @Spy
    private CommandExecutionTraceLogsUseCase commandExecutionTraceLogsUseCase;

    final OrderCommandExecutionRequestDTO orderCommandExecutionRequestDTOMock =
            new OrderCommandExecutionRequestDTOMock().orderCommandExecutionRequestDTO;

    final OrderCommandExecutionTraceLogsEntity orderCommandExecutionTraceLogsEntity =
            new OrderCommandExecutionTraceLogsEntityMock().orderCommandExecutionTraceLogsEntity;

    final OrderCommandExecutionTraceLogsEntity orderCommandExecutionTraceLogsResultEntity =
            new OrderCommandExecutionTraceLogsEntityMock().orderCommandExecutionTraceLogsResultEntity;

    @Test
    void Command_Execution_Trace_Logs_Success() {

        when(orderCommandExecutionTraceLogsMapper.commandTraceLogsDTOToCommandExecutionTraceLogsEntity(
                orderCommandExecutionRequestDTOMock))
                .thenReturn(orderCommandExecutionTraceLogsEntity);

        when(orderCommandExecutionTraceLogsRepository.save(orderCommandExecutionTraceLogsEntity))
                .thenReturn(orderCommandExecutionTraceLogsResultEntity);

        commandExecutionTraceLogsUseCase.invoke(orderCommandExecutionRequestDTOMock, 1);

        verify(orderCommandExecutionTraceLogsRepository).save(orderCommandExecutionTraceLogsEntity);

    }
}
