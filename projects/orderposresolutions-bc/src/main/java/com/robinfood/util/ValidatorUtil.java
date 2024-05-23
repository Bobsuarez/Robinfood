package com.robinfood.util;

import com.robinfood.exceptions.FieldsValidateOrRequiredException;
import com.robinfood.mappers.ResponseMapper;
import org.apache.http.HttpStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidatorUtil {

    public static final Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    private ValidatorUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void validate(Object object) {
        try {
            Set<ConstraintViolation<Object>> validations = validator.validate(object);
            if (!validations.isEmpty()) {
                throw new ConstraintViolationException(validations);
            }
        } catch (ConstraintViolationException violationException) {

            throw new FieldsValidateOrRequiredException(ResponseMapper
                    .buildWithError(HttpStatus.SC_PRECONDITION_FAILED,
                            violationException.getMessage(),
                            Boolean.TRUE
                    ), violationException.getMessage());
        }
    }
}
