package com.robinfood.ordereports_bc_muyapp.usecases.getcurrentstatusbyorderhistory;

import com.robinfood.app.library.exception.business.TransactionExecutionException;
import com.robinfood.app.library.util.ObjectMapperSingleton;
import com.robinfood.ordereports_bc_muyapp.dto.OrderHistoryDTO;
import com.robinfood.ordereports_bc_muyapp.models.entities.OrderHistoryEntity;
import com.robinfood.ordereports_bc_muyapp.repository.orderhistory.IOrderHistoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@Component
@Slf4j
public class GetCurrentStatusByOrderHistoryUseCase implements IGetCurrentStatusByOrderHistoryUseCase {

    private final IOrderHistoryRepository orderHistoryRepository;

    @Async
    @Override
    public CompletableFuture<OrderHistoryDTO> invoke(List<OrderHistoryDTO> historyDTOS, Short statusId) {

        return CompletableFuture.completedFuture(
                getCurrentStatusByOrderHistory(historyDTOS, statusId));
    }

    @Async
    @Override
    public CompletableFuture<List<OrderHistoryDTO>> getListOrderHistory(Integer orderId) {

        return CompletableFuture.completedFuture(getOrderHistoryEntities(orderId)
                                                         .stream()
                                                         .map(data -> ObjectMapperSingleton.objectToClassConvertValue(
                                                                 data, OrderHistoryDTO.class))
                                                         .toList());
    }

    private List<OrderHistoryEntity> getOrderHistoryEntities(Integer orderId) {
        return Optional.ofNullable(orderHistoryRepository.findAllByOrderId(orderId))
                .orElse(Collections.emptyList())
                .stream()
                .sorted(
                        Comparator.comparing(OrderHistoryEntity::getId)
                                .reversed())
                .toList();
    }

    private OrderHistoryDTO getCurrentStatusByOrderHistory(
            List<OrderHistoryDTO> orderHistory,
            Short statusId
    ) {
        return orderHistory.stream()
                .filter(status -> Objects.equals(status.getOrderStatusId(), statusId))
                .findFirst()
                .orElseThrow(() -> new TransactionExecutionException("Order history is not found"));
    }
}
