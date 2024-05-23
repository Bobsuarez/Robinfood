package com.robinfood.app.usecases.getpaymentmethod;

import com.robinfood.core.dtos.transactionrequestdto.PaymentMethodDTO;
import java.util.List;

/**
 * The interface that you must implement to obtain the foodcoins payment method
 */
public interface IGetPaymentMethodFoodCoinsUseCase {

    /**
     * Executes the corresponding validations to get foodcoins the payment method
     * @param paymentMethodDTOS the payment methods
     * @return The result of an only payment method
     */
    PaymentMethodDTO invoke(List<PaymentMethodDTO> paymentMethodDTOS);
}
