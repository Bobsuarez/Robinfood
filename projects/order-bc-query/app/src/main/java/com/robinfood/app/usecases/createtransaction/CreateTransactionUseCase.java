package com.robinfood.app.usecases.createtransaction;

import com.robinfood.app.mappers.input.TransactionMappers;
import com.robinfood.core.dtos.request.transaction.RequestTransactionDTO;
import com.robinfood.core.dtos.response.transaction.ResponseTransactionDTO;
import com.robinfood.core.entities.TransactionEntity;
import com.robinfood.core.entities.TransactionFlowEntity;
import com.robinfood.repository.transaction.ITransactionCRUDRepository;
import com.robinfood.repository.transaction.ITransactionRepository;
import com.robinfood.repository.transactionflow.ITransactionFlowRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * Implementation of ICreateTransactionUseCase
 */
@Component
@Slf4j
public class CreateTransactionUseCase implements ICreateTransactionUseCase {

    private final ITransactionCRUDRepository transactionCRUDRepository;
    private final ITransactionRepository transactionRepository;
    private final ITransactionFlowRepository transactionFlowRepository;

    public CreateTransactionUseCase(
            ITransactionCRUDRepository transactionCRUDRepository,
            ITransactionRepository transactionRepository,
            ITransactionFlowRepository transactionFlowRepository
    ) {
        this.transactionCRUDRepository = transactionCRUDRepository;
        this.transactionRepository = transactionRepository;
        this.transactionFlowRepository = transactionFlowRepository;
    }

    @Override
    public CompletableFuture<ResponseTransactionDTO> invoke(RequestTransactionDTO transactionDTO) {

        log.info("Create transaction has started with request: {}", transactionDTO);

        if (transactionDTO.getId() != null) {
            transactionCRUDRepository.deleteById(transactionDTO.getId());
        }

        final TransactionEntity transactionEntity = TransactionMappers.toTransactionEntity(
                transactionDTO);

        final TransactionEntity savedTransactionEntity = transactionCRUDRepository.save(
                transactionEntity);

        transactionFlowRepository.save(
                new TransactionFlowEntity(
                        savedTransactionEntity.getId(),
                        transactionDTO.getFlowId()));

        if (savedTransactionEntity.getCreatedAt() != null) {
            transactionRepository.setLocalTransaction(savedTransactionEntity);
        }

        savedTransactionEntity.setCreatedAt(transactionRepository.getLocalTransaction().getCreatedAt());

        log.info("Transaction created: {}", savedTransactionEntity);

        return CompletableFuture.completedFuture(
                TransactionMappers.toTransactionDTO(savedTransactionEntity)
        );
    }
}
