package com.robinfood.repository.connectorsimba;

import com.fasterxml.jackson.databind.JsonNode;
import com.robinfood.dtos.request.simba.SimbaContractDTO;
import com.robinfood.network.http.api.SimbaAPI;

public class ConnectorSimbaRepository implements IConnectorSimbaRepository {

    private final SimbaAPI simbaAPI;

    public ConnectorSimbaRepository() {
        this.simbaAPI = new SimbaAPI();
    }

    public ConnectorSimbaRepository(SimbaAPI simbaAPI) {
        this.simbaAPI = simbaAPI;
    }

    @Override
    public JsonNode invoke(SimbaContractDTO simbaContractDTO) {
        return simbaAPI.sendMessage(simbaContractDTO);
    }
}
