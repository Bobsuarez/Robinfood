package com.robinfood.taxes.serializers.tax;

import static org.junit.jupiter.api.Assertions.assertAll;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.robinfood.taxes.models.RuleVariable;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RuleVariableSerializerTest {

    @InjectMocks
    private RuleVariableSerializer ruleVariableSerializer;

    @Mock
    private JsonGenerator jsonGenerator;

    @Mock
    private SerializerProvider serializerProvider;


    @Test
    void test_RuleVariableSerializer_Should_Serialize_When_ReceiveValidRuleVariable() {
        RuleVariable ruleVariable = getRuleVariable();
        ruleVariable.setId(1L);
        ruleVariable.setCreatedAt(LocalDateTime.now());
        ruleVariable.setUpdatedAt(LocalDateTime.now());

        assertAll(() -> ruleVariableSerializer.serialize(ruleVariable, jsonGenerator, serializerProvider));
    }

    @Test
    void test_RuleVariableSerializer_Should_Serializer_When_ReceiveValidRuleVariableWithDeleteAt() {
        RuleVariable ruleVariable = getRuleVariable();
        ruleVariable.setId(1L);
        ruleVariable.setCreatedAt(LocalDateTime.now());
        ruleVariable.setUpdatedAt(LocalDateTime.now());
        ruleVariable.setDeletedAt(LocalDateTime.now());
        assertAll(() -> ruleVariableSerializer.serialize(ruleVariable, jsonGenerator, serializerProvider));
    }

    private RuleVariable getRuleVariable() {
        return RuleVariable.builder()
            .name("Test Name Rule Variable")
            .description("Test Description Rule Variable")
            .type("ArrayString")
            .path(null)
            .value("Test null")
            .build();
    }

}
