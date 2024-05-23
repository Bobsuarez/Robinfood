package com.robinfood.usecases.geteventbyidandflow;

import com.robinfood.entities.EventEntity;
import com.robinfood.repository.event.IEventRepository;
import com.robinfood.utils.LogsUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class GetEventByIdAndFlowUseCaseTest {

    @Mock
    private IEventRepository eventRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_invoke_Should_When() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(anyString());

        when(eventRepository.getEventByIdAndFlow(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(
                        EventEntity.builder().build()
                );

        GetEventByIdAndFlowUseCase getEventByIdAndFlowUseCase = new GetEventByIdAndFlowUseCase(eventRepository);

        getEventByIdAndFlowUseCase.invoke(
                "CHANGE_STATUS",
                "4263adab-95f6-4c9c-a997-1013349f85d8",
                "",
                ""
        );

        Mockito.verify(eventRepository, times(1))
                .getEventByIdAndFlow(anyString(), anyString(), anyString(), anyString());

        clearAllCaches();
    }

    @Test
    void test_GetEventByIdAndFlowUseCase_Should_BuildConstructor_When_MethodInvoke() {

        GetEventByIdAndFlowUseCase getEventByIdAndFlowUseCase = new GetEventByIdAndFlowUseCase();

        Assertions.assertNotNull(getEventByIdAndFlowUseCase);
    }
}