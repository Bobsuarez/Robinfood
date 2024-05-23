package com.robinfood.mappers;

import com.robinfood.dtos.getconfigsubscribers.reponse.ResponseConfigSubscribersDTO;
import com.robinfood.dtos.getconfigsubscribers.reponse.SubscribersItemDTO;
import com.robinfood.entities.FlowsEntity;

import java.util.List;

public class EntitySubscriberChannelToDTOMapper {

    public ResponseConfigSubscribersDTO buildToResponseDTO(
            FlowsEntity flowsEntity,
            List<SubscribersItemDTO> subscribers,
            Long subscriberChannelId
    ) {

        return ResponseConfigSubscribersDTO.builder()
                .flow(EntityFlowsToDTOMapper.buildToFlowDTO(flowsEntity))
                .subscriberChannelId(subscriberChannelId)
                .subscribers(subscribers)
                .build();
    }
}
