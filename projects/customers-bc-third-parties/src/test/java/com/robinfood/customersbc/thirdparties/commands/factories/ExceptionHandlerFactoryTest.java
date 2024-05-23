package com.robinfood.customersbc.thirdparties.commands.factories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
class ExceptionHandlerFactoryTest {
    @InjectMocks
    private ExceptionHandlerFactory exceptionHandlerFactory;

    @Test
    void test_GetExceptionHandler_ShouldBe_ExceptionHandler_When_MethodClassIsValid() {

        assertAll(() -> ExceptionHandlerFactory.getExceptionHandler(""));
    }
}
