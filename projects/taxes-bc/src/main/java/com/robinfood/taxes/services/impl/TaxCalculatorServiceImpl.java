package com.robinfood.taxes.services.impl;

import static com.robinfood.taxes.constants.CalculatorConstants.DECIMAL_PLACES;
import static com.robinfood.taxes.constants.CalculatorConstants.EXPRESSION_TYPE;
import static com.robinfood.taxes.constants.CalculatorConstants.INNER_TYPE;
import static com.robinfood.taxes.constants.CalculatorConstants.NUMBER_TYPE;
import static com.robinfood.taxes.constants.CalculatorConstants.PATH_TYPE;
import static com.robinfood.taxes.constants.CalculatorConstants.DECIMAL_PLACES_CALCULATION_TAXES;


import com.robinfood.taxes.annotations.BasicLog;
import com.robinfood.taxes.domain.CalculatedTax;
import com.robinfood.taxes.domain.FamilyExpression;
import com.robinfood.taxes.domain.MappedVariable;
import com.robinfood.taxes.domain.ProductTaxData;
import com.robinfood.taxes.domain.TaxableItem;
import com.robinfood.taxes.models.Expression;
import com.robinfood.taxes.models.FormulaVariable;
import com.robinfood.taxes.repositories.ExpressionRepository;
import com.robinfood.taxes.repositories.FormulaVariableRepository;
import com.robinfood.taxes.services.TaxCalculatorService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TaxCalculatorServiceImpl implements TaxCalculatorService {

    private final ExpressionRepository taxExpressionRepository;
    private final FormulaVariableRepository variableRepository;

    public TaxCalculatorServiceImpl(
        ExpressionRepository taxExpressionRepository,
        FormulaVariableRepository variableRepository) {
        this.taxExpressionRepository = taxExpressionRepository;
        this.variableRepository = variableRepository;
    }

    @BasicLog
    @Override
    public List<TaxableItem> calculateForProducts(List<TaxableItem> taxableProductList,
        Map<String, Object> additionalVariables) {
        List<Long> taxIds = taxableProductList.stream()
            .map(TaxableItem::getTaxes)
            .flatMap(Collection::stream)
            .map(CalculatedTax::getTaxId)
            .collect(Collectors.toList());
        List<FamilyExpression> expressionList = taxExpressionRepository.findAllByTaxId(taxIds);

        List<Long> expressionIds = expressionList.stream()
            .map(familyExpression -> familyExpression.getExpression().getId())
            .collect(Collectors.toList());
        List<MappedVariable> mappedVariableList = variableRepository.findAllForExpressionIds(expressionIds);

        Map<Long, Expression> expressionCache = expressionListToCache(expressionList);
        Map<Long, List<FormulaVariable>> variableCache = variableListToCache(mappedVariableList);

        for (TaxableItem taxableProduct : taxableProductList) {
            BigDecimal singleProductTax = calculateForSingleProduct(taxableProduct, expressionCache, variableCache,
                additionalVariables);
            BigDecimal totalTax = singleProductTax.multiply(BigDecimal.valueOf(taxableProduct.getQuantity()));
            taxableProduct.setTotalTax(totalTax);
        }

        return taxableProductList;
    }

    private Map<Long, Expression> expressionListToCache(List<FamilyExpression> expressionList) {
        return expressionList.stream()
            .collect(Collectors.toMap(FamilyExpression::getFamilyId,
                FamilyExpression::getExpression));
    }

    private Map<Long, List<FormulaVariable>> variableListToCache(List<MappedVariable> mappedVariables) {
        Map<Long, List<FormulaVariable>> cache = new HashMap<>();

        for (MappedVariable variable : mappedVariables) {
            List<FormulaVariable> variablesPerKey = cache.getOrDefault(variable.getExpressionId(),
                new ArrayList<>());
            variablesPerKey.add(variable.getVariable());
            cache.put(variable.getExpressionId(), variablesPerKey);
        }

        return cache;
    }

    private BigDecimal calculateForSingleProduct(TaxableItem taxableProduct,
        Map<Long, Expression> expressionCache, Map<Long, List<FormulaVariable>> variableCache,
        Map<String, Object> additionalVariables) {

        BigDecimal totalTax = BigDecimal.ZERO;
        BigDecimal totalTaxRate = taxableProduct.getTaxes().stream()
            .map(CalculatedTax::getTaxRate)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        additionalVariables.put("totalTaxRate", totalTaxRate.toString());
        for (CalculatedTax calculatedTax : taxableProduct.getTaxes()) {
            Expression expression = expressionCache.get(calculatedTax.getTaxId());
            if (expression == null) {
                throw new EntityNotFoundException(
                    String.format("Data Error - No expressions found for Tax ID %s",
                        calculatedTax.getTaxId()));
            }

            List<FormulaVariable> expressionVariables = variableCache.getOrDefault(expression.getId(),
                Collections.emptyList());
            if (expressionVariables.isEmpty()) {
                throw new EntityNotFoundException(String
                    .format("Data Error - No variables found for Family Tax ID %s and Expression ID %s",
                        calculatedTax.getTaxId(), expression.getId()));
            }

            ProductTaxData productTaxData = mapToProductTaxData(taxableProduct, calculatedTax);

            BigDecimal currentTax = evaluateExpression(productTaxData, expression.getValue(),
                expressionVariables, additionalVariables);
            totalTax = totalTax.add(currentTax);
            calculatedTax.setValue(currentTax);
        }

        return totalTax;
    }

    private ProductTaxData mapToProductTaxData(TaxableItem taxableProduct, CalculatedTax calculatedTax) {
        return ProductTaxData.builder()
            .price(taxableProduct.getPrice())
            .discount(taxableProduct.getDiscount())
            .taxRate(calculatedTax.getTaxRate())
            .build();
    }

    private BigDecimal evaluateExpression(ProductTaxData product, String expressionString,
        List<FormulaVariable> variables, Map<String, Object> additionalVariables) {
        List<Pair<String, BigDecimal>> parsedVariables = variables.stream()
            .filter(variable -> !variable.getType().equals(INNER_TYPE))
            .map(variable -> parseVariable(product, variable, additionalVariables))
            .collect(Collectors.toList());

        BigDecimal price = product.getPrice();
        BigDecimal discount = product.getDiscount();
        BigDecimal taxRate = product.getTaxRate();
        com.udojava.evalex.Expression expression = new com.udojava.evalex.Expression(expressionString)
                .setPrecision(DECIMAL_PLACES_CALCULATION_TAXES)
                .with("price", price)
                .and("discount", discount)
                .and("taxRate", taxRate);

        for (Pair<String, BigDecimal> variablePair : parsedVariables) {
            expression = expression.and(variablePair.getKey(), variablePair.getValue());
        }
        BigDecimal result = expression.eval();
        result = result.setScale(DECIMAL_PLACES, RoundingMode.HALF_EVEN);
        return result;
    }

    private Pair<String, BigDecimal> parseVariable(ProductTaxData product, FormulaVariable variable,
        Map<String, Object> additionalVariables) {
        BigDecimal value;
        switch (variable.getType()) {
            case NUMBER_TYPE:
                value = new BigDecimal(variable.getValue());
                break;
            case PATH_TYPE:
                value = getValueFromPath(variable.getValue(), additionalVariables);
                break;
            case EXPRESSION_TYPE:
                Long expressionId = Long.valueOf(variable.getValue());
                value = prepareAndEvaluateInnerExpression(product, expressionId, additionalVariables);
                break;
            default:
                throw new IllegalArgumentException(
                    String.format("Invalid type for variable. Type %s", variable.getType()));
        }

        value = value.setScale(DECIMAL_PLACES, RoundingMode.HALF_EVEN);

        return new ImmutablePair<>(variable.getName(), value);
    }

    private static BigDecimal getValueFromPath(String path, Map<String, Object> additionalVariables) {
        String[] levelNames = path.split("\\.");
        Map<String, Object> currentLevel = additionalVariables;
        for (int level=0; level<levelNames.length-1; level++) {
            currentLevel = (Map<String, Object>) additionalVariables.get(levelNames[level]);
            if (currentLevel == null) {
                throw new IllegalArgumentException(
                    String.format("Path %s not present in data", path));
            }
        }

        String value = (String) currentLevel.get(levelNames[levelNames.length-1]);
        if (value == null) {
            throw new IllegalArgumentException(
                String.format("Required parameter %s is not present in data", path));
        }

        try {
            return new BigDecimal(value);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException(
                String.format("Parameter %s with value %s must be a number", path, value));
        }

    }

    private BigDecimal prepareAndEvaluateInnerExpression(ProductTaxData product, Long expressionId,
        Map<String, Object> additionalVariables) {
        List<FormulaVariable> variables = variableRepository.findByExpressionId(expressionId);
        if (variables.isEmpty()) {
            throw new EntityNotFoundException(
                String.format("Data Error - No variables found for Expression %s", expressionId));
        }
        Expression expression = taxExpressionRepository.findById(expressionId).orElseThrow(
            () -> new EntityNotFoundException(
                String.format("Expression with ID %s not found", expressionId)));

        return evaluateExpression(product, expression.getValue(), variables, additionalVariables);
    }
}
