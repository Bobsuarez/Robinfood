package com.robinfood.customersbc.thirdparties.commands.handlers.impl.exceptionhandlers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.lang.reflect.Method;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
class WebExchangeBindExceptionHandlerTest {
    @InjectMocks
    private WebExchangeBindExceptionHandler exchangeBindExceptionHandler;

    @Test
    void test_HandleException_ShouldBe_ResponseForWebExchangeBindException_When_ExceptionIsManaged() throws NoSuchMethodException {
        BindingResult bindingResult = new MapBindingResult(
            new HashMap<>(), "WebExchangeBindExceptionHandler");
        Method method = exchangeBindExceptionHandler.getClass().getMethod("handleException", Throwable.class);
        MethodParameter parameter = new MethodParameter(method, 0);
        WebExchangeBindException exception = new WebExchangeBindException(parameter, bindingResult);

        assertAll(() -> exchangeBindExceptionHandler.handleException(exception));
    }
}
