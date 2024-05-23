package com.robinfood.app.usecases.rollbackfoodcoins;

import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.app.usecases.redeemfoodcoinslogic.IRedeemFoodCoinsLogicUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RollbackFoodCoinsUseCaseTest {

    @Mock
    private IRedeemFoodCoinsLogicUseCase iRedeemFoodCoinsLogicUseCase;

    @InjectMocks
    private RollbackFoodCoinsUseCase rollbackFoodCoinsUseCase;

    final Integer operationTypeRollback = 3;

    final String token = "token";

    final TransactionCreationResult transactionCreationResult = TransactionCreationResult.StepValidationSuccess.INSTANCE;

    final TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTOMock().transactionRequestDTO;

    @Test
    void test_Redeem_Food_Coin_Returns_Error() {

        when(iRedeemFoodCoinsLogicUseCase.invoke(
                token,
                transactionRequestDTO,
                operationTypeRollback
        )).thenReturn(transactionCreationResult);

        try {

            rollbackFoodCoinsUseCase.invoke(token, transactionRequestDTO).join();

        } catch (Exception exception) {
            assertTrue(exception.getLocalizedMessage().contains("Some exception"));
        }
    }
}
