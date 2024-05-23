package com.robinfood.repository.subscriber;

import com.robinfood.entities.SubscriberChannelEntity;
import com.robinfood.enums.AppLogsTraceEnum;
import com.robinfood.network.http.api.RoutingIntegrationBcAPI;
import com.robinfood.utils.LogsUtil;
import com.robinfood.utils.ObjectMapperSingleton;


public class SubscriberRepository implements ISubscriberRepository {

    private final RoutingIntegrationBcAPI routingIntegrationBcAPI;

    public SubscriberRepository() {
        this.routingIntegrationBcAPI = new RoutingIntegrationBcAPI();
    }

    public SubscriberRepository(RoutingIntegrationBcAPI routingIntegrationBcAPI) {
        this.routingIntegrationBcAPI = routingIntegrationBcAPI;
    }

    @Override
    public SubscriberChannelEntity getSubscriberByChannelIdAndFlow(
            Long channelId,
            String flowCode,
            String orderUuid,
            String token
    ) {

        LogsUtil.info(AppLogsTraceEnum.INITIAL_PROCESS_GET_SUBSCRIBER.getMessage(), channelId, flowCode);

        final SubscriberChannelEntity searchAllSubscriber =
                routingIntegrationBcAPI.getSubscriberByChannelIdAndFlow(channelId, flowCode, orderUuid, token);

        LogsUtil.info(
                AppLogsTraceEnum.FINAL_PROCESS_GET_SUBSCRIBER.getMessage(),
                ObjectMapperSingleton.objectToJson(searchAllSubscriber)
        );

        return searchAllSubscriber;
    }
}
