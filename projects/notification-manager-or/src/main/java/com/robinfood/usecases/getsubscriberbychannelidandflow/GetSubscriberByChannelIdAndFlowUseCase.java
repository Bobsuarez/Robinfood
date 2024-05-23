package com.robinfood.usecases.getsubscriberbychannelidandflow;

import com.robinfood.dtos.SubscriberChannelDTO;
import com.robinfood.entities.SubscriberChannelEntity;
import com.robinfood.repository.subscriber.ISubscriberRepository;
import com.robinfood.repository.subscriber.SubscriberRepository;
import com.robinfood.utils.ObjectMapperSingleton;

public class GetSubscriberByChannelIdAndFlowUseCase implements IGetSubscriberByChannelIdAndFlowUseCase {

    private final ISubscriberRepository subscriberRepository;

    public GetSubscriberByChannelIdAndFlowUseCase() {
        this.subscriberRepository = new SubscriberRepository();
    }

    public GetSubscriberByChannelIdAndFlowUseCase(
            ISubscriberRepository subscriberRepository
    ) {
        this.subscriberRepository = subscriberRepository;
    }

    @Override
    public SubscriberChannelDTO invoke(Long channelId, String flowCode, String orderUuid, String token) {

        SubscriberChannelEntity subscriberChannelEntity =
                subscriberRepository.getSubscriberByChannelIdAndFlow(channelId, flowCode, orderUuid, token);

        return ObjectMapperSingleton.objectToClassConvertValue(subscriberChannelEntity, SubscriberChannelDTO.class);
    }
}
