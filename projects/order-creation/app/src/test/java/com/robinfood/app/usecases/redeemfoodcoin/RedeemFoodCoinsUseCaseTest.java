package com.robinfood.app.usecases.redeemfoodcoin;

import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.app.usecases.redeemfoodcoins.RedeemFoodCoinsUseCase;
import com.robinfood.app.usecases.redeemfoodcoinslogic.IRedeemFoodCoinsLogicUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RedeemFoodCoinsUseCaseTest {

    @Mock
    private IRedeemFoodCoinsLogicUseCase redeemFoodCoinsLogicUseCase;

    @InjectMocks
    private RedeemFoodCoinsUseCase redeemFoodCoinsUseCase;

    final String token = "token";

    final TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTOMock().transactionRequestDTO;

    final TransactionCreationResult transactionCreationResult = TransactionCreationResult.StepValidationSuccess.INSTANCE;

    @Test
    void test_Redeem_Food_Coin_Returns_Correctly() {

        when(redeemFoodCoinsLogicUseCase.invoke(
                anyString(),
                eq(transactionRequestDTO),
                anyInt()
        )).thenReturn(transactionCreationResult);

        final TransactionCreationResult response = redeemFoodCoinsUseCase.invoke(token, transactionRequestDTO).join();

        assertEquals(transactionCreationResult, response);
    }

    @Test
    void test_Redeem_Food_Coin_Returns_Error() {

        when(redeemFoodCoinsLogicUseCase.invoke(
                anyString(),
                eq(transactionRequestDTO),
                anyInt()
        )).thenReturn(transactionCreationResult);

        try {
            redeemFoodCoinsUseCase.invoke(token, transactionRequestDTO);
        } catch (Exception exception) {
            assertTrue(exception.getLocalizedMessage().contains("Some exception"));
        }
    }

    @Test
    void test_Not_Redeem_Food_Coin_When_Paid_False() {

        lenient().when(redeemFoodCoinsLogicUseCase.invoke(
                anyString(),
                eq(transactionRequestDTO),
                anyInt()
        )).thenReturn(transactionCreationResult);

        transactionRequestDTO.setPaid(false);

        final TransactionCreationResult response = redeemFoodCoinsUseCase.invoke(token, transactionRequestDTO).join();

        assertNull(response);
    }
}
