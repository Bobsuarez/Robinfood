package com.robinfood.customersbc.thirdparties.commands.handlers.impl.exceptionhandlers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.AuthenticationException;

import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
class AuthenticationExceptionHandlerTest {
    @InjectMocks
    private AuthenticationExceptionHandler authenticationExceptionHandler;

    @Test
    void test_HandleException_ShouldBe_ResponseForAuthenticationException_When_ExceptionIsManaged() {
        assertAll(() ->
            authenticationExceptionHandler.handleException(new AgAuthenticationException("AuthenticationException"))
        );
    }

    private static class AgAuthenticationException extends AuthenticationException {

        public AgAuthenticationException(String msg) {
            super(msg);
        }
    }
}
