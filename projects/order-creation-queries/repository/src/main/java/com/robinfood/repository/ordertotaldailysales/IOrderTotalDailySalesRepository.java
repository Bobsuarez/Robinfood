package com.robinfood.repository.ordertotaldailysales;

import com.robinfood.core.dtos.OrderTotalDailySalesDTO;
import com.robinfood.core.enums.Result;

import java.time.LocalDate;
import java.util.List;

public interface IOrderTotalDailySalesRepository {

    /**
     * Get list order total daily sales
     * @param token token auth service
     * @param storeId store identifier
     * @param date date to consult information
     * @return list OrderTotalDailySalesDTO
     */
    Result<List<OrderTotalDailySalesDTO>> getOrderTotalDailySales(String token, int storeId, LocalDate date);
}
