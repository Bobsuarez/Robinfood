package com.robinfood.usecases;

import com.robinfood.datamock.RequestChangeStateDTOMock;
import com.robinfood.dtos.request.RequestChangeStateDTO;
import com.robinfood.queues.publisher.ChangeStatusOrderPublisher;
import com.robinfood.utils.LogsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;

class SendMessageUseCaseTest {

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        System.setProperty("PILOT_STORES", "27, 3, 4, 5");
    }

    @Test
    void test_HandleRequest_store_pilot() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(anyString());

        mockStatic(ChangeStatusOrderPublisher.class);
        doNothing().when(ChangeStatusOrderPublisher.class);
        ChangeStatusOrderPublisher.sendMessage(any(RequestChangeStateDTO.class));

        SendMessageUseCase sendMessageUseCase = new SendMessageUseCase();

        sendMessageUseCase.invoke(RequestChangeStateDTOMock.getDefault(), 27L);

        clearAllCaches();
    }

    @Test
    void Test_HandleRequest_When_StoreIdIsNull_Should_SendMessage() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(anyString());

        mockStatic(ChangeStatusOrderPublisher.class);
        doNothing().when(ChangeStatusOrderPublisher.class);
        ChangeStatusOrderPublisher.sendMessage(any(RequestChangeStateDTO.class));

        SendMessageUseCase sendMessageUseCase = new SendMessageUseCase();

        sendMessageUseCase.invoke(RequestChangeStateDTOMock.getNotComplete(), null);

        clearAllCaches();
    }

}