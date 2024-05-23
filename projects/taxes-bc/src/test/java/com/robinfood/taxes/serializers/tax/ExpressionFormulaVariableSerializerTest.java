package com.robinfood.taxes.serializers.tax;

import static org.junit.jupiter.api.Assertions.assertAll;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.robinfood.taxes.models.Expression;
import com.robinfood.taxes.models.ExpressionFormulaVariable;
import com.robinfood.taxes.models.FormulaVariable;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ExpressionFormulaVariableSerializerTest {

    @InjectMocks
    private ExpressionFormulaVariableSerializer expressionFormulaVariableSerializer;

    @Mock
    private JsonGenerator jsonGenerator;

    @Mock
    private SerializerProvider serializerProvider;

    @Test
    void test_ExpressionFormulaVariableSerializer_Should_Serialize_When_ValidData() {
        ExpressionFormulaVariable expressionFormulaVariable = getExpressionFormulaVariable();
        expressionFormulaVariable.setCreatedAt(LocalDateTime.now());
        expressionFormulaVariable.setUpdatedAt(LocalDateTime.now());
        assertAll(
            () -> expressionFormulaVariableSerializer
                .serialize(expressionFormulaVariable, jsonGenerator, serializerProvider));
    }

    @Test
    void test_ExpressionFormulaVariableSerializer_Should_Serializer_When_ValidDataWithDeleteAt() {
        ExpressionFormulaVariable expressionFormulaVariable = getExpressionFormulaVariable();
        expressionFormulaVariable.setCreatedAt(LocalDateTime.now());
        expressionFormulaVariable.setUpdatedAt(LocalDateTime.now());
        expressionFormulaVariable.setDeletedAt(LocalDateTime.now());
        assertAll(
            () -> expressionFormulaVariableSerializer
                .serialize(expressionFormulaVariable, jsonGenerator, serializerProvider));
    }

    private Expression getExpression() {
        Expression expression = Expression.builder()
            .value("value")
            .description("description")
            .status(1)
            .build();
        expression.setId(1L);
        return expression;
    }

    private FormulaVariable getFormulaVariable() {
        FormulaVariable formulaVariable = FormulaVariable.builder()
            .type("number")
            .name("variable")
            .value("1")
            .description("description")
            .build();
        formulaVariable.setId(1L);
        return formulaVariable;
    }

    private ExpressionFormulaVariable getExpressionFormulaVariable() {
        ExpressionFormulaVariable expressionFormulaVariable = ExpressionFormulaVariable.builder()
            .expression(getExpression())
            .formulaVariable(getFormulaVariable())
            .build();
        expressionFormulaVariable.setId(1L);
        return expressionFormulaVariable;
    }

}
