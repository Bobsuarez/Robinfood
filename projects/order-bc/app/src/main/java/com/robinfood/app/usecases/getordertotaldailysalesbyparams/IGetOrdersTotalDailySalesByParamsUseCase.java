package com.robinfood.app.usecases.getordertotaldailysalesbyparams;

import com.robinfood.core.dtos.GetOrderTotalDailySalesDTO;

import java.time.LocalDate;
import java.util.List;

/**
 * Use case that returns the total daily sales.
 */
public interface IGetOrdersTotalDailySalesByParamsUseCase {

    /**
     * Retrieve the total daily sales by store and specific date
     * @author Marcos Manotas - CKS
     * @param storeId the store id to order
     * @param createdAt the create at to order
     * @return List<GetOrderTotalDailySalesDTO>
     */
    List<GetOrderTotalDailySalesDTO> invoke(
        Long storeId,
        LocalDate createdAt);
}
