package com.robinfood.app.usecases.getorderspaid;

import com.robinfood.core.dtos.orderspaid.GetOrdersPaidDTO;
import com.robinfood.core.dtos.orderspaid.OrdersPaidResponseDTO;

/**
 * Use case that returns the list of orders paid response
 */
public interface IOrderPaidResponseUseCase {

    /**
     * retrieve the orders paid response according to the entered orders paid
     *
     * @param getOrdersPaidDTO Dto that contains the data
     * @return OrdersPaidResponseDTO with the data orders paid response
     */
    OrdersPaidResponseDTO invoke(GetOrdersPaidDTO getOrdersPaidDTO, String token);
}
