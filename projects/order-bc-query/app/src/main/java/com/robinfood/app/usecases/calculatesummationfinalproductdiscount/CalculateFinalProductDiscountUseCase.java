package com.robinfood.app.usecases.calculatesummationfinalproductdiscount;

import com.robinfood.core.dtos.request.order.FinalProductDTO;
import com.robinfood.core.dtos.request.order.FinalProductDiscountDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_DOUBLE_EMPTY_VALUE;

/**
 * Implementation of ICalculateSummationFinalProductDiscountUseCase
 */
@Component
@Slf4j
public class CalculateFinalProductDiscountUseCase implements ICalculateFinalProductDiscountUseCase {

    @Override
    public Double invoke(FinalProductDTO finalProductDTO){
        log.info("calculating product discounts by id: [{}]", finalProductDTO.getId());

        Double totalDiscounts = finalProductDTO.getDiscounts().stream()
                .map(FinalProductDiscountDTO::getValue)
                .reduce(DEFAULT_DOUBLE_EMPTY_VALUE, Double::sum);

        return totalDiscounts * finalProductDTO.getQuantity();
    }
}
