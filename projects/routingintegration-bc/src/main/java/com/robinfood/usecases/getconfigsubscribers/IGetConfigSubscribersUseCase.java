package com.robinfood.usecases.getconfigsubscribers;

import com.robinfood.dtos.getconfigsubscribers.reponse.ResponseConfigSubscribersDTO;
import com.robinfood.dtos.getrouters.request.HandlerRequestDTO;

public interface IGetConfigSubscribersUseCase {
    ResponseConfigSubscribersDTO invoke(HandlerRequestDTO handlerRequestDTO);

}
