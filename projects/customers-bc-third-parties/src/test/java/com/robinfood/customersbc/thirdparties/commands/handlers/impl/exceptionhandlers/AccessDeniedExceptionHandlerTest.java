package com.robinfood.customersbc.thirdparties.commands.handlers.impl.exceptionhandlers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.access.AccessDeniedException;

import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
class AccessDeniedExceptionHandlerTest {
    @InjectMocks
    private AccessDeniedExceptionHandler accessDeniedExceptionHandler;

    @Test
    void test_HandleException_ShouldBe_ResponseForAccessDeniedException_When_ExceptionIsManaged() {
        assertAll(
            () -> accessDeniedExceptionHandler.handleException(new AccessDeniedException("AccessDeniedException"))
        );
    }
}
