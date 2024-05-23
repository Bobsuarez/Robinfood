package com.robinfood.app.usecases.getorderpayment;

import com.robinfood.core.dtos.orderpayment.DataOrderPaymentRequestDTO;
import com.robinfood.core.dtos.orderpayment.OrderPaymentDTO;
import com.robinfood.core.enums.Result;

import java.util.List;

/**
 * Use case that returns the list of payment methods that made the orders
 */
public interface IGetOrderPaymentUseCase {

    /**
     * retrieve the orders according to the entered criteria
     *
     * @param dataOrderPaymentRequestDTO Dto that contains the data entered
     * @return object with the data pos resolution
     */
    Result<List<OrderPaymentDTO>> invoke(DataOrderPaymentRequestDTO dataOrderPaymentRequestDTO);
}
