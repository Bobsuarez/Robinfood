package com.robinfood.network.http.api;

import com.robinfood.entities.EventEntity;
import com.robinfood.entities.EventHistoryEntity;
import com.robinfood.entities.SubscriberChannelEntity;
import com.robinfood.network.http.connection.HttpClientConnection;
import com.robinfood.utils.LogsUtil;
import com.robinfood.utils.ObjectMapperSingleton;
import lombok.SneakyThrows;

import static com.robinfood.constants.Constants.ATTRIBUTE_RESULT_DEFAULT;
import static com.robinfood.constants.Constants.URL_ROUTING_INTEGRATION_BC;

/**
 * RoutingIntegrationBcAPI
 */

public class RoutingIntegrationBcAPI extends HttpClientConnection {

    /**
     * get Event By Id And Flow
     *
     * @param eventId   Event Id
     * @param flowCode  Flow Code
     * @param orderUuid Order Uuid
     * @param token     the authorization token
     * @return Event Entity
     */
    @SneakyThrows
    public EventEntity getEventByIdAndFlow(
            String eventId,
            String flowCode,
            String orderUuid,
            String token
    ) {

        final String URL = String.format(
                "%sv1/event/%s/flow/%s?uuid=%s",
                URL_ROUTING_INTEGRATION_BC,
                eventId,
                flowCode,
                orderUuid
        );

        final String responseBodyString =
                connectionProcess(ATTRIBUTE_RESULT_DEFAULT, token, URL);

        LogsUtil.info("responseBodyString {} ", responseBodyString);

        return ObjectMapperSingleton.jsonToClass(responseBodyString, EventEntity.class);
    }

    /**
     * Get Subscriber By Channel Id And Flow
     *
     * @param channelId channel Id
     * @param flowCode  flow Code
     * @param orderUuid orderUuid code
     * @param token     the authorization token
     * @return Subscriber Channel Entity
     */
    @SneakyThrows
    public SubscriberChannelEntity getSubscriberByChannelIdAndFlow(
            Long channelId,
            String flowCode,
            String orderUuid,
            String token
    ) {

        final String urlFormat = String.format(
                "%sv1/flow/%s/channel/%s/subscribers?uuid=%s",
                URL_ROUTING_INTEGRATION_BC,
                flowCode,
                channelId,
                orderUuid
        );

        final String responseBodyString = connectionProcess(ATTRIBUTE_RESULT_DEFAULT, token, urlFormat);

        return ObjectMapperSingleton.jsonToClass(responseBodyString, SubscriberChannelEntity.class);
    }

    /**
     * Save Event
     *
     * @param eventEntity Event Entity
     * @param token       the authorization token
     * @return Event Entity Created
     */
    @SneakyThrows
    public EventEntity saveEvent(EventEntity eventEntity, String token) {

        final String URL = URL_ROUTING_INTEGRATION_BC + "v1/events";

        final String responseBodyString =
                connectionProcess(ATTRIBUTE_RESULT_DEFAULT, eventEntity, token, URL);

        return ObjectMapperSingleton.jsonToClass(responseBodyString, EventEntity.class);
    }

    /**
     * Save Event History
     *
     * @param eventHistoryEntity Event History Entity
     * @param token              the authorization token
     * @return Event History Entity Created
     */
    @SneakyThrows
    public void saveEventHistory(EventHistoryEntity eventHistoryEntity, String token) {

        final String URL = URL_ROUTING_INTEGRATION_BC + "v1/events/history";

        connectionProcess(ATTRIBUTE_RESULT_DEFAULT, eventHistoryEntity, token, URL);
    }

}
