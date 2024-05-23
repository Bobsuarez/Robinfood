package com.robinfood.network.http.api;

import com.robinfood.constants.GeneralConstants;
import com.robinfood.dtos.sendordertosimba.request.TransactionRequestDTO;
import com.robinfood.network.http.connection.HttpClientConnection;
import lombok.SneakyThrows;

import static com.robinfood.constants.GeneralConstants.ATTRIBUTE_RESULT_DEFAULT;

/**
 * class connecting by simba api
 * @author Eduardo suarez
 */
public class ConnectorSimbaAPI extends HttpClientConnection {

    /**
     * Method that sends the message to simba api
     *
     * @param transactionRequestDTO the transaction request object
     * @param token the token to send message
     * @return the message response object
     */
    @SneakyThrows
    public String sendMessage(TransactionRequestDTO transactionRequestDTO, String token) {

        final String URL = GeneralConstants.URL_CONNECTOR_SIMBA + "v1/send-electronic-bill";

        return connectionProcess(ATTRIBUTE_RESULT_DEFAULT, transactionRequestDTO, token, URL);
    }
}
