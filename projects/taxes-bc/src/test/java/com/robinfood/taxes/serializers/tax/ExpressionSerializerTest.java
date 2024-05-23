package com.robinfood.taxes.serializers.tax;

import static org.junit.jupiter.api.Assertions.assertAll;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.robinfood.taxes.models.Expression;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ExpressionSerializerTest {

    @InjectMocks
    private ExpressionSerializer expressionSerializer;

    @Mock
    private JsonGenerator jsonGenerator;

    @Mock
    private SerializerProvider serializerProvider;

    @Test
    void test_ExpressionSerializer_Should_Serialize_When_ReceiveValidExpression() {
        Expression expression = getExpression();
        expression.setCreatedAt(LocalDateTime.now());
        expression.setUpdatedAt(LocalDateTime.now());
        assertAll(
            () -> expressionSerializer.serialize(expression, jsonGenerator, serializerProvider));
    }

    @Test
    void test_ExpressionSerializer_Should_Serializer_When_ReceiveValidFamilyWithDeleteAt() {
        Expression expression = getExpression();
        expression.setCreatedAt(LocalDateTime.now());
        expression.setUpdatedAt(LocalDateTime.now());
        expression.setDeletedAt(LocalDateTime.now());
        assertAll(
            () -> expressionSerializer.serialize(expression, jsonGenerator, serializerProvider));
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

}
