package com.robinfood.configurations.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.robinfood.configurations.models.Pos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PosSerializerTest {

    private static final String TEST = "TEST";
    private static final Long TEST_LONG = 1L;
    private static final LocalDateTime CURRENT_DATE = LocalDateTime.now();

    @InjectMocks
    private PosSerializer serializer;

    @Mock
    private JsonGenerator jsonGenerator;

    @Mock
    private SerializerProvider serializerProvider;

    private Pos data;

    @BeforeEach
    public void setUp() {
        data = Pos.builder()
            .id(TEST_LONG)
            .createdAt(CURRENT_DATE)
            .updatedAt(CURRENT_DATE)
            .deletedAt(null)
            .name(TEST)
            .code(TEST)
            .build();
    }

    @Test
    public void test_Serializer_Should_Serialize_When_ReceiveValidModel() {
        assertAll(() -> serializer.serialize(data, jsonGenerator, serializerProvider));
    }

    @Test
    public void test_Serializer_Should_Serialize_When_ReceiveMultipleData() {
        data.setDeletedAt(CURRENT_DATE);
        assertAll(() -> serializer.serialize(data, jsonGenerator, serializerProvider));
    }

    @Test
    public void test_When_ConstructorIsPrivate() throws Exception {
        Constructor<PosSerializer> constructor = PosSerializer.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }
}