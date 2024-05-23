package com.robinfood.taxes.facades;

import com.robinfood.taxes.domain.Item;
import com.robinfood.taxes.domain.TaxableItem;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import java.util.List;
import java.util.Map;

public interface TaxableItemFacade {

    List<TaxableItem> findAndCalculate(List<Item> items, Map<String, Object> criteria)
        throws BusinessRuleException;
}
