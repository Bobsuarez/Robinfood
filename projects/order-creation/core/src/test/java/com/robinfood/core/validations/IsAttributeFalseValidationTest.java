package com.robinfood.core.validations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.robinfood.core.mocks.IsAttributeFalseMock;
import com.robinfood.core.mocks.IsAttributeFalseNoGettersMock;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext;
import org.junit.jupiter.api.Test;

class IsAttributeFalseValidationTest {

    private final IsAttributeFalse isAttributeFalse = mock(IsAttributeFalse.class);

    private final ConstraintValidatorContext constraintValidatorContext = mock(ConstraintValidatorContext.class);

    @Test
    void test_IsAttributeFalseValidation_Null() {

        when(isAttributeFalse.field()).thenReturn("isActive");
        when(isAttributeFalse.required()).thenReturn(new String[]{"statusId", "statusValue", "statusInt", "statusName",
                "isStatus", "statusData"});
        when(isAttributeFalse.message()).thenReturn("false");

        ConstraintViolationBuilder constraintViolationBuilder = mock(ConstraintViolationBuilder.class);
        NodeBuilderCustomizableContext nodeBuilderCustomizableContext = mock(NodeBuilderCustomizableContext.class);

        when(constraintViolationBuilder.addPropertyNode(anyString())).thenReturn(
                nodeBuilderCustomizableContext);

        when(constraintValidatorContext.buildConstraintViolationWithTemplate(anyString())).thenReturn(
                constraintViolationBuilder);

        when(nodeBuilderCustomizableContext.addConstraintViolation()).thenReturn(constraintValidatorContext);

        IsAttributeFalseValidation isAttributeFalseValidation = new IsAttributeFalseValidation();
        isAttributeFalseValidation.initialize(isAttributeFalse);

        IsAttributeFalseMock mock = IsAttributeFalseMock.builder()
                .isActive(Boolean.TRUE)
                .statusInt(1)
                .statusId(0L)
                .statusValue(0.0)
                .statusName("")
                .isStatus(false)
                .statusData(new Object())
                .build();

        boolean result = isAttributeFalseValidation.isValid(mock, constraintValidatorContext);

        assertFalse(result);
    }

    @Test
    void test_IsAttributeFalseValidation_Ok() {

        when(isAttributeFalse.field()).thenReturn("isActive");
        when(isAttributeFalse.required()).thenReturn(new String[]{"statusId", "statusValue", "statusInt", "statusName",
                "isStatus"});
        when(isAttributeFalse.message()).thenReturn("false");

        IsAttributeFalseValidation isAttributeFalseValidation = new IsAttributeFalseValidation();
        isAttributeFalseValidation.initialize(isAttributeFalse);

        IsAttributeFalseMock mock = IsAttributeFalseMock.builder()
                .isActive(Boolean.TRUE)
                .statusId(1L)
                .statusValue(0.2)
                .statusInt(2)
                .statusName("Hi")
                .isStatus(true)
                .build();

        boolean result = isAttributeFalseValidation.isValid(mock, constraintValidatorContext);

        assertTrue(result);
    }

    @Test
    void test_IsAttributeFalseValidation_Not_Getter() {

        when(isAttributeFalse.field()).thenReturn("isActive");
        when(isAttributeFalse.required()).thenReturn(new String[]{"statusId"});
        when(isAttributeFalse.message()).thenReturn("false");

        IsAttributeFalseValidation isAttributeFalseValidation = new IsAttributeFalseValidation();
        isAttributeFalseValidation.initialize(isAttributeFalse);

        IsAttributeFalseNoGettersMock mock = IsAttributeFalseNoGettersMock.builder()
                .isActive(Boolean.TRUE)
                .statusId(1L)
                .build();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> isAttributeFalseValidation.isValid(mock, constraintValidatorContext)
        );

        assertEquals(
                "No such property statusId for class com.robinfood.core.mocks"
                        + ".IsAttributeFalseNoGettersMock exists",
                exception.getMessage());
    }

    @Test
    void test_IsAttributeFalseValidation_Exception_IllegalAccessException() {

        when(isAttributeFalse.field()).thenReturn("isActive");
        when(isAttributeFalse.required()).thenReturn(new String[]{"statusId", "statusValue", "statusInt", "statusName",
                "isStatus", "statusData"});
        when(isAttributeFalse.message()).thenReturn("false");

        ConstraintViolationBuilder constraintViolationBuilder = mock(ConstraintViolationBuilder.class);
        NodeBuilderCustomizableContext nodeBuilderCustomizableContext = mock(NodeBuilderCustomizableContext.class);

        doAnswer(invocation -> {
            throw new IllegalAccessException();
        }).when(constraintValidatorContext).buildConstraintViolationWithTemplate(anyString());

        IsAttributeFalseValidation isAttributeFalseValidation = new IsAttributeFalseValidation();
        isAttributeFalseValidation.initialize(isAttributeFalse);

        IsAttributeFalseMock mock = IsAttributeFalseMock.builder()
                .isActive(Boolean.TRUE)
                .statusInt(1)
                .statusId(0L)
                .statusValue(0.0)
                .statusName("")
                .isStatus(false)
                .statusData(new Object())
                .build();

        boolean result = isAttributeFalseValidation.isValid(mock, constraintValidatorContext);

        assertFalse(result);
    }

    @Test
    void test_IsAttributeFalseValidation_Exception_Constructor() {

        IsAttributeFalseValidation isAttributeFalseValidation = new IsAttributeFalseValidation("isActive",
                new String[]{"statusId", "statusValue", "statusInt", "statusName",
                        "isStatus", "statusData"}, "hi");

        assertNotNull(isAttributeFalseValidation);
    }
}
