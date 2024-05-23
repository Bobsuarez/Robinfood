package com.robinfood.taxes.serializers.tax;

import static org.junit.jupiter.api.Assertions.assertAll;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.robinfood.taxes.models.TaxType;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TaxTypeSerializerTest {

    @InjectMocks
    private TaxTypeSerializer taxSerializer;

    @Mock
    private JsonGenerator jsonGenerator;

    @Mock
    private SerializerProvider serializerProvider;


    @Test
    void test_TaxSerializer_Should_Serialize_When_ReceiveValidTax() {
        TaxType tax = getTax();
        tax.setId(1L);
        tax.setCreatedAt(LocalDateTime.now());
        tax.setUpdatedAt(LocalDateTime.now());
        assertAll(() -> taxSerializer.serialize(tax, jsonGenerator, serializerProvider));
    }

    @Test
    void test_TaxSerializer_Should_Serializer_When_ReceiveValidTaxWithDeleteAt() {
        TaxType tax = getTax();
        tax.setId(1L);
        tax.setCreatedAt(LocalDateTime.now());
        tax.setUpdatedAt(LocalDateTime.now());
        tax.setDeletedAt(LocalDateTime.now());
        assertAll(() -> taxSerializer.serialize(tax, jsonGenerator, serializerProvider));
    }

    private TaxType getTax() {
        return TaxType.builder()
            .name("test")
            .countryId(1L)
            .status(1)
            .build();
    }

}
