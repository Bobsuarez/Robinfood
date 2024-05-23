package com.robinfood.usecases.processhandler;

import com.amazonaws.services.lambda.runtime.events.ActiveMQEvent;
import com.robinfood.dtos.ChangeStatusDTO;
import com.robinfood.usecases.subscriberchangestatus.SubscriberChangeStatusUseCase;
import com.robinfood.utils.LogsUtil;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class ProcessHandlerUseCaseTest {

    @Mock
    private ActiveMQEvent activeMQEvent;

    @Mock
    private SubscriberChangeStatusUseCase subscriberChangeStatusUseCase;

    @NotNull
    private static ActiveMQEvent.ActiveMQMessage getActiveMQMessage() {
        ActiveMQEvent.ActiveMQMessage activeMQMessage = new ActiveMQEvent.ActiveMQMessage();
        activeMQMessage.setData("e1wiY2hhbm5lbElkXCI6MTAsXCJjb3Vwb25SZWZlcmVuY2VcIjpudWxsLFwiYnJhbmRJZFwiOm51bGwsXCJkZWxpdmVyeUludGVncmF0aW9uSWRcIjpudWxsLFwibm90ZXNcIjpcImV4ZWN1dGUgY2hhbmdlIHN0YXR1cyBmcm9tIGxvY2Fsc2VydmVyXCIsXCJvcmRlcklkXCI6NTQ5Mzc2MyxcIm9yZGVyVWlkXCI6bnVsbCxcIm9yZGVyVXVpZFwiOlwiYTQyNzFlN2QtNDE2Yi00NTRjLTk4ZmEtNjA0ZmRiYjQ0N2NhXCIsXCJvcmlnaW5cIjpcImxvY2Fsc2VydmVyXCIsXCJzdGF0dXNDb2RlXCI6XCJPUkRFUl9SRUFEWV9UT19ERUxJVkVSRURcIixcInN0YXR1c0lkXCI6bnVsbCxcInRyYW5zYWN0aW9uSWRcIjpcIjEyMzQ1NjdcIixcInRyYW5zYWN0aW9uVXVpZFwiOlwiMTIzNDU2N1wiLFwidXNlcklkXCI6MX0=");
        return activeMQMessage;
    }

    @NotNull
    private static ActiveMQEvent.ActiveMQMessage getActiveMQMessageError() {
        ActiveMQEvent.ActiveMQMessage activeMQMessage = new ActiveMQEvent.ActiveMQMessage();
        activeMQMessage.setData("Ilwie1xcXCJjaGFubmVsSWRcXFwiOjEwLFxcXCJjb3Vwb25SZWZlcmVuY2VcXFwiOm51bGwsXFxcImJyYW5kSWRcXFwiOm51bGwsXFxcImRlbGl2ZXJ5SW50ZWdyYXRpb25JZFxcXCI6bnVsbCxcXFwibm90ZXNcXFwiOlxcXCJleGVjdXRlIGNoYW5nZSBzdGF0dXMgZnJvbSBsb2NhbHNlcnZlclxcXCIsXFxcIm9yZGVySWRcXFwiOjU0OTM3NjMsXFxcIm9yZGVyVWlkXFxcIjpudWxsLFxcXCJvcmRlclV1aWRcXFwiOlxcXCJhNDI3MWU3ZC00MTZiLTQ1NGMtOThmYS02MDRmZGJiNDQ3Y2FcXFwiLFxcXCJvcmlnaW5cXFwiOlxcXCJsb2NhbHNlcnZlclxcXCIsXFxcInN0YXR1c0NvZGVcXFwiOlxcXCJPUkRFUl9SRUFEWV9UT19ERUxJVkVSRURcXFwiLFxcXCJzdGF0dXNJZFxcXCI6bnVsbCxcXFwidHJhbnNhY3Rpb25JZFxcXCI6XFxcIjEyMzQ1NjdcXFwiLFxcXCJ0cmFuc2FjdGlvblV1aWRcXFwiOlxcXCIxMjM0NTY3XFxcIixcXFwidXNlcklkXFxcIjoxfVwiIg==");
        return activeMQMessage;
    }

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_ProcessHandlerUseCase_Should_ReturnToken_When_InvokeTheUseCase() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(anyString());

        doNothing().when(subscriberChangeStatusUseCase).invoke(any(ChangeStatusDTO.class), anyString());

        ProcessHandlerUseCase processHandlerUseCase = new ProcessHandlerUseCase(subscriberChangeStatusUseCase);

        ActiveMQEvent.ActiveMQMessage activeMQMessage = getActiveMQMessage();

        List<ActiveMQEvent.ActiveMQMessage> activeMQMessages = new ArrayList<>();
        activeMQMessages.add(activeMQMessage);

        when(activeMQEvent.getMessages()).thenReturn(activeMQMessages);

        processHandlerUseCase.invoke(activeMQEvent);

        Assertions.assertNotNull(subscriberChangeStatusUseCase);

        clearAllCaches();
    }

    @Test
    void test_ProcessHandlerUseCase_Should_ApplicationException_When_InvokeTheUseCase() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(anyString());

        doNothing().when(subscriberChangeStatusUseCase).invoke(any(ChangeStatusDTO.class), anyString());

        ProcessHandlerUseCase processHandlerUseCase = new ProcessHandlerUseCase(subscriberChangeStatusUseCase);

        ActiveMQEvent.ActiveMQMessage activeMQMessage = getActiveMQMessageError();

        List<ActiveMQEvent.ActiveMQMessage> activeMQMessages = new ArrayList<>();
        activeMQMessages.add(activeMQMessage);

        when(activeMQEvent.getMessages()).thenReturn(activeMQMessages);

        processHandlerUseCase.invoke(activeMQEvent);

        Assertions.assertNotNull(subscriberChangeStatusUseCase);

        clearAllCaches();
    }

    @Test
    void test_ProcessHandlerUseCase_Should_ApplicationException_When_IsNullActiveEvent() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(anyString());

        ProcessHandlerUseCase processHandlerUseCase = new ProcessHandlerUseCase(subscriberChangeStatusUseCase);

        processHandlerUseCase.invoke(null);

        Assertions.assertNotNull(subscriberChangeStatusUseCase);

        clearAllCaches();
    }

    @Test
    void test_ProcessHandlerUseCase_Should_BuildConstructor_When_MethodInvoke() {

        ProcessHandlerUseCase processHandlerUseCase = new ProcessHandlerUseCase();

        Assertions.assertNotNull(processHandlerUseCase);
    }

}