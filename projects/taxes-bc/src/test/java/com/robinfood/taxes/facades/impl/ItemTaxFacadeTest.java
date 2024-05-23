package com.robinfood.taxes.facades.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.robinfood.taxes.domain.CalculatedTax;
import com.robinfood.taxes.domain.TaxableItem;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.services.RuleEngineService;
import com.robinfood.taxes.services.TaxCalculatorService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ItemTaxFacadeTest {

    @InjectMocks
    private TaxableItemFacadeImpl itemTaxFacade;

    @Mock
    private RuleEngineService ruleEngineService;

    @Mock
    private TaxCalculatorService taxCalculatorService;

    @Test
    void test_ItemTaxFacade_Should_ListItemTax_When_ItemHaveTaxes() throws BusinessRuleException {

        when(ruleEngineService.retrieveTaxes(anyList(), anyMap())).thenReturn(getTaxableProduct());
        when(taxCalculatorService.calculateForProducts(anyList(), anyMap()))
            .thenReturn(getTaxableProduct());

        assertAll(() -> itemTaxFacade.findAndCalculate(anyList(), anyMap()));

        verify(ruleEngineService, times(1)).retrieveTaxes(anyList(), anyMap());
        verify(taxCalculatorService, times(1)).calculateForProducts(anyList(), anyMap());
    }

    private List<TaxableItem> getTaxableProduct() {
        List<TaxableItem> taxableProductList = new ArrayList<>();
        TaxableItem taxableProduct = TaxableItem.builder()
            .discount(new BigDecimal(100))
            .price(new BigDecimal(8900))
            .quantity(1)
            .productTypeId(1L)
            .articleId(1L)
            .totalTax(new BigDecimal(55))
            .taxes(getCalculateTax())
            .build();
        taxableProductList.add(taxableProduct);
        return taxableProductList;
    }

    private List<CalculatedTax> getCalculateTax() {
        List<CalculatedTax> calculatedTaxList = new ArrayList<>();
        CalculatedTax calculatedTax = CalculatedTax.builder()
            .name("")
            .taxId(1L)
            .taxRate(new BigDecimal(9))
            .taxId(1L)
            .familyId(1L)
            .familyTypeId(1L)
            .sapId("asf")
            .build();
        calculatedTaxList.add(calculatedTax);
        return calculatedTaxList;
    }

}
