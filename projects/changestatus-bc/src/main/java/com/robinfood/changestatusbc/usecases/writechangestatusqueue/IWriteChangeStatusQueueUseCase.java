package com.robinfood.changestatusbc.usecases.writechangestatusqueue;

import com.robinfood.changestatusbc.dtos.ChangeOrderStatusDTO;
import com.robinfood.changestatusbc.dtos.WriteChangeStatusDTO;

/**
 *
 */
public interface IWriteChangeStatusQueueUseCase {

    /**
     * Writes in the queue the status change of the order
     *
     * @param nextState new order status
     * @return Write ChangeStatus
     */
    WriteChangeStatusDTO invoke(ChangeOrderStatusDTO nextState);
}
