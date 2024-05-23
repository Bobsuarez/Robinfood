package com.robinfood.mappers;

import com.robinfood.dtos.getconfigsubscribers.reponse.ResponseConfigSubscribersDTO;
import com.robinfood.dtos.getconfigsubscribers.reponse.SubscribersItemDTO;
import com.robinfood.entities.FlowsEntity;

import java.util.List;

public class ResponseConfigSubscriberMapper {

    public static ResponseConfigSubscribersDTO buildToResponseConfigSubscribersDTO(
            FlowsEntity flowsEntity,
            List<SubscribersItemDTO> subscribers
    ) {

        return ResponseConfigSubscribersDTO.builder()
                .flow(EntityFlowsToDTOMapper.buildToFlowDTO(flowsEntity))
                .subscribers(subscribers)
                .build();
    }
}
