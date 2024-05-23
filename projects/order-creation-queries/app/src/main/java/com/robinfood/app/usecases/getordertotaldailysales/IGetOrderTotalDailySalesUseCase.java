package com.robinfood.app.usecases.getordertotaldailysales;

import com.robinfood.core.dtos.OrderTotalDailySalesDTO;
import com.robinfood.core.enums.Result;

import java.time.LocalDate;
import java.util.List;

public interface IGetOrderTotalDailySalesUseCase {

    /**
     * get list of order daily sales
     * @param storeId store identifier
     * @param date date to consult information
     * @return list of order daily sales
     */
    Result<List<OrderTotalDailySalesDTO>> invoke(int storeId, LocalDate date);
}
