package com.robinfood.app.usecases.gettransactionbyid;

import com.robinfood.app.mappers.TransactionFlowMappers;
import com.robinfood.core.dtos.TransactionFlowDTO;
import com.robinfood.core.exceptions.GenericOrderBcException;
import com.robinfood.repository.transactionflow.ITransactionFlowRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class GetTransactionFlowByIdUseCase implements IGetTransactionFlowByIdUseCase {

    private final TransactionFlowMappers transactionFlowMappers;
    private final ITransactionFlowRepository transactionFlowRepository;

    public GetTransactionFlowByIdUseCase(
            ITransactionFlowRepository transactionFlowRepository,
            TransactionFlowMappers transactionFlowMappers
    ) {
        this.transactionFlowRepository = transactionFlowRepository;
        this.transactionFlowMappers = transactionFlowMappers;
    }

    @Override
    public TransactionFlowDTO invoke(Long id) {

        log.info("Start of the process to search for the transaction flow by id {}", id);


        TransactionFlowDTO transactionFlowDTO = Optional.ofNullable(
            transactionFlowRepository.findTransactionFlowEntityByTransactionId(id)
        )
            .map(transactionFlowMappers::toTransactionFlowDTO)
            .orElseThrow(()->new GenericOrderBcException(
                String.format("Transaction not found with id %s", id)
            ));

        log.info("Transaction flow found {}", transactionFlowDTO);

        return transactionFlowDTO;
    }
}
