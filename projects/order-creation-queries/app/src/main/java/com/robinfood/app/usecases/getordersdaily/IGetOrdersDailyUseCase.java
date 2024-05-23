package com.robinfood.app.usecases.getordersdaily;

import com.robinfood.core.dtos.OrderDailyDTO;
import com.robinfood.core.enums.Result;

import java.util.List;

/**
 * Use case that returns the orders of the day.
 */
public interface IGetOrdersDailyUseCase {

    /**
     * Get orders of the day depending on the time zone.
     *
     * @param storeId Store identifier
     * @param timeZone Client time zone
     * @return Daily Order List ready to invoice
     */
    Result<List<OrderDailyDTO>> invoke(Long storeId, String timeZone);
}
