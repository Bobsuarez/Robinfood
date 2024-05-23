package com.robinfood.app.usecases.getsumpaymentmethos;

import com.robinfood.core.dtos.request.transaction.RequestPaymentMethodDTO;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_DOUBLE_VALUE;

/**
 * Implementation of IGetSumProductPriceUseCase
 */
@Component
public class GetSumPaymentMethodsUseCase implements IGetSumPaymentMethodsUseCase {

    @Override
    public Double invoke(List<RequestPaymentMethodDTO> requestPaymentMethodDTO) {
        return requestPaymentMethodDTO.stream()
                .map(RequestPaymentMethodDTO::getValue)
                .reduce(DEFAULT_DOUBLE_VALUE, Double::sum);
    }
}
