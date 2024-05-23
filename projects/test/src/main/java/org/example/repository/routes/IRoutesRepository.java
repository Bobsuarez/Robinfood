package org.example.repository.routes;

import org.example.entities.RoutesEntity;

public interface IRoutesRepository {

    RoutesEntity searchRouteByFlowIdAndChannelId(Long channelId, Long flowId);
}
