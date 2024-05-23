package com.robinfood.usecases.getsubscriberbychannelidandflow;

import com.robinfood.entities.SubscriberChannelEntity;
import com.robinfood.repository.subscriber.ISubscriberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

class GetSubscriberByChannelIdAndFlowTest {

    @Mock
    private ISubscriberRepository subscriberRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_invoke_Should_ReturnSubscriberChannel_When_InvokeTheUseCase() {
        Mockito.when(subscriberRepository
                        .getSubscriberByChannelIdAndFlow(anyLong(), anyString(), anyString(), anyString()))
                .thenReturn(SubscriberChannelEntity.builder().build());

        GetSubscriberByChannelIdAndFlowUseCase getSubscriberByChannelIdAndFlow =
                new GetSubscriberByChannelIdAndFlowUseCase(subscriberRepository);

        getSubscriberByChannelIdAndFlow.invoke(10L, "CHANGE_STATUS", "", "");

        Mockito.verify(subscriberRepository, Mockito.times(1)).getSubscriberByChannelIdAndFlow(
                anyLong(), anyString(), anyString(), anyString()
        );
    }

}