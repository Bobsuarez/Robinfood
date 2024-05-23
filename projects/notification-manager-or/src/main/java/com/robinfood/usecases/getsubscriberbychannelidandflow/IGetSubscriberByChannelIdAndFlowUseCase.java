package com.robinfood.usecases.getsubscriberbychannelidandflow;

import com.robinfood.dtos.SubscriberChannelDTO;

/**
 * Get subscriber by channel id and flow code use case
 */
public interface IGetSubscriberByChannelIdAndFlowUseCase {

    SubscriberChannelDTO invoke(Long channelId, String flowCode, String orderUuid, String token);
}
