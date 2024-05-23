package com.robinfood.app.usecases.getlistpaymentmethods;

import com.robinfood.core.dtos.PaymentMethodEntityDTO;

import java.util.List;

/**
 * get the list of payment methods
 */
public interface IGetListPaymentMethodsUseCase {

    List<PaymentMethodEntityDTO> invoke();
}
