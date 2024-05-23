package com.robinfood.app.usecases.getlistpaymentmethods;

import com.robinfood.core.dtos.PaymentMethodsFilterDTO;
import com.robinfood.core.enums.Result;

import java.util.List;

public interface IGetListPaymentMethodsUseCase {

    /**
     * retrieve the orders according to the entered criteria
     *
     * @return the list of objects containing payment methods
     */
    Result<List<PaymentMethodsFilterDTO>> invoke();

}
