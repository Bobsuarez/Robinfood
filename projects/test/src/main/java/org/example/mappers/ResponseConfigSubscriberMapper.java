package org.example.mappers;


import java.util.List;
import org.example.dtos.getconfigsubscribers.reponse.ResponseConfigSubscribersDTO;
import org.example.dtos.getconfigsubscribers.reponse.SubscribersItemDTO;
import org.example.entities.FlowsEntity;

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
