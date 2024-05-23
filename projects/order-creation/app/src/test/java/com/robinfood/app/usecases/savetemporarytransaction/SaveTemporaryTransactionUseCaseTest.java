package com.robinfood.app.usecases.savetemporarytransaction;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.app.mocks.TransactionCreatedEntityMock;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.entities.template.TransactionCreatedEntity;
import com.robinfood.repositories.dynamodb.transactioncreated.ITransactionCreatedRepository;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class SaveTemporaryTransactionUseCaseTest {

    @Mock
    private ITransactionCreatedRepository transactionCreatedRepository;

    @InjectMocks
    private SaveTemporaryTransactionUseCase saveTemporaryTransactionUseCase;

    @Test
    void test_SaveTransactionCreatedUseCase_When_Save_Success() throws JsonProcessingException {

        when(transactionCreatedRepository
                .save(any(TransactionCreatedEntity.class))).thenReturn(TransactionCreatedEntityMock.build());

        saveTemporaryTransactionUseCase.invoke(
                TransactionRequestDTO.builder()
                .uuid(UUID.randomUUID())
                .build());

        verify(transactionCreatedRepository, times(1)).save(any(TransactionCreatedEntity.class));

    }
}
