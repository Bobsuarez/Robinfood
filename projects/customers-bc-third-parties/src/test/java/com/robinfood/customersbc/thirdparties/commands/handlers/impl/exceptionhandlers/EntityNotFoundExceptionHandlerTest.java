package com.robinfood.customersbc.thirdparties.commands.handlers.impl.exceptionhandlers;

import com.robinfood.customersbc.thirdparties.exceptions.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
class EntityNotFoundExceptionHandlerTest {
    @InjectMocks
    private EntityNotFoundExceptionHandler entityNotFoundExceptionHandler;

    @Test
    void test_HandleException_ShouldBe_Ok_When_ExceptionIsManaged() {
        assertAll(
            () -> entityNotFoundExceptionHandler.handleException(
                new EntityNotFoundException("AccessDeniedException", "message")
            )
        );
    }
}
