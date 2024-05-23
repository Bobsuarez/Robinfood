package com.robinfood.repository.routes;

import com.robinfood.entities.RoutesEntity;

public interface IRoutesRepository {

    RoutesEntity searchRouteByFlowIdAndChannelId(Long channelId, Long flowId);
}
