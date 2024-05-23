package com.robinfood.network.api;

import com.robinfood.dtos.request.RequestChangeOrderStatusDTO;
import com.robinfood.network.connection.HttpClientConnection;
import lombok.SneakyThrows;

import static com.robinfood.constants.Constants.ROUTING_ATTRIBUTE_RESULT;

/**
 * ChangeOrderStatusBcApi
 */
public class ChangeOrderStatusBcApi {

    private final HttpClientConnection httpClientConnection;


    /**
     * constructor for unit tests
     *
     * @param httpClientConnection attribute to calculate
     */
    public ChangeOrderStatusBcApi(HttpClientConnection httpClientConnection) {
        this.httpClientConnection = httpClientConnection;
    }

    /**
     * constructor for initialization of the class in the program
     */
    public ChangeOrderStatusBcApi() {
        httpClientConnection = new HttpClientConnection();
    }

    /**
     * services change order status
     *
     * @param requestChangeOrderStatusDTO body
     * @param url                         url services
     * @param token                       services token
     * @return ResponseChangeOrderStatusEntity Entity
     */
    @SneakyThrows
    public void changeOrderStatus(
            RequestChangeOrderStatusDTO requestChangeOrderStatusDTO,
            String token,
            String url
    ) {
        httpClientConnection.connectionProcess(ROUTING_ATTRIBUTE_RESULT, requestChangeOrderStatusDTO, token, url);
    }
}
