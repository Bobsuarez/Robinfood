package com.robinfood.usecases.saveeventhistory;

import com.robinfood.entities.EventHistoryEntity;
import com.robinfood.repository.eventhistory.IEventHistoryRepository;
import com.robinfood.utils.LogsUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class SaveEventHistoryUseCaseTest {

    @Mock
    IEventHistoryRepository eventHistoryRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_SaveEventHistoryUseCase_Should_ReturnEventHistoryEntity_When_InvokeTheUseCase() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(anyString());

        doNothing().when(eventHistoryRepository).saveEventHistory(any(EventHistoryEntity.class), anyString());

        SaveEventHistoryUseCase saveEventHistoryUseCase = new SaveEventHistoryUseCase(eventHistoryRepository);

        saveEventHistoryUseCase.invoke(1L, 1L, 1L, "", "");

        verify(eventHistoryRepository, times(1))
                .saveEventHistory(any(EventHistoryEntity.class), anyString());

        clearAllCaches();
    }

    @Test
    void test_SaveEventHistoryUseCase_Should_BuildConstructor_When_MethodInvoke() {

        SaveEventHistoryUseCase saveEventHistoryUseCase = new SaveEventHistoryUseCase();

        Assertions.assertNotNull(saveEventHistoryUseCase);
    }
}