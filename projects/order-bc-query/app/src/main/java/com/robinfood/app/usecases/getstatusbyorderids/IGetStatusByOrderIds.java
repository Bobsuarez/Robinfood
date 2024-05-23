package com.robinfood.app.usecases.getstatusbyorderids;

import com.robinfood.core.dtos.OrderStatusDTO;

import java.util.List;

/**
 * Use case that returns order statuses from some ids
 */
public interface IGetStatusByOrderIds {

    /**
     * Gets a list of statuses by their ids
     * @param statusIds the list of status ids
     * @return a list of statuses by their ids
     */
    List<OrderStatusDTO> invoke(List<Long> statusIds);
}
