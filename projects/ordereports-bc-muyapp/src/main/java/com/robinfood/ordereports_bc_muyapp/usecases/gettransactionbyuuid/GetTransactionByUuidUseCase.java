package com.robinfood.ordereports_bc_muyapp.usecases.gettransactionbyuuid;

import com.robinfood.app.library.exception.business.TransactionExecutionException;
import com.robinfood.ordereports_bc_muyapp.models.entities.TransactionEntity;
import com.robinfood.ordereports_bc_muyapp.repository.transaction.ITransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@Component
@Slf4j
public class GetTransactionByUuidUseCase implements IGetTransactionByUuidUseCase {

    private final ITransactionRepository transactionRepository;

    @Async
    @Override
    public CompletableFuture<Integer> invoke(String transactionUuid) {

        return CompletableFuture.supplyAsync(() -> getDataIdDTOList(transactionUuid));
    }

    private Integer getDataIdDTOList(String transactionUuid) {

        return Optional.ofNullable(transactionRepository.findByUniqueIdentifier(transactionUuid))
                .map(TransactionEntity::getId)
                .orElseThrow(() -> new TransactionExecutionException(
                        String.format("Transaction not found with uuid %s", transactionUuid)
                ));
    }
}
