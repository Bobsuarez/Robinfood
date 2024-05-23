package com.robinfood.app.usecases.saveinputrequest;

import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SaveInputRequestUseCaseTest {

    private final TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTOMock().transactionRequestDTO;

    @InjectMocks
    private SaveInputRequestUseCase saveInputRequestUseCase;

    @Test
    void test_Save_Input_Request_Success() {

        saveInputRequestUseCase.invoke(transactionRequestDTO);
    }
}
