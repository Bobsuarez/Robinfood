package com.robinfood.taxes.services;

import com.robinfood.taxes.domain.TaxableItem;
import java.util.List;
import java.util.Map;

public interface TaxCalculatorService {

    List<TaxableItem> calculateForProducts(List<TaxableItem> taxableProductList,
        Map<String, Object> additionalVariables);
}
