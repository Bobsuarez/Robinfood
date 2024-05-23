package com.robinfood.repository.paymentmethods;

import com.robinfood.core.dtos.PaymentMethodsFilterDTO;
import com.robinfood.core.enums.Result;

import java.util.List;

/**
 * Repository Payment methods
 */
public interface IPaymentMethodsRepository {

    /**
     * Get object with the list of payment methods
     *
     * @param token token auth service
     * @return list of payment methods filtered by status
     */
    Result<List<PaymentMethodsFilterDTO>> getDataPaymentMethods(
            String token
    );

}
