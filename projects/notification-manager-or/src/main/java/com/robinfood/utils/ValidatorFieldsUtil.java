package com.robinfood.utils;

import com.robinfood.constants.HttpStatusConstant;
import com.robinfood.exceptions.FieldsValidateOrRequiredException;
import com.robinfood.mappers.ResponseMapper;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

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
                throw new FieldsValidateOrRequiredException(ResponseMapper
                        .buildWithError(HttpStatusConstant.SC_PRECONDITION_FAILED.getCodeHttp(),
                                field.getName() + " is required",
                                Boolean.TRUE), field.getName() + " is required");
            }
        }
    }
}
