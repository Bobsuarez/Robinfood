package com.robinfood.app.usecases.saverequestordercreated;

import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class SaveRequestOrderCreatedUseCaseTest {

    final TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTOMock().transactionRequestDTO;

    @InjectMocks
    private SaveRequestOrderCreatedUseCase saveRequestOrderCreatedUseCase;

    @Test
    void test_Save_Request_Success() {
        saveRequestOrderCreatedUseCase.invoke(transactionRequestDTO);
    }

    @Test
    void test_Request_Is_Null() {
        assertThrows(
                NullPointerException.class,
                () -> saveRequestOrderCreatedUseCase.invoke(null)
        );
    }
}
