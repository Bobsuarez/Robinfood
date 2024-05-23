package com.robinfood.usecases.subscriberchangestatus;

import com.robinfood.datamock.ChangeStatusDTOMock;
import com.robinfood.datamock.EventDTOMock;
import com.robinfood.datamock.SubscriberChannelDTOMock;
import com.robinfood.dtos.ChangeStatusDTO;
import com.robinfood.dtos.EventDTO;
import com.robinfood.usecases.getsubscriberbychannelidandflow.IGetSubscriberByChannelIdAndFlowUseCase;
import com.robinfood.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.usecases.replicateevent.IReplicateEventUseCase;
import com.robinfood.usecases.saveevent.ISaveEventUseCase;
import com.robinfood.utils.LogsUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class SubscriberChangeStatusUseCaseTest {

    @Mock
    private IGetSubscriberByChannelIdAndFlowUseCase getSubscriberByChannelIdAndFlow;

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @Mock
    private IReplicateEventUseCase replicateEventUseCase;

    @Mock
    private ISaveEventUseCase saveEventUseCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test()
    void test_SubscriberChangeStatusUseCase_Should_ReturnEventHistoryEntity_When_InvokeTheUseCase() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(anyString());

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn("token");

        when(getSubscriberByChannelIdAndFlow.invoke(anyLong(), anyString(), anyString(), anyString()))
                .thenReturn(SubscriberChannelDTOMock.getDefault());

        when(saveEventUseCase.invoke(any(ChangeStatusDTO.class), anyString(), anyString()))
                .thenReturn(EventDTOMock.getDefault());

        doNothing().when(replicateEventUseCase)
                .invoke(any(ChangeStatusDTO.class), any(EventDTO.class), anyList(), anyString(), anyString());

        SubscriberChangeStatusUseCase subscriberChangeStatusUseCase =
                new SubscriberChangeStatusUseCase(getSubscriberByChannelIdAndFlow,
                        getTokenBusinessCapabilityUseCase,
                        replicateEventUseCase, saveEventUseCase);

        subscriberChangeStatusUseCase.invoke(ChangeStatusDTOMock.getDefault(), "eventID");

        Mockito.verify(replicateEventUseCase, Mockito.times(1))
                .invoke(any(ChangeStatusDTO.class), any(EventDTO.class), anyList(), anyString(), anyString());

        clearAllCaches();
    }

    @Test
    void test_SubscriberChangeStatusUseCase_Should_BuildConstructor_When_MethodInvoke() {

        SubscriberChangeStatusUseCase subscriberChangeStatusUseCase = new SubscriberChangeStatusUseCase();

        Assertions.assertNotNull(subscriberChangeStatusUseCase);
    }
}