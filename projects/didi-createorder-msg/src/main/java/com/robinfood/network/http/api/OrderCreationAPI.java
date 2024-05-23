package com.robinfood.network.http.api;

import com.robinfood.dtos.request.ordertocreatedto.OrderToCreateDTO;
import com.robinfood.network.http.connection.HttpClientConnection;
import com.robinfood.util.LogsUtil;
import com.robinfood.util.ObjectMapperSingletonUtil;
import lombok.SneakyThrows;

import java.util.Map;

import static com.robinfood.constants.APIConstants.URL_STACK_SECOND;
import static com.robinfood.constants.GeneralConstants.ROUTING_ATTRIBUTE_RESULT;

/**
 * OrderCreationAPI
 */
public class OrderCreationAPI extends HttpClientConnection {

    /**
     * Method that processes the order by calling the OU service
     *
     * @param messageAttribute object header api
     * @param orderToCreateDTO Object representing
     * @param token            Authentication
     * @return response object TransactionDTO
     */
    @SneakyThrows
    public OrderToCreateDTO processOrders(
            Map<String, String> messageAttribute,
            OrderToCreateDTO orderToCreateDTO, String token
    ) {

        final String responseBodyString =
                connectionProcess(
                        messageAttribute,
                        orderToCreateDTO,
                        ROUTING_ATTRIBUTE_RESULT,
                        token,
                        URL_STACK_SECOND
                );

        LogsUtil.info("responseBodyString {} ", responseBodyString);

        return ObjectMapperSingletonUtil.jsonToClass(responseBodyString, OrderToCreateDTO.class);
    }

}
