package com.robinfood.app.usecases.gettransactioninfo;

import com.robinfood.core.entities.TransactionEntity;
import com.robinfood.core.entities.TransactionFlowEntity;
import com.robinfood.repository.transaction.ITransactionCRUDRepository;
import com.robinfood.repository.transactionflow.ITransactionFlowRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class getTransactionInfoUseCaseTest {

    @Mock
    private ITransactionFlowRepository transactionFlowRepository;

    @Mock
    private ITransactionCRUDRepository transactionCRUDRepository;

    @InjectMocks
    private GetTransactionInfoUseCase getTransactionInfoUseCase;

    @Test
    void When_Get_TransactionInfo_With_TransactionId_Then_Return_Map() {

        TransactionFlowEntity transactionFlowEntity = new TransactionFlowEntity();
        transactionFlowEntity.setTransactionId(1L);
        transactionFlowEntity.setId(1L);
        transactionFlowEntity.setFlowId(1L);
        TransactionEntity transactionEntity = new TransactionEntity();
        Map<String,String> result = new HashMap<>();
        result.put("FLOW_ID", "1");
        result.put("TRANSACTION_UUID", "asdasdas");
        transactionEntity.setUniqueIdentifier("asdasdas");
        when(transactionFlowRepository.findTransactionFlowEntityByTransactionId(1L))
                .thenReturn(transactionFlowEntity);
        when(transactionCRUDRepository.findById(1L)).thenReturn(Optional.of(transactionEntity));

        assertEquals(result, getTransactionInfoUseCase.invoke(1L));

    }

    @Test
    void When_Get_TransactionInfo_With_TransactionId_FLow_Null_Then_Return_Map() {

        TransactionEntity transactionEntity = new TransactionEntity();
        Map<String,String> result = new HashMap<>();
        result.put("FLOW_ID", "0");
        result.put("TRANSACTION_UUID", "asdasdas");
        transactionEntity.setUniqueIdentifier("asdasdas");
        when(transactionFlowRepository.findTransactionFlowEntityByTransactionId(1L))
                .thenReturn(null);
        when(transactionCRUDRepository.findById(1L)).thenReturn(Optional.of(transactionEntity));

        assertEquals(result, getTransactionInfoUseCase.invoke(1L));

    }

}
