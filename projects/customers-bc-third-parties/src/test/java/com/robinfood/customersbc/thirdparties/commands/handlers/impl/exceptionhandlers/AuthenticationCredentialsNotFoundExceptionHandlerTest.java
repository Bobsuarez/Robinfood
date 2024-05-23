package com.robinfood.customersbc.thirdparties.commands.handlers.impl.exceptionhandlers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
class AuthenticationCredentialsNotFoundExceptionHandlerTest {
    @InjectMocks
    private AuthenticationCredentialsNotFoundExceptionHandler authenticationCredentialsNotFoundExceptionHandler;

    @Test
    void test_HandleException_ShouldBe_ResponseForAuthenticationCredentialsNotFoundException_When_ExceptionIsManaged() {
        assertAll(
            () -> authenticationCredentialsNotFoundExceptionHandler.handleException(
                new AuthenticationCredentialsNotFoundException("AuthenticationCredentialsNotFoundException")
            )
        );
    }
}
