package com.robinfood.core.validations;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_DOUBLE_VALUE;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_CLASS_VALUE;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_LONG_VALUE;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_STRING_VALUE;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * Implements of ConstraintValidator
 */
@Slf4j
public class IsAttributeFalseValidation implements ConstraintValidator<IsAttributeFalse, Object> {

    private String field;
    private String[] required;
    private String message;

    public IsAttributeFalseValidation(String field, String[] required, String message) {
        this.field = field;
        this.required = required.clone();
        this.message = message;
    }

    public IsAttributeFalseValidation() {
    }

    @Override
    public void initialize(IsAttributeFalse constraintAnnotation) {
        field = constraintAnnotation.field();
        required = constraintAnnotation.required();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object objectToValidate, ConstraintValidatorContext context) {

        boolean valid = true;

        try {
            boolean value = (Boolean) getFieldValue(objectToValidate, field);

            if (value) {
                for (String requiredField : required) {
                    Object requiredValue = getFieldValue(objectToValidate, requiredField);

                    String requiredClassValue = requiredValue.getClass().getSimpleName();

                    valid = isNotNullRequiredValue(requiredValue, requiredClassValue);

                    setContext(context, valid, requiredField);
                }
            }
        } catch (IllegalAccessException illegalAccessException) {
            log.error("Accessor method is not available for class : {}, exception : {}",
                    objectToValidate.getClass().getName(), illegalAccessException);
            return false;
        } catch (InvocationTargetException invocationTargetException) {
            log.error("Accessor field is not available for class : {}, exception : {}",
                    objectToValidate.getClass().getName(), invocationTargetException);
        }

        return valid;
    }

    private static boolean isNotNullRequiredValue(Object requiredValue, String requiredClassValue) {

        boolean isNotNullRequiredValue;
        switch (requiredClassValue) {
            case "String":
                isNotNullRequiredValue = !DEFAULT_STRING_VALUE.equals(requiredValue);
                break;
            case "Double":
                isNotNullRequiredValue = !DEFAULT_DOUBLE_VALUE.equals(requiredValue);
                break;
            case "Long":
                isNotNullRequiredValue = !DEFAULT_LONG_VALUE.equals(requiredValue);
                break;
            case "Boolean":
                isNotNullRequiredValue = !Boolean.FALSE.equals(requiredValue);
                break;
            case "Integer":
                isNotNullRequiredValue = !DEFAULT_INTEGER_CLASS_VALUE.equals(requiredValue);
                break;
            default:
                isNotNullRequiredValue = false;
        }
        return isNotNullRequiredValue;
    }

    private void setContext(ConstraintValidatorContext context, boolean valid, String requiredField) {

        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(requiredField)
                    .addConstraintViolation();
        }
    }

    public Object getFieldValue(Object objectToValidate, String field)
            throws InvocationTargetException, IllegalAccessException {

        PropertyDescriptor propertyDescriptor = BeanUtils.getPropertyDescriptor(
                objectToValidate.getClass(), field);

        if (propertyDescriptor == null) {
            throw new IllegalArgumentException("No such property " + field
                    + " for " + objectToValidate.getClass() + " exists");
        }

        Method readMethod = propertyDescriptor.getReadMethod();
        if (readMethod == null) {
            throw new IllegalStateException("No getter available for property "
                    + field + " on " + objectToValidate.getClass());
        }

        return readMethod.invoke(objectToValidate);
    }
}
