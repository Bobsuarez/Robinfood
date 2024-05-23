package com.robinfood.app.usecases.gettransactioninfo;

import com.robinfood.core.entities.TransactionEntity;
import com.robinfood.core.entities.TransactionFlowEntity;
import com.robinfood.core.exceptions.GenericOrderBcException;
import com.robinfood.repository.transaction.ITransactionCRUDRepository;
import com.robinfood.repository.transactionflow.ITransactionFlowRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class GetTransactionInfoUseCase implements IGetTransactionInfoUseCase {
    private final ITransactionFlowRepository transactionFlowRepository;
    private final ITransactionCRUDRepository transactionRepository;

    public GetTransactionInfoUseCase(ITransactionFlowRepository transactionFlowRepository,
                                     ITransactionCRUDRepository transactionRepository) {
        this.transactionFlowRepository = transactionFlowRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Map<String, String> invoke(Long transactionId) {

        final TransactionFlowEntity flow = transactionFlowRepository.findTransactionFlowEntityByTransactionId(
                transactionId
        );

        Long flowId = 0L;

        if (Objects.nonNull(flow)) {

            flowId = flow.getFlowId();

        }

        TransactionEntity transactionEntity = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new GenericOrderBcException("Transaction was not found"));

        Map<String,String> result = new HashMap<>();
        result.put("FLOW_ID",flowId.toString() );
        result.put("TRANSACTION_UUID", transactionEntity.getUniqueIdentifier());

        return result;
    }
}
