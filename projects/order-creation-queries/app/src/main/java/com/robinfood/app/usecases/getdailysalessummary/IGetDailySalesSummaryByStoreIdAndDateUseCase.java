package com.robinfood.app.usecases.getdailysalessummary;

import com.robinfood.core.dtos.OrderDailySaleSummaryDTO;
import com.robinfood.core.enums.Result;

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
    Result<OrderDailySaleSummaryDTO> invoke(Long storeId, LocalDate createdAt);
}
