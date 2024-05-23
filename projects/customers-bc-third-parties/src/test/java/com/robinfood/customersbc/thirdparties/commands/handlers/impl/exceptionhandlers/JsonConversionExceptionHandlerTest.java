package com.robinfood.customersbc.thirdparties.commands.handlers.impl.exceptionhandlers;

import com.robinfood.customersbc.thirdparties.exceptions.JsonConversionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
class JsonConversionExceptionHandlerTest {
    @InjectMocks
    private JsonConversionExceptionHandler jsonConversionExceptionHandler;

    @Test
    void test_HandleException_ShouldBe_ResponseForJsonConvertionException_When_ExceptionIsManaged() {
        assertAll(
            () -> jsonConversionExceptionHandler.handleException(new JsonConversionException("type", "message"))
        );
    }
}
