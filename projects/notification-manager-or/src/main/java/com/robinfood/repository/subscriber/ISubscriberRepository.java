package com.robinfood.repository.subscriber;

import com.robinfood.entities.SubscriberChannelEntity;

public interface ISubscriberRepository {
    SubscriberChannelEntity getSubscriberByChannelIdAndFlow(
            Long channelId,
            String flowCode,
            String orderUuid,
            String token
    );
}
