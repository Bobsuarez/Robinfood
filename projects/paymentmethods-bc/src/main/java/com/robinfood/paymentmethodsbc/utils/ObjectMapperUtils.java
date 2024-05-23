package com.robinfood.paymentmethodsbc.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.robinfood.paymentmethodsbc.dto.api.creditcards.CreateCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.model.CreditCard;
import com.robinfood.paymentmethodsbc.serializers.CreateCreditCardRequestDTOSerializer;
import com.robinfood.paymentmethodsbc.serializers.CreditCardSerializer;

import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_FLOAT_AS_INT;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.MapperFeature.ALLOW_COERCION_OF_SCALARS;
import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

public final class ObjectMapperUtils {
    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(CreateCreditCardRequestDTO.class, new CreateCreditCardRequestDTOSerializer());
        module.addSerializer(CreditCard.class, new CreditCardSerializer());
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(module);
        objectMapper.configure(WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(ACCEPT_FLOAT_AS_INT, false);
        objectMapper.configure(ALLOW_COERCION_OF_SCALARS, false);
        objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private ObjectMapperUtils() {}

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
