package com.robinfood.app.usecases.exitstransactionuuidandorderuids;

import com.robinfood.core.dtos.response.order.ResponseExistsTransactionUuidOrderUidDTO;
import com.robinfood.repository.orders.IOrdersRepository;
import com.robinfood.repository.transaction.ITransactionCRUDRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExitsTransactionUuidAndOrderUuidsUseCase implements IExitsTransactionUuidAndOrderUuidsUseCase {

    private final IOrdersRepository ordersRepository;
    private final ITransactionCRUDRepository transactionCRUDRepository;

    public ExitsTransactionUuidAndOrderUuidsUseCase(IOrdersRepository ordersRepository,
            ITransactionCRUDRepository transactionCRUDRepository) {
        this.ordersRepository = ordersRepository;
        this.transactionCRUDRepository = transactionCRUDRepository;
    }

    @Override
    public ResponseExistsTransactionUuidOrderUidDTO invoke(String transactionUuid, List<String> orderUuids) {

        log.info("Starting process to check if transactionUuid {} or order uuids {} exits", transactionUuid,
                orderUuids);

        boolean transactionUuidExits = transactionUuidExits(transactionUuid);

        if (transactionUuidExits) {
            return ResponseExistsTransactionUuidOrderUidDTO.builder()
                    .exits(true)
                    .message(String.format("Uuid %1$s exits", transactionUuid))
                    .build();
        }

        boolean orderUidExits = orderUidExits(orderUuids);

        if (orderUidExits) {
            return ResponseExistsTransactionUuidOrderUidDTO.builder()
                    .exits(true)
                    .message(String.format("Any of these %1$s uuids exits and it's paid", orderUuids))
                    .build();
        }

        return ResponseExistsTransactionUuidOrderUidDTO.builder()
                .exits(false)
                .message(
                        String.format("None of these TransactionUuid: %1$s and OrderUuids: %2$s exist and aren't paid",
                                transactionUuid, orderUuids))
                .build();
    }

    private boolean orderUidExits(List<String> orderUuids) {

        if (!orderUuids.isEmpty()) {
            log.info("Starting process to check if order uuids {} exits and are paid", orderUuids);

            return ordersRepository.existsByUuidInAndPaid(orderUuids, true);
        }

        return Boolean.FALSE;
    }

    private boolean transactionUuidExits(String transactionUuid) {

        if (!transactionUuid.isEmpty()) {
            log.info("Starting process to check if transactionUuid {} exits}", transactionUuid);
            return transactionCRUDRepository.existsTransactionEntityByUniqueIdentifier(transactionUuid);
        }

        return Boolean.FALSE;
    }
}
