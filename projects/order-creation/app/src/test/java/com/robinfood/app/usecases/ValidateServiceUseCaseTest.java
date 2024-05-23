package com.robinfood.app.usecases;

import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.app.usecases.validateservice.ValidateServiceUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationResult;
import com.robinfood.repository.services.IServicesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidateServiceUseCaseTest {

    @Mock
    private IServicesRepository servicesRepository;

    @InjectMocks
    private ValidateServiceUseCase validateServiceUseCase;

    private final TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTOMock().transactionRequestDTO;

    private final TransactionRequestDTO transactionRequestDTOWithOutService = new TransactionRequestDTOMock()
            .transactionRequestDTOWithOutServices;

    private final TransactionRequestDTO transactionRequestDTOServices = new TransactionRequestDTOMock()
            .transactionRequestDTOWithServices;

    private final String token = "token";

    @Test
    void given_Service_When_Consume_Then_Validate() {

        when(servicesRepository.validateService(anyString(), anyList(), any())).thenReturn(
                CompletableFuture.completedFuture(Boolean.TRUE)
        );

        TransactionCreationResult result = validateServiceUseCase.invoke(token, transactionRequestDTO).join();

        assertTrue(result instanceof TransactionCreationResult.StepValidationSuccess);
    }

    @Test
    void given_OrderWithOutService_When_Consume_Then_Validate() {

        TransactionCreationResult result = validateServiceUseCase.invoke(token, transactionRequestDTOWithOutService)
                .join();

        assertTrue(result instanceof TransactionCreationResult.StepValidationSuccess);
    }

    @Test
    void given_Service_When_Consume_Then_Validate_With_Services() {

        TransactionCreationResult result = validateServiceUseCase.invoke(token, transactionRequestDTOServices).join();

        assertTrue(result instanceof TransactionCreationResult.StepValidationSuccess);
    }

    @Test
    void test_Params_Is_Null() {
        assertThrows(
                NullPointerException.class,
                () -> validateServiceUseCase.invoke(null, null)
        );
    }
}
