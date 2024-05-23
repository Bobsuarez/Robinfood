package com.robinfood.taxes.services;

import com.robinfood.taxes.domain.Item;
import com.robinfood.taxes.domain.TaxableItem;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import java.util.List;
import java.util.Map;

public interface RuleEngineService {

    List<TaxableItem> retrieveTaxes(List<Item> item, Map<String, Object> criteria)
        throws BusinessRuleException;
}
