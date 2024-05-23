package com.robinfood.customersbc.thirdparties.commands.handlers.impl.exceptionhandlers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ServerWebInputException;

import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
class ServerWebInputExceptionHandlerTest {
    @InjectMocks
    private ServerWebInputExceptionHandler serverWebInputExceptionHandler;

    @Test
    void test_HandleException_ShouldBe_ResponseForServerWebInputException_When_ExceptionIsManaged() {
        assertAll(
            () -> serverWebInputExceptionHandler.handleException(new ServerWebInputException("ServerWebInputException"))
        );
    }
}
