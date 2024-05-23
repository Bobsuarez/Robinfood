package com.robinfood.app.usecases.changestatusorders;

import com.robinfood.core.dtos.queue.ChangeOrderStatusDTO;
import com.robinfood.core.dtos.queue.WriteChangeStatusDTO;

public interface IChangeOrderStateUseCase {

    /**
     * Change the State od an Order and send it to a queue of orders state change
     * @param order Order to wants to be change
     */
    WriteChangeStatusDTO invoke(ChangeOrderStatusDTO order);
}
