package com.robinfood.usecases.saveevent;

import com.robinfood.dtos.ChangeStatusDTO;
import com.robinfood.entities.EventEntity;
import com.robinfood.repository.event.IEventRepository;
import com.robinfood.utils.LogsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class SaveEventUseCaseTest {

    @Mock
    private IEventRepository eventRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_invoke_Should_ReturnEventEntity_When_InvokeTheUseCase() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(anyString());

        when(eventRepository.saveEvent(any(EventEntity.class), anyString()))
                .thenReturn(EventEntity.builder().build());

        SaveEventUseCase saveEventUseCase = new SaveEventUseCase(eventRepository);
        saveEventUseCase.invoke(ChangeStatusDTO.builder().build(), "", "");

        Mockito.verify(eventRepository, Mockito.times(1))
                .saveEvent(any(EventEntity.class), anyString());

        clearAllCaches();
    }
}