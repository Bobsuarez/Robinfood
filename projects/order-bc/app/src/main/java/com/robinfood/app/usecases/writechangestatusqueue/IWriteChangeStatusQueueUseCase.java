package com.robinfood.app.usecases.writechangestatusqueue;

import com.robinfood.core.dtos.queue.ChangeOrderStatusDTO;
import com.robinfood.core.dtos.queue.WriteChangeStatusDTO;

public interface IWriteChangeStatusQueueUseCase {
    /**
     * writes in the queue the status change of the order
     *
     * @param nextState new order status
     */
    WriteChangeStatusDTO invoke(ChangeOrderStatusDTO nextState);
}
