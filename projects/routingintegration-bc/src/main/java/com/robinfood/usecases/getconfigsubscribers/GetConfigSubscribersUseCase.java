package com.robinfood.usecases.getconfigsubscribers;

import com.robinfood.dtos.getconfigsubscribers.reponse.PropertiesItemDTO;
import com.robinfood.dtos.getconfigsubscribers.reponse.ResponseConfigSubscribersDTO;
import com.robinfood.dtos.getconfigsubscribers.reponse.SubscribersItemDTO;
import com.robinfood.dtos.getrouters.request.HandlerRequestDTO;
import com.robinfood.entities.FlowsEntity;
import com.robinfood.entities.SubscriberChannelsEntity;
import com.robinfood.entities.SubscriberPropertiesEntity;
import com.robinfood.entities.SubscribersEntity;
import com.robinfood.mappers.EntitySubscriberItemToDTOMapper;
import com.robinfood.mappers.EntitySubscriberPropertiesToDTOMapper;
import com.robinfood.mappers.ResponseConfigSubscriberMapper;
import com.robinfood.repository.flows.FlowsRepository;
import com.robinfood.repository.flows.IFlowsRepository;
import com.robinfood.repository.subscriberchannels.ISubscriberChannelsRepository;
import com.robinfood.repository.subscriberchannels.SubscriberChannelsRepository;
import com.robinfood.repository.subscriberproperties.ISubscriberPropertiesRepository;
import com.robinfood.repository.subscriberproperties.SubscriberPropertiesRepository;
import com.robinfood.repository.subscribers.ISubscribersRepository;
import com.robinfood.repository.subscribers.SubscribersRepository;
import com.robinfood.repository.subscribertypes.ISubscriberTypesRepository;
import com.robinfood.repository.subscribertypes.SubscriberTypesRepository;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * use case that brings the configuration of a subscriber
 */
@AllArgsConstructor
public class GetConfigSubscribersUseCase implements IGetConfigSubscribersUseCase {

    private final ISubscriberChannelsRepository subscribersChannelsRepository;
    private final ISubscriberPropertiesRepository subscriberPropertiesRepository;
    private final ISubscriberTypesRepository subscriberTypesRepository;
    private final ISubscribersRepository subscribersRepository;
    private final IFlowsRepository flowsRepository;

    public GetConfigSubscribersUseCase() {

        this.subscribersChannelsRepository = SubscriberChannelsRepository.getInstance();
        this.subscriberPropertiesRepository = SubscriberPropertiesRepository.getInstance();
        this.subscriberTypesRepository = SubscriberTypesRepository.getInstance();
        this.subscribersRepository = SubscribersRepository.getInstance();
        this.flowsRepository = FlowsRepository.getInstance();
    }

    public ResponseConfigSubscribersDTO invoke(HandlerRequestDTO handlerRequestDTO) {

        FlowsEntity flowsEntity = flowsRepository.searchByFlowCode(handlerRequestDTO.getFlowCode());

        List<SubscriberChannelsEntity> subscriberChannelsEntities = subscribersChannelsRepository
                .findByChannelIdAndFlowId(handlerRequestDTO.getChannelId(), flowsEntity.getId());

        List<Long> subscriberIds = subscriberChannelsEntities.stream()
                .map(SubscriberChannelsEntity::getSubscriber_id)
                .collect(Collectors.toList());

        List<SubscribersItemDTO> subscribersItemDTOS = new ArrayList<>();

        for (Long ids : subscriberIds) {

            List<PropertiesItemDTO> propertiesItemDTOS = new ArrayList<>();

            List<SubscriberPropertiesEntity> propertiesEntityList =
                    subscriberPropertiesRepository.findBySubscriberId(ids);

            propertiesEntityList.forEach(data ->
                    propertiesItemDTOS.add(EntitySubscriberPropertiesToDTOMapper.buildToPropertiesDTO(data)));

            SubscribersEntity subscribersEntity = subscribersRepository.findById(ids);

            SubscribersItemDTO subscribersItemDTO = EntitySubscriberItemToDTOMapper.buildToSubscribersChannelDTO(
                    propertiesItemDTOS,
                    subscribersEntity,
                    subscriberTypesRepository.findById(subscribersEntity.getSubscriber_type_id()));

            subscribersItemDTOS.add(subscribersItemDTO);
        }

        return ResponseConfigSubscriberMapper.buildToResponseConfigSubscribersDTO(
                flowsEntity,
                subscribersItemDTOS
        );
    }
}
