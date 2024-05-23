package com.robinfood.app.usecases.getsumdiscountprice;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_DOUBLE_VALUE;

import com.robinfood.core.dtos.request.order.FinalProductDTO;
import com.robinfood.core.dtos.request.order.FinalProductDiscountDTO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implementation of IGetSumDiscountPriceUseCase
 */
@Component
public class GetSumDiscountPriceUseCase implements IGetSumDiscountPriceUseCase {

    @Override
    public Double invoke(List<FinalProductDTO> finalProductDTOS) {
        return finalProductDTOS.stream()
            .map(this::getDiscountsByProduct)
            .reduce(DEFAULT_DOUBLE_VALUE, Double::sum);
    }

    private Double getDiscountsByProduct(FinalProductDTO finalProductDTO) {
        Double discounts = finalProductDTO.getDiscounts().stream()
            .map(FinalProductDiscountDTO::getValue)
            .reduce(DEFAULT_DOUBLE_VALUE, Double::sum);

        return discounts * finalProductDTO.getQuantity();
    }
}
