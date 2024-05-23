package com.robinfood.ordereports_bc_muyapp.usecases.gettransactionflowbyid;

import com.robinfood.app.library.exception.business.TransactionExecutionException;
import com.robinfood.ordereports_bc_muyapp.models.entities.TransactionFlowEntity;
import com.robinfood.ordereports_bc_muyapp.models.mapper.TransactionFlowMapper;
import com.robinfood.ordereports_bc_muyapp.repository.orders.transactionflow.ITransactionFlowRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static com.robinfood.ordereports_bc_muyapp.enums.OrderDetailLogEnum.FOLLOWING_IS_FOUND_FLOW_ID;

@AllArgsConstructor
@Component
@Slf4j
public class GetTransactionFlowByIdUseCase implements IGetTransactionFlowByIdUseCase {

    private final TransactionFlowMapper transactionFlowMappers;

    private final ITransactionFlowRepository transactionFlowRepository;

    @Async
    @Override
    public CompletableFuture<Short> invoke(Long id) {

        return CompletableFuture.supplyAsync(() -> getDataFlowIdList(id));
    }

    private Short getDataFlowIdList(Long id) {

        return Optional
                .ofNullable(transactionFlowRepository.findTransactionFlowEntityByTransactionId(id))
                .map((TransactionFlowEntity transactionFlowEntity) -> {
                    Short flowIdFound = transactionFlowMappers.toTransactionFlowDTO(transactionFlowEntity).getFlowId();

                    log.info(FOLLOWING_IS_FOUND_FLOW_ID.getMessage(), flowIdFound);

                    return flowIdFound;
                })
                .orElseThrow(() -> new TransactionExecutionException(
                        String.format(" Transaction not found with id %s", id)
                ));
    }
}
