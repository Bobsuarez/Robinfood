package com.robinfood.app.usecases.hasaccessinformationbyuser;

import com.robinfood.core.dtos.UserDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.exceptions.UnauthorizedAccessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test of HasAccessInformationByUserUseCase
 */
@ExtendWith(MockitoExtension.class)
public class HasAccessInformationByUserUseCaseTest {

    @InjectMocks
    private HasAccessInformationByUserUseCase useCase;

    @Test
    void testValidateAccessToInformationWithSameUserShouldBeOk() {
        UserDTO applicationUser = new UserDTO("123456", "", new HashSet<>());

        Authentication authentication = mock(Authentication.class);

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(authentication.getPrincipal()).thenReturn(applicationUser);

        assertAll(() -> useCase.invoke(123456L));

        Result<Long> response = useCase.invoke(123456L);

        assertTrue(response instanceof Result.Success);
        assertEquals(123456L, ((Result.Success<Long>) response).getData());
    }

    @Test
    void testValidateAccessToInformationWithDifferentUserShouldBeError() {
        UserDTO applicationUser = new UserDTO("123456", "", new HashSet<>());

        Authentication authentication = mock(Authentication.class);

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(authentication.getPrincipal()).thenReturn(applicationUser);

        Result<Long> response = useCase.invoke(123L);

        assertTrue(response instanceof Result.Error);
        assertTrue(((Result.Error) response).getException() instanceof UnauthorizedAccessException);
    }

}
