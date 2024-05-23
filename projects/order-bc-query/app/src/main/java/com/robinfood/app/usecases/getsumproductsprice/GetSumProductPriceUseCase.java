package com.robinfood.app.usecases.getsumproductsprice;

import com.robinfood.core.dtos.request.order.FinalProductDTO;
import kotlin.collections.CollectionsKt;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implementation of IGetSumProductPriceUseCase
 */
@Component
public class GetSumProductPriceUseCase implements IGetSumProductPriceUseCase {
    @Override
    public Double invoke(List<FinalProductDTO> finalProductDTOS) {
        return CollectionsKt.sumByDouble(finalProductDTOS, FinalProductDTO::getValue);
    }
}
