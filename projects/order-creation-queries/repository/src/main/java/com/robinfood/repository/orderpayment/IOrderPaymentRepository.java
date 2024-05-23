package com.robinfood.repository.orderpayment;

import com.robinfood.core.dtos.orderpayment.DataOrderPaymentRequestDTO;
import com.robinfood.core.dtos.orderpayment.OrderPaymentDTO;
import com.robinfood.core.enums.Result;

import java.util.List;

/**
 * Repository for the methods of payment
 */
public interface IOrderPaymentRepository {

    /**
     * get the list of payment methods
     *
     * @param dataOrderPaymentRequestDTO information for the different filters
     * @param token token auth service
     * @return data of payment methods
     */
    Result<List<OrderPaymentDTO>> getDataOrderPayment(
            DataOrderPaymentRequestDTO dataOrderPaymentRequestDTO,
            String token
    );
}
