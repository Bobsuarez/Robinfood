package com.robinfood.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.robinfood.constants.HttpStatusConstant;
import com.robinfood.exceptions.FieldsValidateOrRequiredException;
import com.robinfood.mappers.ResponseDTOMapper;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static com.robinfood.constants.Constants.STORE_ID;
import static com.robinfood.constants.Constants.VALUE_NULL;

public class ValidatorFieldsUtil {

    private ValidatorFieldsUtil() {
        throw new IllegalStateException("Utility class");
    }

    @SneakyThrows
    public static void validateNullFields(Object entity, String... fieldList) {

        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            List<String> fieldsToValidate = Arrays.asList(fieldList);
            if (!fieldsToValidate.contains(field.getName())) {
                continue;
            }
            if (field.get(entity) == null) {
                throw new FieldsValidateOrRequiredException(ResponseDTOMapper
                        .buildWithError(HttpStatusConstant.SC_PRECONDITION_FAILED.getCodeHttp(),
                                field.getName() + " is required",
                                Boolean.TRUE), field.getName() + " is required");
            }
        }
    }

    @SneakyThrows
    public static Long getStoreId(String body) {

        JsonNode toJsonNode = ObjectMapperSingletonUtil.stringToJsonNode(body);
        if (toJsonNode.has(STORE_ID) && !toJsonNode.get(STORE_ID).toString().equals(VALUE_NULL)) {
            return Long.parseLong(toJsonNode.get(STORE_ID).toString());
        }
        return null;
    }
}
