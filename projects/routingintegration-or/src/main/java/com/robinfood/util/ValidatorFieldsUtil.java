package com.robinfood.util;

import com.robinfood.constants.HttpStatusConstant;
import com.robinfood.exceptions.FieldsValidateOrRequiredException;
import com.robinfood.mappers.ResponseMapper;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

import static com.robinfood.constants.Constants.STORE_ID;

public class ValidatorFieldsUtil {

    @SneakyThrows
    public static void validateNullFields(Object entity) {
        for (Field field : entity.getClass().getDeclaredFields()) {

            field.setAccessible(true);

            if (!field.getName().equals(STORE_ID) && field.get(entity) == null) {
                throw new FieldsValidateOrRequiredException(ResponseMapper
                        .buildWithError(HttpStatusConstant.SC_PRECONDITION_FAILED.getCodeHttp(),
                                field.getName() + " is required",
                                Boolean.TRUE), field.getName() + " is required");
            }

        }
    }
}
