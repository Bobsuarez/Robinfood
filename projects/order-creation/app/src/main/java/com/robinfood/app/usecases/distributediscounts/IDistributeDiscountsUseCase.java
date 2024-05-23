package com.robinfood.app.usecases.distributediscounts;

import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;

import java.math.BigDecimal;

/**
 * Use case that distributes discounts by percentage
 */
public interface IDistributeDiscountsUseCase {

    /**
     * Distributes discounts by percentage
     * @param orderDTO order request
     * @param totals discount total
     * @param discountId discount id
     */
    void invoke(
            OrderDTO orderDTO,
            BigDecimal totals,
            Long discountId,
            TransactionRequestDTO transactionRequestDTO
    );
}
