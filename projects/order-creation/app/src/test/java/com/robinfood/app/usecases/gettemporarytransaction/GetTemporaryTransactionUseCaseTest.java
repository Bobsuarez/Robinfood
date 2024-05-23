package com.robinfood.app.usecases.gettemporarytransaction;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.robinfood.app.mocks.TransactionCreatedEntityMock;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.entities.template.TransactionCreatedEntity;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.repositories.dynamodb.transactioncreated.ITransactionCreatedRepository;
import java.util.Optional;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)

class GetTemporaryTransactionUseCaseTest {

    @Mock
    private ITransactionCreatedRepository transactionCreatedRepository;

    @InjectMocks
    private GetTemporaryTransactionUseCase temporaryTransactionUseCase;

    @Test
    @SneakyThrows
    void test_GetTransactionCreatedUseCase_when_GetTransaction_Is_OK() {

        TransactionCreatedEntity entity = TransactionCreatedEntityMock.build();

        when(transactionCreatedRepository.findById(anyString())).thenReturn(Optional.of(entity));

        TransactionRequestDTO transactionRequestDTO =
                temporaryTransactionUseCase.invoke("bdb0ae7f-6b70-451a-a6bf-cfbf4d3e3627");

        Assertions.assertEquals(transactionRequestDTO.getUuid().toString(), entity.getTransactionUuid());
    }

    @Test
    void test_GetTransactionCreatedUseCase_when_GetTransaction_Is_Not_Found() {

        when(transactionCreatedRepository.findById(anyString())).thenReturn(Optional.empty());

        TransactionCreationException transactionCreationException = assertThrows(
                TransactionCreationException.class,
                () -> temporaryTransactionUseCase.invoke("bdb0ae7f-6b70-451a-a6bf-cfbf4d3e3627")
        );

        Assertions.assertEquals(HttpStatus.NOT_FOUND, transactionCreationException.getStatus());
    }
}
