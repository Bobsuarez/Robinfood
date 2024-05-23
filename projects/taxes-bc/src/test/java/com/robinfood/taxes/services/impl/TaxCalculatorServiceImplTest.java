package com.robinfood.taxes.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.robinfood.taxes.constants.CalculatorConstants;
import com.robinfood.taxes.domain.CalculatedTax;
import com.robinfood.taxes.domain.FamilyExpression;
import com.robinfood.taxes.domain.MappedVariable;
import com.robinfood.taxes.domain.TaxableItem;
import com.robinfood.taxes.models.Expression;
import com.robinfood.taxes.models.FormulaVariable;
import com.robinfood.taxes.repositories.ExpressionRepository;
import com.robinfood.taxes.repositories.FormulaVariableRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TaxCalculatorServiceImplTest {

    @Mock
    private ExpressionRepository taxExpressionRepository;

    @Mock
    private FormulaVariableRepository variableRepository;

    @InjectMocks
    private TaxCalculatorServiceImpl calculatorService;

    @Test
    void test_calculateForProducts_Should_ThrowException_When_NoExpressionIsFound() {
        // Given
        Map<String, Object> additionalVariables = new HashMap<>();
        TaxableItem taxableProduct = getTaxableProduct();
        CalculatedTax tax = CalculatedTax.builder()
            .taxId(1L)
            .taxRate(BigDecimal.valueOf(0.10))
            .build();
        taxableProduct.setTaxes(Arrays.asList(tax));
        Expression expression = new Expression();
        expression.setValue("price * taxRate");
        expression.setId(1L);
        FamilyExpression familyExpression = new FamilyExpression(2L, expression);
        FormulaVariable priceVar = FormulaVariable.builder()
            .name("price")
            .type(CalculatorConstants.INNER_TYPE)
            .build();

        FormulaVariable taxVar = FormulaVariable.builder()
            .name("taxRate")
            .type(CalculatorConstants.INNER_TYPE)
            .build();
        List<MappedVariable> variables = Arrays.asList(new MappedVariable(1L, priceVar),
            new MappedVariable(1L, taxVar));

        // When
        when(taxExpressionRepository.findAllByTaxId(any()))
            .thenReturn(Arrays.asList(familyExpression));
        when(variableRepository.findAllForExpressionIds(any())).thenReturn(variables);

        // Then
        assertThrows(EntityNotFoundException.class,
            () -> calculatorService
                .calculateForProducts(Arrays.asList(taxableProduct), additionalVariables));

        verify(taxExpressionRepository, times(1)).findAllByTaxId(any());
        verify(variableRepository, times(1)).findAllForExpressionIds(any());
    }

    @Test
    void test_calculateForProducts_Should_ThrowException_When_NoVariablesAreFoundForExpression() {
        // Given
        Map<String, Object> additionalVariables = new HashMap<>();
        TaxableItem taxableProduct = getTaxableProduct();
        CalculatedTax tax = CalculatedTax.builder()
            .taxId(1L)
            .taxRate(BigDecimal.valueOf(0.10))
            .build();
        taxableProduct.setTaxes(Arrays.asList(tax));
        Expression expression = new Expression();
        expression.setValue("price * taxRate");
        expression.setId(1L);
        FamilyExpression familyExpression = new FamilyExpression(1L, expression);
        FormulaVariable priceVar = FormulaVariable.builder()
            .name("price")
            .type(CalculatorConstants.INNER_TYPE)
            .build();

        FormulaVariable taxVar = FormulaVariable.builder()
            .name("taxRate")
            .type(CalculatorConstants.INNER_TYPE)
            .build();
        List<MappedVariable> variables = Arrays.asList(new MappedVariable(2L, priceVar),
            new MappedVariable(2L, taxVar));

        // When
        when(taxExpressionRepository.findAllByTaxId(any()))
            .thenReturn(Arrays.asList(familyExpression));
        when(variableRepository.findAllForExpressionIds(any())).thenReturn(variables);

        // Then
        assertThrows(EntityNotFoundException.class,
            () -> calculatorService
                .calculateForProducts(Arrays.asList(taxableProduct), additionalVariables));

        verify(taxExpressionRepository, times(1)).findAllByTaxId(any());
        verify(variableRepository, times(1)).findAllForExpressionIds(any());
    }

    @Test
    void test_calculateForProducts_Should_CalculateTax_When_ValidDataIsGiven() {
        // Given
        Map<String, Object> additionalVariables = new HashMap<>();
        TaxableItem taxableProduct = getTaxableProduct();
        CalculatedTax tax = CalculatedTax.builder()
            .taxId(1L)
            .taxRate(BigDecimal.valueOf(0.10))
            .build();
        taxableProduct.setTaxes(Arrays.asList(tax));
        Expression expression = new Expression();
        expression.setValue("price * taxRate");
        expression.setId(1L);
        FamilyExpression familyExpression = new FamilyExpression(1L, expression);
        FormulaVariable priceVar = FormulaVariable.builder()
            .name("price")
            .type(CalculatorConstants.INNER_TYPE)
            .build();

        FormulaVariable taxVar = FormulaVariable.builder()
            .name("taxRate")
            .type(CalculatorConstants.INNER_TYPE)
            .build();
        List<MappedVariable> variables = Arrays.asList(new MappedVariable(1L, priceVar),
            new MappedVariable(2L, taxVar));

        // When
        when(taxExpressionRepository.findAllByTaxId(any()))
            .thenReturn(Arrays.asList(familyExpression));
        when(variableRepository.findAllForExpressionIds(any())).thenReturn(variables);

        // Then
        calculatorService.calculateForProducts(Arrays.asList(taxableProduct), additionalVariables);

        verify(taxExpressionRepository, times(1)).findAllByTaxId(any());
        verify(variableRepository, times(1)).findAllForExpressionIds(any());

        BigDecimal expectedTax = BigDecimal.valueOf(1000L);
        expectedTax = expectedTax
            .setScale(CalculatorConstants.DECIMAL_PLACES, RoundingMode.HALF_EVEN);
        assertEquals(expectedTax, tax.getValue());
    }

    @Test
    void test_calculateForProducts_Should_CalculateTax_When_GivenAnAdditionalNumberVariable() {
        // Given
        Map<String, Object> additionalVariables = new HashMap<>();
        TaxableItem taxableProduct = getTaxableProduct();
        CalculatedTax tax = CalculatedTax.builder()
            .taxId(1L)
            .taxRate(BigDecimal.valueOf(0.10))
            .build();
        taxableProduct.setTaxes(Arrays.asList(tax));
        Expression expression = new Expression();
        expression.setValue("(price * taxRate) - number");
        expression.setId(1L);
        FamilyExpression familyExpression = new FamilyExpression(1L, expression);
        FormulaVariable priceVar = FormulaVariable.builder()
            .name("price")
            .type(CalculatorConstants.INNER_TYPE)
            .build();

        FormulaVariable taxVar = FormulaVariable.builder()
            .name("taxRate")
            .type(CalculatorConstants.INNER_TYPE)
            .build();

        FormulaVariable numberVar = FormulaVariable.builder()
            .name("number")
            .type(CalculatorConstants.NUMBER_TYPE)
            .value("200")
            .build();
        List<MappedVariable> variables = Arrays.asList(new MappedVariable(1L, priceVar),
            new MappedVariable(1L, taxVar), new MappedVariable(1L, numberVar));

        // When
        when(taxExpressionRepository.findAllByTaxId(any()))
            .thenReturn(Arrays.asList(familyExpression));
        when(variableRepository.findAllForExpressionIds(any())).thenReturn(variables);

        // Then
        calculatorService.calculateForProducts(Arrays.asList(taxableProduct), additionalVariables);

        verify(taxExpressionRepository, times(1)).findAllByTaxId(any());
        verify(variableRepository, times(1)).findAllForExpressionIds(any());

        BigDecimal expectedTax = BigDecimal.valueOf(800);
        expectedTax = expectedTax
            .setScale(CalculatorConstants.DECIMAL_PLACES, RoundingMode.HALF_EVEN);
        assertEquals(expectedTax, tax.getValue());
    }

    @Test
    void test_calculateForProducts_Should_CalculateTax_When_GivenAnAdditionalPathVariable() {
        // Given
        Map<String, Object> additionalVariables = new HashMap<>();
        additionalVariables.put("modifier", "1");
        TaxableItem taxableProduct = getTaxableProduct();
        CalculatedTax tax = CalculatedTax.builder()
            .taxId(1L)
            .taxRate(BigDecimal.valueOf(0.10))
            .build();
        taxableProduct.setTaxes(Arrays.asList(tax));
        Expression expression = new Expression();
        expression.setValue("(price * taxRate) - modifier");
        expression.setId(1L);
        FamilyExpression familyExpression = new FamilyExpression(1L, expression);
        FormulaVariable priceVar = FormulaVariable.builder()
            .name("price")
            .type(CalculatorConstants.INNER_TYPE)
            .build();

        FormulaVariable taxVar = FormulaVariable.builder()
            .name("taxRate")
            .type(CalculatorConstants.INNER_TYPE)
            .build();

        FormulaVariable modVar = FormulaVariable.builder()
            .name("modifier")
            .type(CalculatorConstants.PATH_TYPE)
            .value("modifier")
            .build();
        List<MappedVariable> variables = Arrays.asList(new MappedVariable(1L, priceVar),
            new MappedVariable(1L, taxVar), new MappedVariable(1L, modVar));

        // When
        when(taxExpressionRepository.findAllByTaxId(any()))
            .thenReturn(Arrays.asList(familyExpression));
        when(variableRepository.findAllForExpressionIds(any())).thenReturn(variables);

        // Then
        calculatorService.calculateForProducts(Arrays.asList(taxableProduct), additionalVariables);

        verify(taxExpressionRepository, times(1)).findAllByTaxId(any());
        verify(variableRepository, times(1)).findAllForExpressionIds(any());

        BigDecimal expectedTax = BigDecimal.valueOf(999);
        expectedTax = expectedTax
            .setScale(CalculatorConstants.DECIMAL_PLACES, RoundingMode.HALF_EVEN);
        assertEquals(expectedTax, tax.getValue());
    }

    @Test
    void test_calculateForProducts_Should_CalculateTax_When_GivenAnAdditionalNestedPathVariable() {
        // Given
        Map<String, Object> additionalVariables = new HashMap<>();
        Map<String, Object> nestedMap = new HashMap<>();
        nestedMap.put("level2", "2");
        additionalVariables.put("level1", nestedMap);
        TaxableItem taxableProduct = getTaxableProduct();
        CalculatedTax tax = CalculatedTax.builder()
            .taxId(1L)
            .taxRate(BigDecimal.valueOf(0.10))
            .build();
        taxableProduct.setTaxes(Arrays.asList(tax));
        Expression expression = new Expression();
        expression.setValue("(price * taxRate) / modifier");
        expression.setId(1L);
        FamilyExpression familyExpression = new FamilyExpression(1L, expression);
        FormulaVariable priceVar = FormulaVariable.builder()
            .name("price")
            .type(CalculatorConstants.INNER_TYPE)
            .build();

        FormulaVariable taxVar = FormulaVariable.builder()
            .name("taxRate")
            .type(CalculatorConstants.INNER_TYPE)
            .build();

        FormulaVariable modVar = FormulaVariable.builder()
            .name("modifier")
            .type(CalculatorConstants.PATH_TYPE)
            .value("level1.level2")
            .build();
        List<MappedVariable> variables = Arrays.asList(new MappedVariable(1L, priceVar),
            new MappedVariable(1L, taxVar), new MappedVariable(1L, modVar));

        // When
        when(taxExpressionRepository.findAllByTaxId(any()))
            .thenReturn(Arrays.asList(familyExpression));
        when(variableRepository.findAllForExpressionIds(any())).thenReturn(variables);

        // Then
        calculatorService.calculateForProducts(Arrays.asList(taxableProduct), additionalVariables);

        verify(taxExpressionRepository, times(1)).findAllByTaxId(any());
        verify(variableRepository, times(1)).findAllForExpressionIds(any());

        BigDecimal expectedTax = BigDecimal.valueOf(500);
        expectedTax = expectedTax
            .setScale(CalculatorConstants.DECIMAL_PLACES, RoundingMode.HALF_EVEN);
        assertEquals(expectedTax, tax.getValue());
    }

    @Test
    void test_calculateForProducts_Should_ThrowException_When_MissingFinalLevelOfNestedPath() {
        // Given
        Map<String, Object> additionalVariables = new HashMap<>();
        Map<String, Object> nestedMap = new HashMap<>();
        nestedMap.put("nolevel", "2");
        additionalVariables.put("level1", nestedMap);
        TaxableItem taxableProduct = getTaxableProduct();
        CalculatedTax tax = CalculatedTax.builder()
            .taxId(1L)
            .taxRate(BigDecimal.valueOf(0.10))
            .build();
        taxableProduct.setTaxes(Arrays.asList(tax));
        Expression expression = new Expression();
        expression.setValue("(price * taxRate) / modifier");
        expression.setId(1L);
        FamilyExpression familyExpression = new FamilyExpression(1L, expression);
        FormulaVariable priceVar = FormulaVariable.builder()
            .name("price")
            .type(CalculatorConstants.INNER_TYPE)
            .build();

        FormulaVariable taxVar = FormulaVariable.builder()
            .name("taxRate")
            .type(CalculatorConstants.INNER_TYPE)
            .build();

        FormulaVariable modVar = FormulaVariable.builder()
            .name("modifier")
            .type(CalculatorConstants.PATH_TYPE)
            .value("level1.level2")
            .build();
        List<MappedVariable> variables = Arrays.asList(new MappedVariable(1L, priceVar),
            new MappedVariable(1L, taxVar), new MappedVariable(1L, modVar));

        // When
        when(taxExpressionRepository.findAllByTaxId(any()))
            .thenReturn(Arrays.asList(familyExpression));
        when(variableRepository.findAllForExpressionIds(any())).thenReturn(variables);

        // Then
        assertThrows(IllegalArgumentException.class,
            () -> calculatorService
                .calculateForProducts(Arrays.asList(taxableProduct), additionalVariables));

        verify(taxExpressionRepository, times(1)).findAllByTaxId(any());
        verify(variableRepository, times(1)).findAllForExpressionIds(any());
    }

    @Test
    void test_calculateForProducts_Should_ThrowException_When_MissingMiddleLevelOfNestedPath() {
        // Given
        Map<String, Object> additionalVariables = new HashMap<>();
        Map<String, Object> nestedMap = new HashMap<>();
        nestedMap.put("level3", "2");
        additionalVariables.put("level1", nestedMap);
        TaxableItem taxableProduct = getTaxableProduct();
        CalculatedTax tax = CalculatedTax.builder()
            .taxId(1L)
            .taxRate(BigDecimal.valueOf(0.10))
            .build();
        taxableProduct.setTaxes(Arrays.asList(tax));
        Expression expression = new Expression();
        expression.setValue("(price * taxRate) / modifier");
        expression.setId(1L);
        FamilyExpression familyExpression = new FamilyExpression(1L, expression);
        FormulaVariable priceVar = FormulaVariable.builder()
            .name("price")
            .type(CalculatorConstants.INNER_TYPE)
            .build();

        FormulaVariable taxVar = FormulaVariable.builder()
            .name("taxRate")
            .type(CalculatorConstants.INNER_TYPE)
            .build();

        FormulaVariable modVar = FormulaVariable.builder()
            .name("modifier")
            .type(CalculatorConstants.PATH_TYPE)
            .value("level1.level2.level3")
            .build();
        List<MappedVariable> variables = Arrays.asList(new MappedVariable(1L, priceVar),
            new MappedVariable(1L, taxVar), new MappedVariable(1L, modVar));

        // When
        when(taxExpressionRepository.findAllByTaxId(any()))
            .thenReturn(Arrays.asList(familyExpression));
        when(variableRepository.findAllForExpressionIds(any())).thenReturn(variables);

        // Then
        assertThrows(IllegalArgumentException.class,
            () -> calculatorService
                .calculateForProducts(Arrays.asList(taxableProduct), additionalVariables));

        verify(taxExpressionRepository, times(1)).findAllByTaxId(any());
        verify(variableRepository, times(1)).findAllForExpressionIds(any());
    }

    @Test
    void test_calculateForProducts_Should_ThrowException_When_PathVariableIsNotNumber() {
        // Given
        Map<String, Object> additionalVariables = new HashMap<>();
        additionalVariables.put("modifier", "String");
        TaxableItem taxableProduct = getTaxableProduct();
        CalculatedTax tax = CalculatedTax.builder()
            .taxId(1L)
            .taxRate(BigDecimal.valueOf(0.10))
            .build();
        taxableProduct.setTaxes(Arrays.asList(tax));
        Expression expression = new Expression();
        expression.setValue("(price * taxRate) - modifier");
        expression.setId(1L);
        FamilyExpression familyExpression = new FamilyExpression(1L, expression);
        FormulaVariable priceVar = FormulaVariable.builder()
            .name("price")
            .type(CalculatorConstants.INNER_TYPE)
            .build();

        FormulaVariable taxVar = FormulaVariable.builder()
            .name("taxRate")
            .type(CalculatorConstants.INNER_TYPE)
            .build();

        FormulaVariable modVar = FormulaVariable.builder()
            .name("modifier")
            .type(CalculatorConstants.PATH_TYPE)
            .value("modifier")
            .build();
        List<MappedVariable> variables = Arrays.asList(new MappedVariable(1L, priceVar),
            new MappedVariable(1L, taxVar), new MappedVariable(1L, modVar));

        // When
        when(taxExpressionRepository.findAllByTaxId(any()))
            .thenReturn(Arrays.asList(familyExpression));
        when(variableRepository.findAllForExpressionIds(any())).thenReturn(variables);

        // Then
        assertThrows(IllegalArgumentException.class,
            () -> calculatorService
                .calculateForProducts(Arrays.asList(taxableProduct), additionalVariables));

        verify(taxExpressionRepository, times(1)).findAllByTaxId(any());
        verify(variableRepository, times(1)).findAllForExpressionIds(any());
    }

    @Test
    void test_calculateForProducts_Should_ThrowException_When_InvalidVariableType() {
        // Given
        Map<String, Object> additionalVariables = new HashMap<>();
        TaxableItem taxableProduct = getTaxableProduct();
        CalculatedTax tax = CalculatedTax.builder()
            .taxId(1L)
            .taxRate(BigDecimal.valueOf(0.10))
            .build();
        taxableProduct.setTaxes(Arrays.asList(tax));
        Expression expression = new Expression();
        expression.setValue("(price * taxRate) - modifier");
        expression.setId(1L);
        FamilyExpression familyExpression = new FamilyExpression(1L, expression);
        FormulaVariable priceVar = FormulaVariable.builder()
            .name("price")
            .type(CalculatorConstants.INNER_TYPE)
            .build();

        FormulaVariable taxVar = FormulaVariable.builder()
            .name("taxRate")
            .type(CalculatorConstants.INNER_TYPE)
            .build();

        FormulaVariable invalidVar = FormulaVariable.builder()
            .name("modifier")
            .type("someInvalidType")
            .value("500")
            .build();
        List<MappedVariable> variables = Arrays.asList(new MappedVariable(1L, priceVar),
            new MappedVariable(1L, taxVar), new MappedVariable(1L, invalidVar));

        // When
        when(taxExpressionRepository.findAllByTaxId(any()))
            .thenReturn(Arrays.asList(familyExpression));
        when(variableRepository.findAllForExpressionIds(any())).thenReturn(variables);

        // Then
        assertThrows(IllegalArgumentException.class,
            () -> calculatorService
                .calculateForProducts(Arrays.asList(taxableProduct), additionalVariables));

        verify(taxExpressionRepository, times(1)).findAllByTaxId(any());
        verify(variableRepository, times(1)).findAllForExpressionIds(any());
    }

    @Test
    void test_calculateForProducts_Should_CalculateTax_When_GivenAnAdditionalExpressionVariable() {
        // Given
        Map<String, Object> additionalVariables = new HashMap<>();
        TaxableItem taxableProduct = getTaxableProduct();
        CalculatedTax tax = CalculatedTax.builder()
            .taxId(1L)
            .taxRate(BigDecimal.valueOf(0.10))
            .build();
        taxableProduct.setTaxes(Arrays.asList(tax));
        Expression expression = new Expression();
        expression.setValue("(price * taxRate) + innerexp");
        expression.setId(1L);
        FamilyExpression familyExpression = new FamilyExpression(1L, expression);
        FormulaVariable priceVar = FormulaVariable.builder()
            .name("price")
            .type(CalculatorConstants.INNER_TYPE)
            .build();

        FormulaVariable taxVar = FormulaVariable.builder()
            .name("taxRate")
            .type(CalculatorConstants.INNER_TYPE)
            .build();

        FormulaVariable expressionVar = FormulaVariable.builder()
            .name("innerexp")
            .type(CalculatorConstants.EXPRESSION_TYPE)
            .value("2")
            .build();
        List<MappedVariable> variables = Arrays.asList(new MappedVariable(1L, priceVar),
            new MappedVariable(1L, taxVar), new MappedVariable(1L, expressionVar));

        Expression innerExpression = new Expression();
        innerExpression.setValue("price * constant");
        innerExpression.setId(2L);

        FormulaVariable innerVar = FormulaVariable.builder()
            .name("constant")
            .type(CalculatorConstants.NUMBER_TYPE)
            .value("3")
            .build();

        List<FormulaVariable> innerExpVariables = Arrays.asList(priceVar, innerVar);
        // When
        when(taxExpressionRepository.findAllByTaxId(any()))
            .thenReturn(Arrays.asList(familyExpression));
        when(variableRepository.findAllForExpressionIds(any())).thenReturn(variables);
        when(taxExpressionRepository.findById(any())).thenReturn(Optional.of(innerExpression));
        when(variableRepository.findByExpressionId(any())).thenReturn(innerExpVariables);

        // Then
        calculatorService.calculateForProducts(Arrays.asList(taxableProduct), additionalVariables);

        verify(taxExpressionRepository, times(1)).findAllByTaxId(any());
        verify(variableRepository, times(1)).findAllForExpressionIds(any());
        verify(taxExpressionRepository, times(1)).findById(any());
        verify(variableRepository, times(1)).findByExpressionId(any());

        BigDecimal expectedTax = BigDecimal.valueOf(31000);
        expectedTax = expectedTax
            .setScale(CalculatorConstants.DECIMAL_PLACES, RoundingMode.HALF_EVEN);
        assertEquals(expectedTax, tax.getValue());
    }

    private TaxableItem getTaxableProduct() {
        return TaxableItem.builder()
            .price(BigDecimal.valueOf(10000L))
            .discount(BigDecimal.ZERO)
            .quantity(1)
            .build();
    }

}