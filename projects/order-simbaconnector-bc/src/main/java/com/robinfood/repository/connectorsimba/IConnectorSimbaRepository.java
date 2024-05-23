package com.robinfood.repository.connectorsimba;

import com.fasterxml.jackson.databind.JsonNode;
import com.robinfood.dtos.request.simba.SimbaContractDTO;

public interface IConnectorSimbaRepository {

    JsonNode invoke(SimbaContractDTO simbaContractDTO);
}
