package com.robinfood.util;

import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.robinfood.constants.HttpStatusConstant;
import com.robinfood.exceptions.FieldsValidateOrRequiredException;
import com.robinfood.mappers.ResponseMapper;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidatorFieldsUtil {

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

    @SneakyThrows
    public static void validateAttributes(Map<String, String> dataAttributes, String... attributes) {

        List<String> fieldsToValidate = Arrays.asList(attributes);

        for (String field : fieldsToValidate) {
            if (dataAttributes.containsKey(field)) {
                continue;
            }
            throw new FieldsValidateOrRequiredException(ResponseMapper
                    .buildWithError(HttpStatusConstant.SC_PRECONDITION_FAILED.getCodeHttp(),
                            field + " is required",
                            Boolean.TRUE), field + " is required");

        }
    }

    @SneakyThrows
    public static Map<String, String> validateAttributesSQS(Map<String, SQSEvent.MessageAttribute> dataAttributes, String... attributes) {

        Map<String, String> result = new HashMap<>();
        List<String> fieldsToValidate = Arrays.asList(attributes);

        for (String field : fieldsToValidate) {
            if (dataAttributes.containsKey(field)) {
                result.put(field, dataAttributes.get(field).getStringValue());
                continue;
            }
            throw new FieldsValidateOrRequiredException(ResponseMapper
                    .buildWithError(HttpStatusConstant.SC_PRECONDITION_FAILED.getCodeHttp(),
                            field + " is required",
                            Boolean.TRUE), field + " is required");

        }
        return result;
    }
}
