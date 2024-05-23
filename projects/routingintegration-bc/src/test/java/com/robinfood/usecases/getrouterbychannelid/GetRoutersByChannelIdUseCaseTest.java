package com.robinfood.usecases.getrouterbychannelid;

import com.robinfood.datamock.FlowsEntityMock;
import com.robinfood.datamock.RoutesEntityMock;
import com.robinfood.dtos.getrouters.request.HandlerRequestDTO;
import com.robinfood.dtos.getrouters.response.RouterResponseDTO;
import com.robinfood.repository.flows.IFlowsRepository;
import com.robinfood.repository.routes.IRoutesRepository;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class GetRoutersByChannelIdUseCaseTest {

    @Mock
    private IFlowsRepository flowsRepository;

    @Mock
    private IRoutesRepository routesRepository;

    @Mock
    private GetRoutersByChannelIdUseCase getRoutersByChannelIdUseCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_invoke_Should_When() {

        when(flowsRepository.searchByFlowCode(anyString()))
                .thenReturn(FlowsEntityMock.build());

        when(routesRepository.searchRouteByFlowIdAndChannelId(anyLong(), anyLong()))
                .thenReturn(RoutesEntityMock.build());

        getRoutersByChannelIdUseCase = new
                GetRoutersByChannelIdUseCase(flowsRepository, routesRepository);

        RouterResponseDTO routerResponseDTO =
                getRoutersByChannelIdUseCase.invoke(new HandlerRequestDTO("CHANGE_STATUS", 1L));

        Assertions.assertNotNull(routerResponseDTO);

    }

    @Test
    void test_Invoke_buildGetRoutersByChannelIdUseCase_Should_When_Instance() {

        getRoutersByChannelIdUseCase = new GetRoutersByChannelIdUseCase();
        Assert.notNull(getRoutersByChannelIdUseCase);
    }
}
