package com.robinfood.app.usecases.convertidstransactiontonull;

import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ConvertIdsTransactionToNullUseCaseTest {

    final TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTOMock().transactionRequestDTO;

    @InjectMocks
    ConvertIdsTransactionToNullUseCase mockConvertIdsTransactionToNullUseCase;

    @Test
    public void test_Invoke_Success() {

        mockConvertIdsTransactionToNullUseCase.invoke(transactionRequestDTO);
    }
}
