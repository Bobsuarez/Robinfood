package com.robinfood.taxes.components;

import static org.junit.jupiter.api.Assertions.assertAll;
import com.robinfood.taxes.components.impl.AuthenticationFacadeImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuthenticationFacadeImplTest {

    @InjectMocks
    private AuthenticationFacadeImpl authenticationFacadeImplTest;

    @Test
    void test_RestControllerExceptionHandler_Should_BusinessRuleException_When_Invoked() throws Exception {
        assertAll(() -> authenticationFacadeImplTest.getAuthentication());

    }
}
