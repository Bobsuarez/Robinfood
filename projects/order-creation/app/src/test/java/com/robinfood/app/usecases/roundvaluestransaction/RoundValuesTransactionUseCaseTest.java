package com.robinfood.app.usecases.roundvaluestransaction;

import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RoundValuesTransactionUseCaseTest {

    private final TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTOMock().transactionRequestDTO;

    @InjectMocks
    private RoundValuesTransactionUseCase mockRoundValuesTransactionUseCase;

    @Test
    void test_Round_Values_Transaction_Success() {

        mockRoundValuesTransactionUseCase.invoke(transactionRequestDTO);
    }
}
