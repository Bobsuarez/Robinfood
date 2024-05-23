package com.robinfood.customersbc.thirdparties.commands.handlers.impl.exceptionhandlers;

import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
class SignatureExceptionHandlerTest {
    @InjectMocks
    private SignatureExceptionHandler signatureExceptionHandler;

    @Test
    void test_HandleException_ShouldBe_ResponseForSignatureException_When_ExceptionIsManaged() {
        assertAll(
            () -> signatureExceptionHandler.handleException(new SignatureException("SignatureException"))
        );
    }
}