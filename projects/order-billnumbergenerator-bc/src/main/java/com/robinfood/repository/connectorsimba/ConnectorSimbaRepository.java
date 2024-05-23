package com.robinfood.repository.connectorsimba;

import com.robinfood.dtos.sendordertosimba.request.TransactionRequestDTO;
import com.robinfood.network.http.api.ConnectorSimbaAPI;

public class ConnectorSimbaRepository implements IConnectorSimbaRepository {

    private final ConnectorSimbaAPI connectorSimbaAPI;

    public ConnectorSimbaRepository() {
        this.connectorSimbaAPI = new ConnectorSimbaAPI();
    }

    public ConnectorSimbaRepository(ConnectorSimbaAPI connectorSimbaAPI) {
        this.connectorSimbaAPI = connectorSimbaAPI;
    }

    @Override
    public String invoke(TransactionRequestDTO transactionRequestDTO, String token) {
        return connectorSimbaAPI.sendMessage(transactionRequestDTO, token);
    }
}
