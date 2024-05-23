package com.robinfood.app.usecases.getsumpaymentmethos;

import com.robinfood.core.dtos.request.transaction.RequestPaymentMethodDTO;
import java.util.List;

/**
 * Use case that gets sum of the payment methods
 */
public interface IGetSumPaymentMethodsUseCase {

    /**
     * Get sum payment methods
     *
     * @param requestPaymentMethodDTO
     * @return Sum payment methods
     */
    Double invoke(List<RequestPaymentMethodDTO> requestPaymentMethodDTO);
}
