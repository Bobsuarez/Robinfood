package com.robinfood.paymentmethodsbc.utils;

import com.robinfood.paymentmethodsbc.exceptions.ErrorValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ConstraintValidationUtils {

    private ConstraintValidationUtils() {
        // ConstraintValidationUtils
    }

    public static <T> void validateObject(T object)
        throws ErrorValidationException {
        log.info("Object to validate: {}", object);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(
            object
        );

        if (!constraintViolations.isEmpty()) {
            List<String> errorList = new ArrayList<>();
            constraintViolations
                .stream()
                .forEach(
                    (ConstraintViolation<T> obj) ->
                        errorList.add(
                            String.format(
                                "Error OrderService %s: %s",
                                obj.getPropertyPath(),
                                obj.getMessage()
                            )
                        )
                );

            throw new ErrorValidationException(
                "ConstraintViolation",
                errorList.toString()
            );
        }
    }
}
