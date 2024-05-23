package com.robinfood.app.usecases.redeemfoodcoinslogic;

import com.robinfood.app.mocks.ConfigTransactionResponseDTOMock;
import com.robinfood.app.mocks.PaymentMethodDTOMock;
import com.robinfood.app.mocks.RedeemFoodCoinsResponseDTOMock;
import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.app.usecases.getpaymentmethod.IGetPaymentMethodFoodCoinsUseCase;
import com.robinfood.core.dtos.transactionrequestdto.PaymentMethodDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationResult;
import com.robinfood.core.exceptions.WriteInTransactionException;
import com.robinfood.repository.loyalty.IRedeemFoodCoinsRepository;
import com.robinfood.repository.transaction.ITransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.concurrent.CompletionException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RedeemFoodCoinsLogicUseCaseTest {

    @Mock
    private IGetPaymentMethodFoodCoinsUseCase getPaymentMethodFoodCoinsUseCase;

    @Mock
    private IRedeemFoodCoinsRepository redeemFoodCoinRepository;

    @Mock
    private ITransactionRepository mockTransactionRepository;

    @InjectMocks
    private RedeemFoodCoinsLogicUseCase redeemFoodCoinsLogicUseCase;

    final String token = "token";
    final Integer operationTypeRedeem = 1;

    private final TransactionRequestDTO transactionRequest = new TransactionRequestDTOMock().paidTransactionRequestDTO;

    @Test
    void test_Redeem_Food_Coins_Logic_Returns_Correctly() {

        when(mockTransactionRepository.getTransactionResponseDTO()).thenReturn(ConfigTransactionResponseDTOMock.build());
        when(redeemFoodCoinRepository.redeemOrRollbackFoodCoins(eq(token), any())).thenReturn(RedeemFoodCoinsResponseDTOMock.build());
        when(getPaymentMethodFoodCoinsUseCase.invoke(anyList())).thenReturn(transactionRequest.getPaymentsPaid().get(0));

        final TransactionCreationResult response = redeemFoodCoinsLogicUseCase.invoke(
                token, transactionRequest, operationTypeRedeem
        );

        assertNotNull(response);
    }

    @Test
    void test_Redeem_Food_Coin_Logic_Returns_Error() {

        when(mockTransactionRepository.getTransactionResponseDTO()).thenReturn(
                ConfigTransactionResponseDTOMock.withOrderIds()
        );

        assertThrows(
                WriteInTransactionException.class,
                () -> redeemFoodCoinsLogicUseCase.invoke(
                        token, transactionRequest, operationTypeRedeem
                )
        );
    }

    @Test
    void test_invoke_Should_ThrowError_When_OneStepFails() {

        final List<PaymentMethodDTO> paymentMethods = PaymentMethodDTOMock.List();

        paymentMethods.add(transactionRequest.getPaymentsPaid().get(0));

        when(mockTransactionRepository.getTransactionResponseDTO()).thenReturn(ConfigTransactionResponseDTOMock.build());
        when(getPaymentMethodFoodCoinsUseCase.invoke(paymentMethods)).thenReturn(paymentMethods.get(2));
        when(redeemFoodCoinRepository.redeemOrRollbackFoodCoins(eq(token), any()))
                .thenThrow(new CompletionException("Error executing", new Exception()));

         assertThrows(
                WriteInTransactionException.class,
                () -> redeemFoodCoinsLogicUseCase.invoke(
                        token, transactionRequest, operationTypeRedeem
                )
        );
    }

    @Test
    void test_Find_Payment_Method_FoodCoins_Is_Null() {

        when(mockTransactionRepository.getTransactionResponseDTO()).thenReturn(ConfigTransactionResponseDTOMock.build());
        lenient().when(redeemFoodCoinRepository.redeemOrRollbackFoodCoins(eq(token), any())).thenReturn(RedeemFoodCoinsResponseDTOMock.build());
        when(getPaymentMethodFoodCoinsUseCase.invoke(anyList())).thenReturn(null);

        final TransactionCreationResult response = redeemFoodCoinsLogicUseCase.invoke(
                token, transactionRequest, operationTypeRedeem
        );

        assertNotNull(response);
    }
}
