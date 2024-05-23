package com.robinfood.taxes.serializers.tax;

import static org.junit.jupiter.api.Assertions.assertAll;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.robinfood.taxes.models.Expression;
import com.robinfood.taxes.models.Family;
import com.robinfood.taxes.models.Tax;
import com.robinfood.taxes.models.TaxType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TaxSerializerTest {

    @InjectMocks
    private TaxSerializer taxSerializer;

    @Mock
    private JsonGenerator jsonGenerator;

    @Mock
    private SerializerProvider serializerProvider;


    @Test
    void test_TaxSerializer_Should_Serialize_When_ReceiveValidTax() {
        Tax tax = getTax();
        tax.setId(1L);
        tax.setCreatedAt(LocalDateTime.now());
        tax.setUpdatedAt(LocalDateTime.now());
        assertAll(() -> taxSerializer.serialize(tax, jsonGenerator, serializerProvider));
    }

    @Test
    void test_TaxSerializer_Should_Serializer_When_ReceiveValidTaxWithDeleteAt() {
        Tax tax = getTax();
        tax.setId(1L);
        tax.setCreatedAt(LocalDateTime.now());
        tax.setUpdatedAt(LocalDateTime.now());
        tax.setDeletedAt(LocalDateTime.now());
        assertAll(() -> taxSerializer.serialize(tax, jsonGenerator, serializerProvider));
    }

    private Tax getTax() {
        return Tax.builder()
            .value(BigDecimal.valueOf(10))
            .name("test")
            .description("test")
            .applyRules(true)
            .sapId("123")
            .family(new Family(1L))
            .taxType(new TaxType(1L))
            .expression(new Expression(1L))
            .status(1)
            .build();
    }

}
