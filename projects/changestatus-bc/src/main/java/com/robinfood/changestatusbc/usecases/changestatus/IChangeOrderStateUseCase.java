package com.robinfood.changestatusbc.usecases.changestatus;

import com.robinfood.changestatusbc.dtos.ChangeOrderStatusDTO;
import com.robinfood.changestatusbc.dtos.WriteChangeStatusDTO;

/**
 *
 */
public interface IChangeOrderStateUseCase {

    /**
     * Change the State od an Order and send it to a queue of orders state change
     *
     * @param changeOrderStatusDTO Order tha wants to be change
     * @return WriteChangeStatusDTO
     */
    WriteChangeStatusDTO invoke(ChangeOrderStatusDTO changeOrderStatusDTO);
}
