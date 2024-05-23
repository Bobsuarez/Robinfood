package com.robinfood.usecases.getrouterbychannelid;

import com.robinfood.dtos.getrouters.request.HandlerRequestDTO;
import com.robinfood.dtos.getrouters.response.RouterResponseDTO;
import com.robinfood.entities.FlowsEntity;
import com.robinfood.entities.RoutesEntity;
import com.robinfood.mappers.EntityRouterToDTOMapper;
import com.robinfood.repository.flows.FlowsRepository;
import com.robinfood.repository.flows.IFlowsRepository;
import com.robinfood.repository.routes.IRoutesRepository;
import com.robinfood.repository.routes.RoutesRepository;
import lombok.AllArgsConstructor;

/**
 * Use case that queries routers information per channel
 */
@AllArgsConstructor
public class GetRoutersByChannelIdUseCase implements IGetRoutersByChannelIdUseCase {

    private final IFlowsRepository flowsRepository;
    private final IRoutesRepository routesRepository;

    public GetRoutersByChannelIdUseCase() {
        this.flowsRepository = FlowsRepository.getInstance();
        this.routesRepository = RoutesRepository.getInstance();
    }

    public RouterResponseDTO invoke(HandlerRequestDTO routeRequest) {

        final FlowsEntity flowsEntity = flowsRepository.searchByFlowCode(routeRequest.getFlowCode());
        final RoutesEntity routesEntity = routesRepository
                .searchRouteByFlowIdAndChannelId(routeRequest.getChannelId(), flowsEntity.getId());

        return new EntityRouterToDTOMapper().buildToRouterResponseDTO(flowsEntity, routesEntity);
    }
}
