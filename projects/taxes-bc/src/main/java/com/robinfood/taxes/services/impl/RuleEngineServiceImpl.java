package com.robinfood.taxes.services.impl;

import static com.robinfood.taxes.constants.GeneralConstants.ACTIVE_STATUS;

import com.robinfood.taxes.annotations.BasicLog;
import com.robinfood.taxes.domain.CalculatedTax;
import com.robinfood.taxes.domain.Item;
import com.robinfood.taxes.domain.RuleVariable;
import com.robinfood.taxes.domain.TaxableItem;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.ArticleFamily;
import com.robinfood.taxes.models.Tax;
import com.robinfood.taxes.models.TaxRule;
import com.robinfood.taxes.repositories.ArticleFamilyRepository;
import com.robinfood.taxes.repositories.RuleVariableRepository;
import com.robinfood.taxes.repositories.TaxRepository;
import com.robinfood.taxes.repositories.TaxRuleRepository;
import com.robinfood.taxes.rules.AbstractRule;
import com.robinfood.taxes.rules.RuleFactory;
import com.robinfood.taxes.services.RuleEngineService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RuleEngineServiceImpl implements RuleEngineService {

    private final TaxRuleRepository taxRuleRepository;
    private final RuleVariableRepository ruleVariableRepository;
    private final RuleFactory ruleFactory;
    private final ArticleFamilyRepository articleFamilyRepository;
    private final ModelMapper modelMapper;
    private final TaxRepository taxRepository;

    public RuleEngineServiceImpl(
        TaxRuleRepository taxRuleRepository,
        RuleVariableRepository ruleVariableRepository, RuleFactory ruleFactory,
        ArticleFamilyRepository articleFamilyRepository,
        ModelMapper modelMapper, TaxRepository taxRepository) {
        this.taxRuleRepository = taxRuleRepository;
        this.ruleVariableRepository = ruleVariableRepository;
        this.ruleFactory = ruleFactory;
        this.articleFamilyRepository = articleFamilyRepository;
        this.modelMapper = modelMapper;
        this.taxRepository = taxRepository;
    }

    @BasicLog
    @Override
    public List<TaxableItem> retrieveTaxes(List<Item> items, Map<String, Object> criteria)
        throws BusinessRuleException {

        log.trace("Ready to iterate items {}", items);
        List<TaxableItem> taxableProducts = new ArrayList<>();
        TaxableItem taxableProduct = null;

        for (Item item : items) {
            taxableProduct = retrieveItemTaxes(item, criteria);
            log.debug("Taxable Product retrieved successfully: {}", taxableProduct);
            taxableProducts.add(taxableProduct);
        }
        return taxableProducts;
    }

    private TaxableItem retrieveItemTaxes(Item item, Map<String, Object> criteria)
        throws BusinessRuleException {

        List<CalculatedTax> taxDetail = new ArrayList<>();
        CalculatedTax taxDetailBuilder;
        List<ArticleFamily> articleFamilies = findArticleFamily(item.getProductTypeId(),
            item.getArticleId());
        log.debug("ArticleFamilyTax retrieved: {}", articleFamilies);
        List<Long> familyIds = articleFamilies.stream()
            .map(article -> article.getFamily().getId())
            .collect(Collectors.toList());

        List<Tax> taxList = findTaxByFamily(familyIds);

        for (Tax tax : taxList) {
            if (Boolean.TRUE.equals(tax.getApplyRules())) {
                taxDetailBuilder = validateTaxes(tax, criteria);
            } else {
                taxDetailBuilder = mapTaxDetail(tax);
            }
            log.debug("Tax Detail retrieved successfully: {}", tax);
            if (taxDetailBuilder != null) {
                taxDetail.add(taxDetailBuilder);
            }
        }

        return TaxableItem.builder()
            .productTypeId(item.getProductTypeId())
            .articleId(item.getArticleId())
            .price(item.getPrice())
            .discount(item.getDiscount())
            .quantity(item.getQuantity())
            .taxes(taxDetail)
            .build();
    }

    private CalculatedTax validateTaxes(Tax tax, Map<String, Object> criteria)
        throws BusinessRuleException {

        log.trace("Starting process for obtain tax rules");
        CalculatedTax taxDetail = null;

        List<TaxRule> taxRules = findTaxRules(tax.getId());
        log.debug("TaxRule retrieved: {}", taxRules);

        for (TaxRule taxRule : taxRules) {
            if (isValidRule(taxRule, criteria)) {
                taxDetail = mapTaxDetail(tax);
                log.debug("Rule '{}' with criteria {} generate taxDetail {}", taxRule, criteria,
                    taxDetail);
            } else {
                log.debug("Rule '{}' with criteria {} did not pass validation", taxRule, criteria);
                return null;
            }
        }
        return taxDetail;
    }

    private List<ArticleFamily> findArticleFamily(Long productTypeId, Long articleId) {
        log.trace("Retrieving ArticleFamily with productTypeId {} and articleId {}",
            productTypeId, articleId);
        return articleFamilyRepository
            .findByProductTypeIdAndArticleIdAndStatusAndDeletedAtIsNull(productTypeId, articleId,
                ACTIVE_STATUS);
    }

    private List<Tax> findTaxByFamily(List<Long> familyId) {
        log.trace("Retrieving Tax with familyId {}.", familyId);
        return taxRepository
            .findByFamilyInAndStatusAndDeleteIsNull(familyId, ACTIVE_STATUS);
    }

    private List<TaxRule> findTaxRules(Long familyId) {
        log.trace("Retrieving TaxRule with familyId {}", familyId);
        return taxRuleRepository.findByTaxIdAndStatusAndDeletedAtIsNull(familyId, ACTIVE_STATUS);
    }

    private com.robinfood.taxes.models.RuleVariable findVariable(Long variableId) {
        log.trace("Retrieving Rule Variable with id {}", variableId);
        return ruleVariableRepository.findByIdAndDeletedAtIsNull(variableId);
    }

    private boolean isValidRule(TaxRule taxRules, Map<String, Object> criteria)
        throws BusinessRuleException {

        log.trace("Starting process for validate rules");
        String leftValue;
        String rightValue;

        com.robinfood.taxes.models.RuleVariable leftVariable = findVariable(
            taxRules.getLeftVariable().getId());
        log.trace("Rule Variable left retrieved: {}", leftVariable);

        if (leftVariable.getPath() != null) {
            leftValue = getValueFromPath(leftVariable.getPath(), criteria);
            leftVariable.setValue(leftValue);
        }

        com.robinfood.taxes.models.RuleVariable rightVariable = findVariable(
            taxRules.getRightVariable().getId());
        log.trace("Rule Variable right retrieved: {}", rightVariable);

        if (rightVariable.getPath() != null) {
            rightValue = getValueFromPath(rightVariable.getPath(), criteria);
            rightVariable.setValue(rightValue);
        }

        RuleVariable left = modelMapper
            .map(leftVariable, RuleVariable.class);
        RuleVariable right = modelMapper
            .map(rightVariable, RuleVariable.class);

        AbstractRule rule = ruleFactory
            .createRule(taxRules.getRuleType().getIdentifier(), left, right);
        return rule.evaluate();
    }

    private static String getValueFromPath(String pathString, Map<String, Object> criteria)
        throws BusinessRuleException {

        String[] path = pathString.split("\\.");
        Map<String, Object> currentLevel = criteria;

        for (int i = 0; i < path.length - 1; i++) {
            currentLevel = (Map<String, Object>) currentLevel.get(path[i]);
            if (currentLevel == null) {
                throw new BusinessRuleException(
                    String.format("Path %s not present in Json", path));
            }
        }
        Object value = currentLevel.get(path[path.length - 1]);
        if (value == null) {
            throw new BusinessRuleException(
                String.format("Path %s not present in Json", path));
        }

        return value.toString();
    }

    private CalculatedTax mapTaxDetail(Tax familyTax) {
        return CalculatedTax.builder()
                .id(familyTax.getId())
                .name(familyTax.getName())
                .taxRate(new BigDecimal(String.valueOf(familyTax.getValue())))
                .sapId(familyTax.getSapId())
                .familyId(familyTax.getFamily().getId())
                .familyTypeId(
                    familyTax.getFamily().getFamilyType().getId())
                .taxId(familyTax.getId())
                .taxTypeId(familyTax.getTaxType().getId())
                .build();
    }
}
