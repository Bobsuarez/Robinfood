package com.robinfood.customersbc.thirdparties.mappers.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;

import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_FLOAT_AS_INT;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.MapperFeature.ALLOW_COERCION_OF_SCALARS;
import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

public final class ObjectMapperUtils {
    @Getter
    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(ACCEPT_FLOAT_AS_INT, false);
        objectMapper.configure(ALLOW_COERCION_OF_SCALARS, false);
        objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private ObjectMapperUtils() {}

}
