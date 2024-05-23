package com.robinfood.taxes.serializers.tax;

import static org.junit.jupiter.api.Assertions.assertAll;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.robinfood.taxes.models.Family;
import com.robinfood.taxes.models.FamilyType;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FamilySerializerTest {

    @InjectMocks
    private FamilySerializer familySerializer;

    @Mock
    private JsonGenerator jsonGenerator;

    @Mock
    private SerializerProvider serializerProvider;


    @Test
    void test_FamilySerializer_Should_Serialize_When_ReceiveValidFamily() {
        Family family = getFamily();
        family.setId(1L);
        family.setCreatedAt(LocalDateTime.now());
        family.setUpdatedAt(LocalDateTime.now());
        assertAll(() -> familySerializer.serialize(family, jsonGenerator, serializerProvider));
    }

    @Test
    void test_FamilySerializer_Should_Serializer_When_ReceiveValidFamilyWithDeleteAt() {
        Family family = getFamily();
        family.setId(1L);
        family.setCreatedAt(LocalDateTime.now());
        family.setUpdatedAt(LocalDateTime.now());
        family.setDeletedAt(LocalDateTime.now());
        assertAll(() -> familySerializer.serialize(family, jsonGenerator, serializerProvider));
    }

    private Family getFamily() {
        return Family.builder()
            .name("test")
            .countryId(1L)
            .familyType(new FamilyType(1L))
            .status(1)
            .build();
    }

}
