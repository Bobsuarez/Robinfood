package com.robinfood.paymentmethodsbc.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import javax.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import static com.robinfood.paymentmethodsbc.utils.ObjectMapperUtils.getObjectMapper;

@Slf4j
public final class JsonUtils {

    private JsonUtils() {}

    public static String convertToJson(@NotNull final Object data) {
        try {
            return getObjectMapper().writeValueAsString(data);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static <T> T jsonToObject(
        @NonNull String json,
        Class<T> classToConvert
    ) throws JsonProcessingException {
        return getObjectMapper().readValue(json, classToConvert);
    }
}
