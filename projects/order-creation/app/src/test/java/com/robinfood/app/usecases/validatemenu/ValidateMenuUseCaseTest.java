package com.robinfood.app.usecases.validatemenu;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationResult;
import com.robinfood.core.models.domain.configuration.StoreInformation;
import com.robinfood.repository.menu.IMenuRepository;

import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ValidateMenuUseCaseTest {

    @Mock
    private IMenuRepository mockMenuRepository;

    @InjectMocks
    private ValidateMenuUseCase validateMenuUseCase;

    private TransactionRequestDTO transactionRequest;

    final String token = "token";

    @BeforeEach
    void setUp() {
        transactionRequest = new TransactionRequestDTOMock().transactionRequestDTO;
        transactionRequest.setStoreInfo(StoreInformation.builder()
                .timezone("America/Bogota")
                .build()
        );
    }

    @Test
    void test_When_Some_Validation_Fails_Internally() {

        when(mockMenuRepository.validateMenu(
                anyString(),
                any()
        )).thenReturn(CompletableFuture.failedFuture(new Exception("Some exception")));

        try {
            validateMenuUseCase.invoke(token, transactionRequest).join();
        } catch (Exception exception) {
            assertTrue(exception.getLocalizedMessage().contains("Some exception"));
        }
    }

    @Test
    void test_When_All_Menu_Products_Are_Valid_Happy_Path() {

        when(mockMenuRepository.validateMenu(
                anyString(),
                any()
        )).thenReturn(CompletableFuture.completedFuture(true));

        final TransactionCreationResult result = validateMenuUseCase.invoke(token, transactionRequest).join();

        assertTrue(result instanceof TransactionCreationResult.StepValidationSuccess);
    }
}
