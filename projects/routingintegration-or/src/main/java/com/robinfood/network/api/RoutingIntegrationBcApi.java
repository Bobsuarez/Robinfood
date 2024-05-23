package com.robinfood.network.api;

import com.robinfood.entities.api.routingintegration.ResponseRoutingIntegrationEntity;
import com.robinfood.network.connection.HttpClientConnection;
import com.robinfood.util.ObjectMapperSingleton;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

import static com.robinfood.constants.APIConstants.URL_ROUTING_INTEGRATION_BC;
import static com.robinfood.constants.APIConstants.URL_ROUTING_INTEGRATION_BC_API;
import static com.robinfood.constants.Constants.CHANGE_STATUS;
import static com.robinfood.constants.Constants.ROUTING_ATTRIBUTE_RESULT;

/**
 * RoutingIntegrationBcApi
 */
public class RoutingIntegrationBcApi {

    private final HttpClientConnection httpClientConnection;

    /**
     * constructor for unit tests
     *
     * @param httpClientConnection attribute to calculate
     */
    public RoutingIntegrationBcApi(HttpClientConnection httpClientConnection) {
        this.httpClientConnection = httpClientConnection;
    }

    /**
     * constructor for initialization of the class in the program
     */
    public RoutingIntegrationBcApi() {
        httpClientConnection = new HttpClientConnection();
    }

    /**
     * service routing integration
     *
     * @param id    identification channel
     * @param token services token
     * @param uuId  identification order
     * @return ResponseRoutingIntegrationEntity Entity
     */
    public ResponseRoutingIntegrationEntity getChannelIntegration(
            @Required Long id,
            @Required String token,
            @Required String uuId
    ) {

        String urlRoutingBCBuild = URL_ROUTING_INTEGRATION_BC_API + URL_ROUTING_INTEGRATION_BC;
        final String url = String.format(
                urlRoutingBCBuild,
                id,
                CHANGE_STATUS,
                uuId
        );

        String responseBody = httpClientConnection.connectionProcess(ROUTING_ATTRIBUTE_RESULT, token, url);
        return ObjectMapperSingleton.jsonToClass(responseBody, ResponseRoutingIntegrationEntity.class);
    }
}
