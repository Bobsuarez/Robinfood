package com.robinfood.util;

import com.robinfood.constants.HttpStatusConstants;
import com.robinfood.exceptions.FieldsValidateOrRequiredException;
import com.robinfood.mappers.ResponseMapper;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

class ValidatorFieldsUtil {

    @SneakyThrows
    public static void validateNullFields(Object entity) {
        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.get(entity) == null) {
                throw new FieldsValidateOrRequiredException(ResponseMapper
                        .buildWithError(HttpStatusConstants.SC_PRECONDITION_FAILED.getCodeHttp(),
                                field.getName() + " is required",
                                Boolean.TRUE), field.getName() + " is required");
            }
        }
    }
}
