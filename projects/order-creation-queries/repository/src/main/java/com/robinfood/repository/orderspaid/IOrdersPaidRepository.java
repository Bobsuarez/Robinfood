package com.robinfood.repository.orderspaid;

import com.robinfood.core.dtos.orderspaid.DataOrdersPaidRequestDTO;
import com.robinfood.core.dtos.orderspaid.GetOrdersPaidDTO;
import com.robinfood.core.enums.Result;

public interface IOrdersPaidRepository {

    /**
     * get the list of orders paid by filter
     *
     * @param dataOrdersPaidRequestDTO information for the different filters
     * @param token token auth service
     * @return data of orders paid
     */
    Result<GetOrdersPaidDTO> getDataOrdersPaid(
            DataOrdersPaidRequestDTO dataOrdersPaidRequestDTO,
            String token
    );
}
