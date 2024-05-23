package com.robinfood.app.usecases.createdorderdeduction;

import com.robinfood.app.mappers.OrderDeductionMapper;
import com.robinfood.app.mappers.TrasactionDeductionMapper;
import com.robinfood.core.dtos.request.transaction.RequestOrderTransactionDTO;
import com.robinfood.core.entities.OrderDeductionEntity;
import com.robinfood.core.entities.TrasanctionDeductionEntity;
import com.robinfood.repository.orderdeduction.IOrderDeductionRepository;
import com.robinfood.repository.orderdeductiontrasaction.IDeductionTransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class CreateOrderDeductionsUseCase implements ICreateOrderDeductionsUseCase {

    private final IDeductionTransactionRepository deductionTransactionRepository;

    private final IOrderDeductionRepository orderDeductionRepository;

    public CreateOrderDeductionsUseCase(
            IDeductionTransactionRepository deductionTransactionRepository,
            IOrderDeductionRepository orderDeductionRepository
    ) {
        this.deductionTransactionRepository = deductionTransactionRepository;
        this.orderDeductionRepository = orderDeductionRepository;
    }

    @Override
    public CompletableFuture<Boolean> invoke(
            RequestOrderTransactionDTO requestOrderTransactionDTO,
            Long transactionId,
            List<Long> orderIds) {

        if (Objects.nonNull(requestOrderTransactionDTO.getDeductions())) {

            List<TrasanctionDeductionEntity> listTransactionDeductionsEntities = TrasactionDeductionMapper
                    .listDeductionsDTOToListTransactionsEntity(
                            requestOrderTransactionDTO.getDeductions(),
                            transactionId);

            deductionTransactionRepository.saveAll(listTransactionDeductionsEntities);

            for (int index = 0; index < orderIds.size(); index++) {

                List<OrderDeductionEntity> listOrderDeduction = OrderDeductionMapper
                        .listOrderDeductionDTOToListOrderDeductionEntity(
                                requestOrderTransactionDTO.
                                        getOrders().get(index).getDeductions(), orderIds.get(index));
                orderDeductionRepository.saveAll(listOrderDeduction);

            }
        }

        return CompletableFuture.completedFuture(Boolean.TRUE);
    }

}
