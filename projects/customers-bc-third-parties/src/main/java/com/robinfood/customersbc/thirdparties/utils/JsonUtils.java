package com.robinfood.customersbc.thirdparties.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.customersbc.thirdparties.mappers.utils.ObjectMapperUtils;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class JsonUtils {

    private JsonUtils() {}

    public static String convertToJson(@NotNull final Object data) {
        try {
            return ObjectMapperUtils.getObjectMapper().writeValueAsString(data);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
