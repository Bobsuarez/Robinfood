package com.robinfood.app.usecases.getsumproductsprice;

import com.robinfood.core.dtos.request.order.FinalProductDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Implementation of IGetSumProductPriceUseCase
 */
@Component
public class GetSumProductPriceUseCase implements IGetSumProductPriceUseCase {
    @Override
    public BigDecimal invoke(List<FinalProductDTO> finalProductDTOS) {
        return finalProductDTOS.stream().map(FinalProductDTO::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
