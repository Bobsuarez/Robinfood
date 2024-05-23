package com.robinfood.changestatusbc.repositories.queue;

import com.robinfood.changestatusbc.dtos.WriteChangeStatusDTO;

public interface IProducerOrderRepository {

    void sendChangeStatusMessage(WriteChangeStatusDTO writeChangeStatusDTO);
}
