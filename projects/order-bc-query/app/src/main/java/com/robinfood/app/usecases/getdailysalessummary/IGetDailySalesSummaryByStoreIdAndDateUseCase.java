package com.robinfood.app.usecases.getdailysalessummary;

import com.robinfood.core.dtos.OrderDailySaleSummaryDTO;

import java.time.LocalDate;

public interface IGetDailySalesSummaryByStoreIdAndDateUseCase {

    /**
     * Get daily sales summary by store and specific date
     *
     * @param storeId   the store id to order
     * @param createdAt the date of creation to order
     * @return an object of OrderDailySaleSummaryDTO
     * @author Jose Mario Londoño - CKS
     */
    OrderDailySaleSummaryDTO invoke(Long storeId, LocalDate createdAt);
}
