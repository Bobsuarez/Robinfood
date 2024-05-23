package com.robinfood.repository.orderdaily;

import com.robinfood.core.dtos.OrderDailyDTO;
import com.robinfood.core.enums.Result;

import java.util.List;

/**
 * Get Orders of the day repository
 */
public interface IOrderDailyRepository {

    /**
     * Get Orders of the day by store
     *
     * @param storeId Store identifier
     * @param timeZone Client time zone
     * @param token Token auth service
     * @return List Order Daily
     */
    Result<List<OrderDailyDTO>> getOrderDaily(
            Long storeId,
            String timeZone,
            String token
    );
}
