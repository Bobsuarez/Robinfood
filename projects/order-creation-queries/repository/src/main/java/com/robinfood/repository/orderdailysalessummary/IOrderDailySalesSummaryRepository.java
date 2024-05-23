package com.robinfood.repository.orderdailysalessummary;

import com.robinfood.core.dtos.OrderDailySaleSummaryDTO;
import com.robinfood.core.enums.Result;

import java.time.LocalDate;

/**
 * Repository for order detail related data
 */
public interface IOrderDailySalesSummaryRepository {

    /**
     * Get daily sales summary by store and specific date
     *
     * @param token the authentication token
     * @param storeId   the store id to order
     * @param createdAt the date of creation to order
     * @return an object of OrderDailySaleSummaryDTO
     * @author Jose Mario Londoño - CKS
     */
    Result<OrderDailySaleSummaryDTO>  getOrderDailySalesSummary(String token, Long storeId, LocalDate createdAt);

}
