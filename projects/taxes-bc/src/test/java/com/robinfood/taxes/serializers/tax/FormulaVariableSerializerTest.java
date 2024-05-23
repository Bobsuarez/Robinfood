package com.robinfood.taxes.serializers.tax;

import static org.junit.jupiter.api.Assertions.assertAll;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.robinfood.taxes.models.Expression;
import com.robinfood.taxes.models.Family;
import com.robinfood.taxes.models.FormulaVariable;
import com.robinfood.taxes.models.Tax;
import com.robinfood.taxes.models.TaxType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName.Form;

@ExtendWith(MockitoExtension.class)
class FormulaVariableSerializerTest {

    @InjectMocks
    private FormulaVariableSerializer formulaVariableSerializer;

    @Mock
    private JsonGenerator jsonGenerator;

    @Mock
    private SerializerProvider serializerProvider;


    @Test
    void test_FormulaVariableSerializer_Should_Serialize_When_ReceiveValidTax() {
        FormulaVariable formulaVariable = getFormulaVariable();
        formulaVariable.setId(1L);
        formulaVariable.setCreatedAt(LocalDateTime.now());
        formulaVariable.setUpdatedAt(LocalDateTime.now());
        assertAll(() -> formulaVariableSerializer
            .serialize(formulaVariable, jsonGenerator, serializerProvider));
    }

    @Test
    void test_TaxSerializer_Should_Serializer_When_ReceiveValidTaxWithDeleteAt() {
        FormulaVariable formulaVariable = getFormulaVariable();
        formulaVariable.setId(1L);
        formulaVariable.setCreatedAt(LocalDateTime.now());
        formulaVariable.setUpdatedAt(LocalDateTime.now());
        formulaVariable.setDeletedAt(LocalDateTime.now());
        assertAll(() -> formulaVariableSerializer
            .serialize(formulaVariable, jsonGenerator, serializerProvider));
    }

    private FormulaVariable getFormulaVariable() {
        return FormulaVariable.builder()
            .name("Name")
            .description("Desc")
            .type("Type")
            .value("Val")
            .build();
    }

}
