package com.robinfood.app.usecases.getorderspaid;

import com.robinfood.core.dtos.orderspaid.DataOrdersPaidRequestDTO;
import com.robinfood.core.dtos.orderspaid.OrdersPaidResponseDTO;
import com.robinfood.core.enums.Result;

/**
 * Use case that returns the list of orders paid
 */
public interface IGetOrdersPaidUseCase {

    /**
     * retrieve the orders paid according to the entered criteria
     *
     * @param dataOrdersPaidRequestDTO Dto that contains the data entered
     * @return OrdersPaidResponseDTO with the data orders paid
     */
    Result<OrdersPaidResponseDTO> invoke(DataOrdersPaidRequestDTO dataOrdersPaidRequestDTO);
}
