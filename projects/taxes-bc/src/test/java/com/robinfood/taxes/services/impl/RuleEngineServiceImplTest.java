package com.robinfood.taxes.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.robinfood.taxes.domain.Item;
import com.robinfood.taxes.domain.TaxableItem;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.ArticleFamily;
import com.robinfood.taxes.models.Family;
import com.robinfood.taxes.models.Tax;
import com.robinfood.taxes.models.FamilyType;
import com.robinfood.taxes.models.RuleType;
import com.robinfood.taxes.models.RuleVariable;
import com.robinfood.taxes.models.TaxType;
import com.robinfood.taxes.models.TaxRule;
import com.robinfood.taxes.repositories.ArticleFamilyRepository;
import com.robinfood.taxes.repositories.RuleVariableRepository;
import com.robinfood.taxes.repositories.TaxRepository;
import com.robinfood.taxes.repositories.TaxRuleRepository;
import com.robinfood.taxes.rules.RuleFactory;
import com.robinfood.taxes.rules.AbstractRule;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class RuleEngineServiceImplTest {

    @InjectMocks
    private RuleEngineServiceImpl ruleEngineService;

    @Mock
    private RuleVariableRepository ruleVariableRepository;

    @Mock
    private TaxRuleRepository taxRuleRepository;

    @Mock
    private TaxRepository taxRepository;

    @Mock
    private ArticleFamilyRepository articleFamilyRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private RuleFactory ruleFactory;

    @Mock
    private AbstractRule rules;


    @Test
    void test_listItemTax_Should_ListTaxDetail_When_taxNoApplyRules()
        throws BusinessRuleException {

        //Data
        Long idTax = 1L;
        List<Item> itemList = Collections.singletonList(getItem());

        ArticleFamily articleFamily = ArticleFamily.builder()
            .family(getFamily(1))
            .articleId(1L)
            .productTypeId(1L)
            .build();
        List<Tax> taxList = new ArrayList<>();
        taxList.add(getTax(idTax, false, 1));

        //When
        when(articleFamilyRepository
            .findByProductTypeIdAndArticleIdAndStatusAndDeletedAtIsNull(anyLong(), anyLong(),
                anyInt())).thenReturn(Collections.singletonList(articleFamily));
        when(taxRepository.findByFamilyInAndStatusAndDeleteIsNull(anyList(), anyInt()))
            .thenReturn(taxList);

        //Call
        List<TaxableItem> itemDetail = ruleEngineService
            .retrieveTaxes(itemList, new HashMap<>());

        verify(articleFamilyRepository, times(1))
            .findByProductTypeIdAndArticleIdAndStatusAndDeletedAtIsNull(anyLong(), anyLong(),
                anyInt());
        verify(taxRepository, times(1))
            .findByFamilyInAndStatusAndDeleteIsNull(anyList(), anyInt());

        assertEquals(idTax, itemDetail.get(0).getTaxes().get(0).getTaxId());
    }

    @Test
    void test_listItemTax_Should_ListTaxDetail_When_ValidateRuleTrue()
        throws BusinessRuleException {

        //Data
        Long idTax = 2L;
        List<Item> itemList = Arrays.asList(getItem());

        ArticleFamily articleFamily = ArticleFamily.builder()
            .family(getFamily(1))
            .articleId(1L)
            .productTypeId(1L)
            .build();
        List<Tax> taxList = new ArrayList<>();
        taxList.add(getTax(idTax, true, 1));

        List<TaxRule> taxRuleList = Arrays
            .asList(getTaxRule(1L, "Equal", 1L, 2L, "Number", "1", "Number", "1", null));

        //When
        when(articleFamilyRepository
            .findByProductTypeIdAndArticleIdAndStatusAndDeletedAtIsNull(anyLong(), anyLong(),
                anyInt())).thenReturn(Arrays.asList(articleFamily));
        when(taxRepository.findByFamilyInAndStatusAndDeleteIsNull(anyList(), anyInt()))
            .thenReturn(taxList);
        when(taxRuleRepository
            .findByTaxIdAndStatusAndDeletedAtIsNull(anyLong(), anyInt()))
            .thenReturn(taxRuleList);
        when(ruleVariableRepository
            .findByIdAndDeletedAtIsNull(anyLong()))
            .thenReturn(taxRuleList.get(0).getLeftVariable());
        when(ruleFactory.createRule(anyString(), any(), any())).thenReturn(rules);
        when(rules.evaluate()).thenReturn(true);

        //Call
        List<TaxableItem> taxableProductList = ruleEngineService
            .retrieveTaxes(itemList, new HashMap<>());

        verify(articleFamilyRepository, times(1))
            .findByProductTypeIdAndArticleIdAndStatusAndDeletedAtIsNull(anyLong(), anyLong(),
                anyInt());
        verify(taxRepository, times(1))
            .findByFamilyInAndStatusAndDeleteIsNull(anyList(), anyInt());
        verify(taxRuleRepository, times(1))
            .findByTaxIdAndStatusAndDeletedAtIsNull(anyLong(), anyInt());
        verify(ruleVariableRepository, times(2))
            .findByIdAndDeletedAtIsNull(anyLong());

        assertEquals(idTax, taxableProductList.get(0).getTaxes().get(0).getId());
    }

    @Test
    void test_listItemTax_Should_ListTaxDetail_When_ValidateRuleFalse()
        throws BusinessRuleException {

        //Data
        Long idTax = 2L;
        List<Item> itemList = Arrays.asList(getItem());

        ArticleFamily articleFamily = ArticleFamily.builder()
            .family(getFamily(1))
            .articleId(1L)
            .productTypeId(1L)
            .build();

        List<Tax> taxList = new ArrayList<>();
        taxList.add(getTax(idTax, true, 1));

        List<TaxRule> taxRuleList = Arrays
            .asList(getTaxRule(1L, "Equal", 1L, 2L, "Number", null, "Number", "1", null));

        //When
        when(articleFamilyRepository
            .findByProductTypeIdAndArticleIdAndStatusAndDeletedAtIsNull(anyLong(), anyLong(),
                anyInt())).thenReturn(Arrays.asList(articleFamily));
        when(taxRepository.findByFamilyInAndStatusAndDeleteIsNull(anyList(), anyInt()))
            .thenReturn(taxList);
        when(taxRuleRepository
            .findByTaxIdAndStatusAndDeletedAtIsNull(anyLong(), anyInt()))
            .thenReturn(taxRuleList);
        when(ruleVariableRepository
            .findByIdAndDeletedAtIsNull(anyLong()))
            .thenReturn(taxRuleList.get(0).getLeftVariable());
        when(ruleVariableRepository
            .findByIdAndDeletedAtIsNull(anyLong()))
            .thenReturn(taxRuleList.get(0).getRightVariable());
        when(ruleFactory.createRule(anyString(), any(), any())).thenReturn(rules);
        when(rules.evaluate()).thenReturn(false);

        //Call
        List<TaxableItem> taxableProductList = ruleEngineService
            .retrieveTaxes(itemList, new HashMap<>());

        verify(articleFamilyRepository, times(1))
            .findByProductTypeIdAndArticleIdAndStatusAndDeletedAtIsNull(anyLong(), anyLong(),
                anyInt());
        verify(taxRepository, times(1))
            .findByFamilyInAndStatusAndDeleteIsNull(anyList(), anyInt());
        verify(taxRuleRepository, times(1))
            .findByTaxIdAndStatusAndDeletedAtIsNull(anyLong(), anyInt());
        verify(ruleVariableRepository, times(2))
            .findByIdAndDeletedAtIsNull(anyLong());
    }

    @Test
    void test_listItemTax_Should_ListTaxDetail_When_TypeVariableIsPath()
        throws BusinessRuleException {

        //Data
        Long idTax = 2L;
        List<Item> itemList = Arrays.asList(getItem());
        Map<String, Object> criteria = new HashMap<>();
        criteria.put("country", "1");
        criteria.put("location", "1");

        ArticleFamily articleFamily = ArticleFamily.builder()
            .family(getFamily(1))
            .articleId(1L)
            .productTypeId(1L)
            .build();

        List<Tax> taxList = new ArrayList<>();
        taxList.add(getTax(idTax, true, 1));

        List<TaxRule> taxRuleList = Arrays
            .asList(
                getTaxRule(1L, "Equal", 1L, 2L, "Number", null, "Number", "location", "country"));

        //When
        when(articleFamilyRepository
            .findByProductTypeIdAndArticleIdAndStatusAndDeletedAtIsNull(anyLong(), anyLong(),
                anyInt())).thenReturn(Arrays.asList(articleFamily));
        when(taxRepository.findByFamilyInAndStatusAndDeleteIsNull(anyList(), anyInt()))
            .thenReturn(taxList);
        when(taxRuleRepository
            .findByTaxIdAndStatusAndDeletedAtIsNull(anyLong(), anyInt()))
            .thenReturn(taxRuleList);
        when(ruleVariableRepository
            .findByIdAndDeletedAtIsNull(anyLong()))
            .thenReturn(taxRuleList.get(0).getLeftVariable());
        when(ruleFactory.createRule(anyString(), any(), any())).thenReturn(rules);
        when(rules.evaluate()).thenReturn(false);

        //Call
        List<TaxableItem> taxableProductList = ruleEngineService
            .retrieveTaxes(itemList, criteria);

        verify(articleFamilyRepository, times(1))
            .findByProductTypeIdAndArticleIdAndStatusAndDeletedAtIsNull(anyLong(), anyLong(),
                anyInt());
        verify(taxRepository, times(1))
            .findByFamilyInAndStatusAndDeleteIsNull(anyList(), anyInt());
        verify(taxRuleRepository, times(1))
            .findByTaxIdAndStatusAndDeletedAtIsNull(anyLong(), anyInt());
        verify(ruleVariableRepository, times(2))
            .findByIdAndDeletedAtIsNull(anyLong());
    }

    @Test
    void test_listItemTax_Should_ListTaxDetail_When_PathsGreaterTwo()
        throws BusinessRuleException {

        //Data
        Long idTax = 2L;
        List<Item> itemList = Arrays.asList(getItem());

        Map<String, Object> map = new HashMap<>();
        map.put("locationId", "1");

        Map<String, Object> criteria = new HashMap<>();
        criteria.put("country", map);

        ArticleFamily articleFamily = ArticleFamily.builder()
            .family(getFamily(1))
            .articleId(1L)
            .productTypeId(1L)
            .build();

        List<Tax> taxList = new ArrayList<>();
        taxList.add(getTax(idTax, true, 1));

        List<TaxRule> taxRuleList = Arrays
            .asList(getTaxRule(1L, "Equal", 1L, 2L, "Number", null, "Number",
                "country.locationId", "country.locationId"));

        //When
        when(articleFamilyRepository
            .findByProductTypeIdAndArticleIdAndStatusAndDeletedAtIsNull(anyLong(), anyLong(),
                anyInt())).thenReturn(Arrays.asList(articleFamily));
        when(taxRepository.findByFamilyInAndStatusAndDeleteIsNull(anyList(), anyInt()))
            .thenReturn(taxList);
        when(taxRuleRepository
            .findByTaxIdAndStatusAndDeletedAtIsNull(anyLong(), anyInt()))
            .thenReturn(taxRuleList);
        when(ruleVariableRepository
            .findByIdAndDeletedAtIsNull(anyLong()))
            .thenReturn(taxRuleList.get(0).getRightVariable());
        when(ruleFactory.createRule(anyString(), any(), any())).thenReturn(rules);
        when(rules.evaluate()).thenReturn(false);

        //Call
        List<TaxableItem> taxableProductList = ruleEngineService
            .retrieveTaxes(itemList, criteria);

        verify(articleFamilyRepository, times(1))
            .findByProductTypeIdAndArticleIdAndStatusAndDeletedAtIsNull(anyLong(), anyLong(),
                anyInt());
        verify(taxRepository, times(1))
            .findByFamilyInAndStatusAndDeleteIsNull(anyList(), anyInt());
        verify(taxRuleRepository, times(1))
            .findByTaxIdAndStatusAndDeletedAtIsNull(anyLong(), anyInt());
        verify(ruleVariableRepository, times(2))
            .findByIdAndDeletedAtIsNull(anyLong());
    }

    @Test
    void test_listItemTax_Should_ThrowIllegalArgumentException_When_PathNotMatch()
        throws BusinessRuleException {

        //Data
        Long idTax = 2L;
        List<Item> itemList = Arrays.asList(getItem());

        Map<String, Object> criteria = new HashMap<>();
        criteria.put("countryId", "1");

        ArticleFamily articleFamily = ArticleFamily.builder()
            .family(getFamily(1))
            .articleId(1L)
            .productTypeId(1L)
            .build();

        List<Tax> taxList = new ArrayList<>();
        taxList.add(getTax(idTax, true, 1));

        List<TaxRule> taxRuleList = Arrays
            .asList(
                getTaxRule(1L, "Equal", 1L, 2L, "Number", null, "Number", "country", "country"));

        //When
        when(articleFamilyRepository
            .findByProductTypeIdAndArticleIdAndStatusAndDeletedAtIsNull(anyLong(), anyLong(),
                anyInt())).thenReturn(Arrays.asList(articleFamily));
        when(taxRepository.findByFamilyInAndStatusAndDeleteIsNull(anyList(), anyInt()))
            .thenReturn(taxList);
        when(taxRuleRepository
            .findByTaxIdAndStatusAndDeletedAtIsNull(anyLong(), anyInt()))
            .thenReturn(taxRuleList);
        when(ruleVariableRepository
            .findByIdAndDeletedAtIsNull(anyLong()))
            .thenReturn(taxRuleList.get(0).getRightVariable());

        //Call
        assertThrows(BusinessRuleException.class,
            () -> ruleEngineService.retrieveTaxes(itemList, criteria));

        verify(articleFamilyRepository, times(1))
            .findByProductTypeIdAndArticleIdAndStatusAndDeletedAtIsNull(anyLong(), anyLong(),
                anyInt());
        verify(taxRepository, times(1))
            .findByFamilyInAndStatusAndDeleteIsNull(anyList(), anyInt());
        verify(taxRepository, times(1))
            .findByFamilyInAndStatusAndDeleteIsNull(anyList(), anyInt());
        verify(taxRuleRepository, times(1))
            .findByTaxIdAndStatusAndDeletedAtIsNull(anyLong(), anyInt());
        verify(ruleVariableRepository, times(1))
            .findByIdAndDeletedAtIsNull(anyLong());

    }

    @Test
    void test_listItemTax_Should_ThrowIllegalArgumentException_When_PathNotMatchGreaterTwo()
        throws BusinessRuleException {

        //Data
        Long idTax = 2L;
        List<Item> itemList = Arrays.asList(getItem());

        Map<String, Object> map = new HashMap<>();
        map.put("locationId", "1");

        Map<String, Object> criteria = new HashMap<>();
        criteria.put("countryId", map);

        ArticleFamily articleFamily = ArticleFamily.builder()
            .family(getFamily(1))
            .articleId(1L)
            .productTypeId(1L)
            .build();

        List<Tax> taxList = new ArrayList<>();
        taxList.add(getTax(idTax, true, 1));

        List<TaxRule> taxRuleList = Arrays
            .asList(
                getTaxRule(1L, "Equal", 1L, 2L, "Number", null, "Number", "country.location",
                    "country.location"));

        //When
        when(articleFamilyRepository
            .findByProductTypeIdAndArticleIdAndStatusAndDeletedAtIsNull(anyLong(), anyLong(),
                anyInt())).thenReturn(Arrays.asList(articleFamily));
        when(taxRepository.findByFamilyInAndStatusAndDeleteIsNull(anyList(), anyInt()))
            .thenReturn(taxList);
        when(taxRuleRepository
            .findByTaxIdAndStatusAndDeletedAtIsNull(anyLong(), anyInt()))
            .thenReturn(taxRuleList);
        when(ruleVariableRepository
            .findByIdAndDeletedAtIsNull(anyLong()))
            .thenReturn(taxRuleList.get(0).getRightVariable());

        //Call
        assertThrows(BusinessRuleException.class,
            () -> ruleEngineService.retrieveTaxes(itemList, criteria));
        verify(articleFamilyRepository, times(1))
            .findByProductTypeIdAndArticleIdAndStatusAndDeletedAtIsNull(anyLong(), anyLong(),
                anyInt());
        verify(taxRepository, times(1))
            .findByFamilyInAndStatusAndDeleteIsNull(anyList(), anyInt());
        verify(taxRuleRepository, times(1))
            .findByTaxIdAndStatusAndDeletedAtIsNull(anyLong(), anyInt());
        verify(ruleVariableRepository, times(1))
            .findByIdAndDeletedAtIsNull(anyLong());
    }

    private Item getItem() {
        return Item.builder()
            .articleId(1L)
            .productTypeId(1L)
            .price(new BigDecimal(500))
            .discount(new BigDecimal(100))
            .quantity(1)
            .build();
    }

    private Family getFamily(Integer status) {
        return Family.builder()
            .familyType(new FamilyType("Venta"))
            .countryId(1L)
            .status(status)
            .name("Family")
            .build();
    }

    private Tax getTax(Long id, Boolean applyRule, Integer status) {
        Tax tax = new Tax();
        tax.setId(id);
        tax.setApplyRules(applyRule);
        tax.setStatus(status);
        tax.setFamily(getFamily(1));
        tax.setTaxType(new TaxType());
        tax.setValue(new BigDecimal(8.0));
        return tax;
    }

    private TaxRule getTaxRule(Long tax, String ruleType, Long left, Long right,
        String leftType, String leftValue, String rightType, String rightValue, String path) {
        TaxRule taxRule = new TaxRule();
        taxRule.setTax(getTax(tax, true, 1));
        taxRule.setRuleType(getRuleType(ruleType));
        taxRule.setLeftVariable(getVariable(left, leftType, leftValue, null));
        taxRule.setRightVariable(getVariable(right, rightType, rightValue, path));
        return taxRule;
    }

    private RuleType getRuleType(String identifier) {
        RuleType ruleType = new RuleType();
        ruleType.setId(1L);
        ruleType.setIdentifier(identifier);
        return ruleType;
    }

    private RuleVariable getVariable(Long id, String type, String value, String path) {
        RuleVariable variable = new RuleVariable();
        variable.setId(id);
        variable.setType(type);
        variable.setPath(path);
        variable.setValue(value);
        variable.setName("NameVariable");
        variable.setDescription("Description Variable");
        return variable;
    }
}
