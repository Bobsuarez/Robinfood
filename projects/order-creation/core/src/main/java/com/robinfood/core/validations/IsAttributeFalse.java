package com.robinfood.core.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * If The annotated class has the field specified true, the fields on required
 * attribute can't be null
 */
@Repeatable(Conditionals.class)
@Constraint(validatedBy = {IsAttributeFalseValidation.class})
@Target({
        ElementType.TYPE
})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsAttributeFalse {

    /**
     * Return the message if it was specified, if not return
     * default message
     *
     * @return message validation
     */
    String message() default "This field is required.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * Return the field to validate if is true or false
     *
     * @return field for validation
     */
    String field();

    /**
     * Return the fields that are required if attribute field
     * is true
     *
     * @return required fields
     */
    String[] required();
}
