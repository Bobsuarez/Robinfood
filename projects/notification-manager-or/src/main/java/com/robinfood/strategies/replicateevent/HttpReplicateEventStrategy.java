package com.robinfood.strategies.replicateevent;

import com.robinfood.dtos.ChangeStatusDTO;
import com.robinfood.dtos.PropertyDTO;
import com.robinfood.dtos.SubscriberDTO;
import com.robinfood.entities.ChangeStatusEntity;
import com.robinfood.exceptions.ResourceNotFoundException;
import com.robinfood.mappers.ChangeStatusMapper;
import com.robinfood.repository.statuschangepublished.IStatusChangePublishRepository;
import com.robinfood.repository.statuschangepublished.StatusChangePublishRepository;

import java.util.Objects;

import static com.robinfood.constants.Constants.PROPERTY_URL_TYPE;

public class HttpReplicateEventStrategy implements IReplicateEventStrategy {

    private final IStatusChangePublishRepository statusChangePublishRepository;

    public HttpReplicateEventStrategy() {
        statusChangePublishRepository = new StatusChangePublishRepository();
    }

    public HttpReplicateEventStrategy(IStatusChangePublishRepository statusChangePublishRepository) {
        this.statusChangePublishRepository = statusChangePublishRepository;
    }

    @Override
    public void execute(ChangeStatusDTO changeStatusDTO, String eventId, SubscriberDTO subscriberDTO, String token) {

        final PropertyDTO propertyDTO = subscriberDTO.getProperties()
                .stream()
                .filter(property -> Objects.equals(property.getKey(), PROPERTY_URL_TYPE))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Property URL not found"));

        ChangeStatusEntity changeStatusConvert =
                ChangeStatusMapper.changeStatusDtoToChangeStatusEntity(changeStatusDTO, eventId);

        statusChangePublishRepository.publish(
                changeStatusConvert,
                token,
                propertyDTO.getName()
        );
    }
}
