package com.robinfood.ordereports_bc_muyapp.usecases.getorderbyuid;

import com.robinfood.app.library.exception.business.TransactionExecutionException;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseOrderDetailDTO;
import com.robinfood.ordereports_bc_muyapp.models.mapper.OrderDetailOrderMapper;
import com.robinfood.ordereports_bc_muyapp.repository.orders.IOrdersRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@Component
@Slf4j
public class GetOrderByOrderIdUseCase implements IGetOrderByOrderIdUseCase {

    private final OrderDetailOrderMapper orderDetailOrderMapper;

    private final IOrdersRepository ordersRepository;

    @Value("#{'${user.history-orders.diff-status}'.split(',')}")
    private List<Long> notStatusIds;

    @Async
    @Override
    public CompletableFuture<ResponseOrderDetailDTO> invoke(Integer transactionId) {
        return CompletableFuture.supplyAsync(() -> getDataOrderDetailDTO(transactionId));
    }

    private ResponseOrderDetailDTO getDataOrderDetailDTO(Integer transactionId) {
        return Optional
                .ofNullable(ordersRepository.findByTransactionIdAndStatusIdNotIn(transactionId, notStatusIds))
                .map(orderDetailOrderMapper::mapOrderEntityToResponseDTO)
                .orElseThrow(() -> new TransactionExecutionException(
                        String.format(" Transaction not found with transactionUuid %s", transactionId)
                ));
    }
}
