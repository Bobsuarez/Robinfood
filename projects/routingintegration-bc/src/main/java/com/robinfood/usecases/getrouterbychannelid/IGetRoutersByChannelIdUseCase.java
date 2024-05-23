package com.robinfood.usecases.getrouterbychannelid;

import com.robinfood.dtos.getrouters.request.HandlerRequestDTO;
import com.robinfood.dtos.getrouters.response.RouterResponseDTO;

public interface IGetRoutersByChannelIdUseCase {

    RouterResponseDTO invoke(HandlerRequestDTO routeRequest);

}
