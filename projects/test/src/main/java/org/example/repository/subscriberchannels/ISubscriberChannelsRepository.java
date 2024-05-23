package org.example.repository.subscriberchannels;


import java.util.List;
import org.example.entities.SubscriberChannelsEntity;

public interface ISubscriberChannelsRepository {

    List<SubscriberChannelsEntity> findByChannelIdAndFlowId(Long channelId, Long flowId);
}
