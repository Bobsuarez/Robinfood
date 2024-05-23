package com.robinfood.app.usecases.calculatesummationfinalproducttax;

import com.robinfood.core.dtos.request.order.FinalProductDTO;
import com.robinfood.core.dtos.request.order.FinalProductTaxDTO;
import kotlin.collections.CollectionsKt;
import org.springframework.stereotype.Component;

/**
 * Implementation of ICalculateSummationFinalProductTaxUseCase
 */
@Component
public class CalculateSummationFinalProductTaxUseCase implements ICalculateSummationFinalProductTaxUseCase {

    @Override
    public Double invoke(FinalProductDTO finalProduct){
        return CollectionsKt.sumByDouble(finalProduct.getTaxes(), FinalProductTaxDTO::getTaxPrice);
    }
}
