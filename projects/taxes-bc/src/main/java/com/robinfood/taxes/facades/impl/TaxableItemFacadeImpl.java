package com.robinfood.taxes.facades.impl;

import com.robinfood.taxes.annotations.BasicLog;
import com.robinfood.taxes.domain.Item;
import com.robinfood.taxes.domain.TaxableItem;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.facades.TaxableItemFacade;
import com.robinfood.taxes.services.RuleEngineService;
import com.robinfood.taxes.services.TaxCalculatorService;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TaxableItemFacadeImpl implements TaxableItemFacade {

    private final RuleEngineService ruleEngineService;
    private final TaxCalculatorService taxCalculatorService;

    public TaxableItemFacadeImpl(RuleEngineService ruleEngineService,
        TaxCalculatorService taxCalculatorService) {
        this.ruleEngineService = ruleEngineService;
        this.taxCalculatorService = taxCalculatorService;
    }

    @BasicLog
    @Override
    public List<TaxableItem> findAndCalculate(List<Item> items, Map<String, Object> criteria)
        throws BusinessRuleException {

        log.trace("Retrieve taxes for items: {} with criteria: {}", items, criteria);
        List<TaxableItem> taxableItems = ruleEngineService.retrieveTaxes(items, criteria);
        log.trace("Item taxes generated successfully. {}", taxableItems);

        log.trace("Calculation of total taxes for items: {} with criteria: {}", items, criteria);
        List<TaxableItem> calculateTaxes = taxCalculatorService
            .calculateForProducts(taxableItems, criteria);
        log.trace("Calculate tax item generated successfully. {}", calculateTaxes);

        return calculateTaxes;
    }
}
