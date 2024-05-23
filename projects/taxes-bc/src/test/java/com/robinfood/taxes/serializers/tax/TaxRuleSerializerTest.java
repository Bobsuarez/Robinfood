package com.robinfood.taxes.serializers.tax;

import static org.junit.jupiter.api.Assertions.assertAll;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.robinfood.taxes.models.RuleType;
import com.robinfood.taxes.models.RuleVariable;
import com.robinfood.taxes.models.Tax;
import com.robinfood.taxes.models.TaxRule;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TaxRuleSerializerTest {

    @InjectMocks
    private TaxRuleSerializer taxRuleSerializer;

    @Mock
    private JsonGenerator jsonGenerator;

    @Mock
    private SerializerProvider serializerProvider;

    @Test
    void test_TaxRuleSerializer_Should_Serialize_When_ReceiveValidTaxRule() {
        TaxRule taxRule = getTaxRule();
        taxRule.setId(1L);
        taxRule.setCreatedAt(LocalDateTime.now());
        taxRule.setUpdatedAt(LocalDateTime.now());
        assertAll(() -> taxRuleSerializer.serialize(taxRule, jsonGenerator, serializerProvider));
    }

    @Test
    void test_TaxRuleSerializer_Should_Serializer_When_ReceiveValidFamilyWithDeleteAt() {
        TaxRule taxRule = getTaxRule();
        taxRule.setId(1L);
        taxRule.setCreatedAt(LocalDateTime.now());
        taxRule.setUpdatedAt(LocalDateTime.now());
        taxRule.setDeletedAt(LocalDateTime.now());
        assertAll(() -> taxRuleSerializer.serialize(taxRule, jsonGenerator, serializerProvider));
    }

    private TaxRule getTaxRule() {
        TaxRule taxRule = TaxRule.builder()
            .tax(new Tax(1L))
            .ruleType(new RuleType(1L))
            .leftVariable(new RuleVariable(1L))
            .rightVariable(new RuleVariable(2L))
            .status(1)
            .build();
        taxRule.setId(1L);
        return taxRule;
    }

}
