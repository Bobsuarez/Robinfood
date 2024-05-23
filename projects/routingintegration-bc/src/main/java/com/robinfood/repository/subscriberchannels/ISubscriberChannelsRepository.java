package com.robinfood.repository.subscriberchannels;

import com.robinfood.entities.SubscriberChannelsEntity;

import java.util.List;

public interface ISubscriberChannelsRepository {

    List<SubscriberChannelsEntity> findByChannelIdAndFlowId(Long channelId, Long flowId);
}
