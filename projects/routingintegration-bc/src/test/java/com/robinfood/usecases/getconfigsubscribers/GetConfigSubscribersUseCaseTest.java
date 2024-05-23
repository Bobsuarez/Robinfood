package com.robinfood.usecases.getconfigsubscribers;

import com.robinfood.datamock.FlowsEntityMock;
import com.robinfood.datamock.SubscriberTypeEntityMock;
import com.robinfood.datamock.SubscribersChannelsEntityMock;
import com.robinfood.datamock.SubscribersEntityMock;
import com.robinfood.datamock.SubscribersPropertiesEntityMock;
import com.robinfood.dtos.getconfigsubscribers.reponse.ResponseConfigSubscribersDTO;
import com.robinfood.dtos.getrouters.request.HandlerRequestDTO;
import com.robinfood.repository.flows.IFlowsRepository;
import com.robinfood.repository.subscriberchannels.ISubscriberChannelsRepository;
import com.robinfood.repository.subscriberproperties.ISubscriberPropertiesRepository;
import com.robinfood.repository.subscribers.ISubscribersRepository;
import com.robinfood.repository.subscribertypes.ISubscriberTypesRepository;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class GetConfigSubscribersUseCaseTest {

    @Mock
    private ISubscriberChannelsRepository subscribersChannelsRepository;
    @Mock
    private ISubscriberPropertiesRepository subscriberPropertiesRepository;
    @Mock
    private ISubscriberTypesRepository subscriberTypesRepository;

    @Mock
    private ISubscribersRepository subscribersRepository;
    @Mock
    private IFlowsRepository flowsRepository;

    @Mock
    private GetConfigSubscribersUseCase getConfigSubscribersUseCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_Should_GetConfigSubscribers_When_Data_Ok() {

        when(flowsRepository.searchByFlowCode(anyString()))
                .thenReturn(FlowsEntityMock.build());

        when(subscribersChannelsRepository.findByChannelIdAndFlowId(anyLong(), anyLong()))
                .thenReturn(List.of(SubscribersChannelsEntityMock.build()));

        when(subscriberPropertiesRepository.findBySubscriberId(anyLong()))
                .thenReturn(List.of(SubscribersPropertiesEntityMock.build()));

        when(subscribersRepository.findById(anyLong()))
                .thenReturn(SubscribersEntityMock.build());

        when(subscriberTypesRepository.findById(anyLong()))
                .thenReturn(SubscriberTypeEntityMock.build());

        getConfigSubscribersUseCase = new GetConfigSubscribersUseCase(
                subscribersChannelsRepository,
                subscriberPropertiesRepository,
                subscriberTypesRepository,
                subscribersRepository,
                flowsRepository
        );

        ResponseConfigSubscribersDTO configSubscribersDTO =
                getConfigSubscribersUseCase.invoke(new HandlerRequestDTO("CHANGE_STATUS", 1L));

        Assertions.assertNotNull(configSubscribersDTO);

    }

    @Test
    void test_Invoke_buildGetConfigSubscribersUseCase_Should_When_Instance() {

        getConfigSubscribersUseCase = new GetConfigSubscribersUseCase();
        Assert.notNull(getConfigSubscribersUseCase);
    }
}
