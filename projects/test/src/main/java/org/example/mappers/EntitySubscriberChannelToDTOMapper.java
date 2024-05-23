package org.example.mappers;


import java.util.List;
import org.example.dtos.getconfigsubscribers.reponse.ResponseConfigSubscribersDTO;
import org.example.dtos.getconfigsubscribers.reponse.SubscribersItemDTO;
import org.example.entities.FlowsEntity;

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
