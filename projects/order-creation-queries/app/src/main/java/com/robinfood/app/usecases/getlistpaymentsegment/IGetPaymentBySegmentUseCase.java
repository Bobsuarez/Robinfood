package com.robinfood.app.usecases.getlistpaymentsegment;

import com.robinfood.core.dtos.PaymentMethodsFilterDTO;
import com.robinfood.core.dtos.report.salebysegment.PaymentMethodSegmentDTO;
import com.robinfood.core.dtos.report.salebysegment.response.PaymentMethodsDTOResponse;

import java.util.List;

/**
 * Use case that returns the list of company by app
 */
public interface IGetPaymentBySegmentUseCase {

    /**
     * retrieve the stores according to the entered criteria
     *
     * @return object with the data
     */
    PaymentMethodsDTOResponse invoke(
            String currencyType,
            List<PaymentMethodSegmentDTO> channelSegmentDTOS,
            List<PaymentMethodsFilterDTO> paymentDTOList);
}
