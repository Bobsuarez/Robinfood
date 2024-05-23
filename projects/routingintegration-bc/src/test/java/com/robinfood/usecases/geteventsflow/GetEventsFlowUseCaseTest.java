package com.robinfood.usecases.geteventsflow;

import com.robinfood.datamock.FlowEventLogsEntityMock;
import com.robinfood.datamock.FlowsEntityMock;
import com.robinfood.dtos.geteventflow.request.EventFlowRequestDTO;
import com.robinfood.dtos.geteventflow.response.ResponseEventFlowDTO;
import com.robinfood.repository.floweventlogs.IFlowEventLogsRepository;
import com.robinfood.repository.flows.IFlowsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class GetEventsFlowUseCaseTest {

    @Mock
    private IFlowsRepository flowsRepository;

    @Mock
    private IFlowEventLogsRepository flowEventLogsRepository;

    @Mock
    private GetEventsFlowUseCase getEventsFlowUseCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_invoke_Should_When() {

        when(flowsRepository.searchByFlowCode(anyString()))
                .thenReturn(FlowsEntityMock.build());

        when(flowEventLogsRepository.searchByEventIdAndFlowId(anyString(), anyLong()))
                .thenReturn(FlowEventLogsEntityMock.build());

        getEventsFlowUseCase = new GetEventsFlowUseCase(flowsRepository, flowEventLogsRepository);

        ResponseEventFlowDTO eventFlowDTO =
                getEventsFlowUseCase.invoke(new EventFlowRequestDTO("CHANGE_STATUS", "1db0e433-6697-4050-8b8c" +
                        "-64cbb28227d3"));

        Assertions.assertNotNull(eventFlowDTO);

    }
}
