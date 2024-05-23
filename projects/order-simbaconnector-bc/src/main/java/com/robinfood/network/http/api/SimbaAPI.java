package com.robinfood.network.http.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.robinfood.dtos.request.simba.SimbaContractDTO;
import com.robinfood.network.http.connection.HttpClientConnection;
import lombok.SneakyThrows;

import static com.robinfood.constants.GeneralConstants.API_SIMBA;
import static com.robinfood.constants.GeneralConstants.BASE_SIMBA_URL;

public class SimbaAPI extends HttpClientConnection {

    @SneakyThrows
    public JsonNode sendMessage(SimbaContractDTO simbaContractDTO) {

        final String URL = BASE_SIMBA_URL + API_SIMBA;

        return connectionProcess(simbaContractDTO, URL);
    }
}
