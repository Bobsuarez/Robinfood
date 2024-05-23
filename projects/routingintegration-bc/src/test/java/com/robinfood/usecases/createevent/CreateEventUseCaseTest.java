package com.robinfood.usecases.createevent;

import com.amazonaws.services.lambda.runtime.Context;
import com.robinfood.datamock.FlowEventLogsEntityMock;
import com.robinfood.datamock.request.EventRequestDTOMock;
import com.robinfood.dtos.createeventflow.response.FlowEventLogsResponseDTO;
import com.robinfood.repository.floweventlogs.IFlowEventLogsRepository;
import com.robinfood.util.LogsUtil;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigInteger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class CreateEventUseCaseTest {

    @Mock
    private IFlowEventLogsRepository flowEventLogsRepository;

    @Mock
    private CreateEventUseCase createEventUseCase;

    @Mock
    private Context context;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_Invoke_Should_When_Success() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(any());

        when(flowEventLogsRepository.save(any())).thenReturn(new BigInteger(String.valueOf(1)));

        when(flowEventLogsRepository.searchByEventIdAndFlowId(anyString(), anyLong()))
                .thenReturn(FlowEventLogsEntityMock.build());

        createEventUseCase = new CreateEventUseCase(flowEventLogsRepository);

        final FlowEventLogsResponseDTO flowEventLogsDTO = createEventUseCase.invoke(EventRequestDTOMock.build());

        Assert.notNull(flowEventLogsDTO);
        clearAllCaches();
    }

    @Test
    void test_Invoke_Should_When_Not_Registered() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(any());

        when(flowEventLogsRepository.save(any())).thenReturn(new BigInteger(String.valueOf(-1)));

        when(flowEventLogsRepository.searchByEventIdAndFlowId(anyString(), anyLong()))
                .thenReturn(FlowEventLogsEntityMock.build());

        createEventUseCase = new CreateEventUseCase(flowEventLogsRepository);

        final FlowEventLogsResponseDTO flowEventLogsDTO = createEventUseCase.invoke(EventRequestDTOMock.build());

        Assert.notNull(flowEventLogsDTO);
        clearAllCaches();
    }

    @Test
    void test_Invoke_Should_When_Instance() {

        createEventUseCase = new CreateEventUseCase();
        Assert.notNull(createEventUseCase);
    }
}
