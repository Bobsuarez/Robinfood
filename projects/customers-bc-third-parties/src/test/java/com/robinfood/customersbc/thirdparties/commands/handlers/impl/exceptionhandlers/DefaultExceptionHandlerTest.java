package com.robinfood.customersbc.thirdparties.commands.handlers.impl.exceptionhandlers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
class DefaultExceptionHandlerTest {
    @InjectMocks
    private DefaultExceptionHandler defaultExceptionHandler;

    @Test
    void test_HandleException_ShouldBe_ResponseForException_When_ExceptionIsManaged() {
        assertAll(() -> defaultExceptionHandler.handleException(new Exception("Exception")));
    }
}
