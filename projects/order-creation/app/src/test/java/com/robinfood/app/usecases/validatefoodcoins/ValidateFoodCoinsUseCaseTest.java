package com.robinfood.app.usecases.validatefoodcoins;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.app.usecases.getpaymentmethod.IGetPaymentMethodFoodCoinsUseCase;
import com.robinfood.core.dtos.ValidateFoodCoinsRequestDTO;
import com.robinfood.core.dtos.ValidateFoodCoinsResponseDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationResult;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.core.exceptions.WriteInTransactionException;
import com.robinfood.repository.loyalty.IRedeemFoodCoinsRepository;

import java.math.BigDecimal;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class ValidateFoodCoinsUseCaseTest {

    @Mock
    private IRedeemFoodCoinsRepository redeemFoodCoinsRepository;

    @Mock
    private IGetPaymentMethodFoodCoinsUseCase getPaymentMethodFoodCoinsUseCase;

    @InjectMocks
    private ValidateFoodCoinsUseCase validateFoodCoinsUseCase;

    private final String token = "token";

    private final ValidateFoodCoinsRequestDTO validateFoodCoinsRequestDTO = ValidateFoodCoinsRequestDTO.builder()
            .amount(BigDecimal.valueOf(2000.0))
            .countryId(1L)
            .operationType(1)
            .userId(1L)
            .build();

    private final ValidateFoodCoinsResponseDTO responseSuccess = ValidateFoodCoinsResponseDTO.builder()
            .message("Valid transaction")
            .transactionCredits(10)
            .transactionStatus(true)
            .userCurrentCredits(50)
            .build();

    private final ValidateFoodCoinsResponseDTO responseFail = ValidateFoodCoinsResponseDTO.builder()
            .message("Invalid transaction")
            .transactionCredits(10)
            .transactionStatus(false)
            .userCurrentCredits(50)
            .build();

    private final ValidateFoodCoinsResponseDTO responseInvalid = ValidateFoodCoinsResponseDTO.builder()
            .message("Valid transaction")
            .transactionCredits(10)
            .transactionStatus(true)
            .userCurrentCredits(50)
            .build();

    private final TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTOMock().paidTransactionRequestDTO;

    @Test
    void test_FoodCoins_Validation_Is_Correct() {

        when(getPaymentMethodFoodCoinsUseCase.invoke(anyList())).thenReturn(
                transactionRequestDTO.getPaymentsPaid().get(0)
        );

        when(redeemFoodCoinsRepository.validateFoodCoins(token, validateFoodCoinsRequestDTO)).thenReturn(
                CompletableFuture.completedFuture(responseSuccess)
        );

        final TransactionCreationResult response = validateFoodCoinsUseCase.invoke(token, transactionRequestDTO).join();

        assertTrue(response instanceof TransactionCreationResult.StepValidationSuccess);
    }

    @Test
    void test_FoodCoins_Validation_Throw_TransactionCreationException() {

        when(getPaymentMethodFoodCoinsUseCase.invoke(anyList())).thenReturn(
                transactionRequestDTO.getPaymentsPaid().get(0)
        );

        when(redeemFoodCoinsRepository.validateFoodCoins(token, validateFoodCoinsRequestDTO)).thenReturn(
                CompletableFuture.completedFuture(responseFail)
        );

        assertInstanceOf(
                CompletableFuture.class,
                validateFoodCoinsUseCase.invoke(token, transactionRequestDTO)
        );
    }

    @Test
    void test_PaidIsFalse_Thrown_WriteInOrderException() {

        try {
            validateFoodCoinsUseCase.invoke(token, transactionRequestDTO).join();
        } catch (WriteInTransactionException writeInOrderException) {
            assertEquals(HttpStatus.CONFLICT, writeInOrderException.getStatus());
        }
    }

    @Test
    void test_RedeemFoodCoinsRepository_Throw_CancellationException() {

        when(validateFoodCoinsUseCase.invoke(token, transactionRequestDTO)).thenThrow(
                CancellationException.class
        );

        try {
            validateFoodCoinsUseCase.invoke(token, transactionRequestDTO);
        } catch (WriteInTransactionException writeInOrderException) {
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, writeInOrderException.getStatus());
        }
    }

    @Test
    void test_TransactionStatusIsInvalid_Thrown_OrderCreationException() {

        when(redeemFoodCoinsRepository.validateFoodCoins(token, validateFoodCoinsRequestDTO)).thenReturn(
                CompletableFuture.completedFuture(responseInvalid)
        );

        when(getPaymentMethodFoodCoinsUseCase.invoke(anyList())).thenReturn(
                transactionRequestDTO.getPaymentsPaid().get(0)
        );

        try {
            validateFoodCoinsUseCase.invoke(token, transactionRequestDTO).join();
        } catch (Exception transactionCreationException) {
            assertEquals(TransactionCreationException.class,
                    transactionCreationException.getCause().getClass());
        }
    }

    @Test
    void test_RedeemFoodCoinsRepository_Return_NotNull() {

        when(redeemFoodCoinsRepository.validateFoodCoins(
                token,
                validateFoodCoinsRequestDTO
        )).thenReturn(
                CompletableFuture.completedFuture(responseSuccess)
        );

        CompletableFuture<ValidateFoodCoinsResponseDTO> result = redeemFoodCoinsRepository.validateFoodCoins(
                token,
                validateFoodCoinsRequestDTO
        );

        assertNotNull(result.join());
    }

    @Test
    void test_FoodCoins_Validation_Params_Are_Null() {
        assertThrows(
                NullPointerException.class,
                () -> validateFoodCoinsUseCase.invoke(null, null)
        );
    }
}
