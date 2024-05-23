package com.robinfood.app.usecases.createorderhistory;

import com.robinfood.app.mappers.input.OrderHistoryMappers;
import com.robinfood.core.dtos.request.order.OrderHistoryDTO;
import com.robinfood.core.dtos.request.transaction.RequestOrderTransactionDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.entities.OrderHistoryEntity;
import com.robinfood.core.exceptions.GenericOrderBcException;
import com.robinfood.repository.orderhistory.IOrderHistoryRepository;
import com.robinfood.repository.orders.IOrdersRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static com.robinfood.core.constants.GlobalConstants.ORDER_STATUS_CREATED;

/**
 * Implementation of ICreateOrderHistoryUseCase
 */
@Component
@Slf4j
public class CreateOrderHistoryUseCase implements ICreateOrderHistoryUseCase {

    private final IOrderHistoryRepository orderHistoryRepository;
    private final IOrdersRepository ordersRepository;

    public CreateOrderHistoryUseCase(IOrderHistoryRepository orderHistoryRepository,
                                     IOrdersRepository ordersRepository
    ) {
        this.orderHistoryRepository = orderHistoryRepository;

        this.ordersRepository = ordersRepository;
    }

    @Override
    public CompletableFuture<Boolean> invoke(
            String note,
            Long orderId,
            Double orderValueTotal,
            Boolean paid,
            RequestOrderTransactionDTO transaction,
            Double totalPaymentMethods,
            Long userId
    ) {

        log.info(
                "Starting process to save order history with note: {}, order id: {}, " +
                        "order value total: {}, paid: {}, total payments methods: {} and user id: {}",
                note, orderId, orderValueTotal, paid, totalPaymentMethods, userId);

        List<OrderHistoryDTO> orderHistoryDTOList = new ArrayList<>(Collections.singletonList(
                new OrderHistoryDTO(
                        note,
                        orderId,
                        ORDER_STATUS_CREATED,
                        userId
                )
        ));

        List<OrderHistoryEntity> orderHistoryEntities = CollectionsKt.map(
                orderHistoryDTOList,
                OrderHistoryMappers::toOrderHistoryEntity
        );

        orderHistoryRepository.saveAll(orderHistoryEntities);

        if (Objects.nonNull(transaction.getId())) {

            updateCreatedDateOrder(orderId, transaction);
        }

        return CompletableFuture.completedFuture(true);
    }

    private void updateCreatedDateOrder(Long orderId, RequestOrderTransactionDTO transaction) {

        OrderEntity orderTemporal = ordersRepository.
                findAllByTransactionIdOrderByCreatedAtAsc(transaction.getId()).stream().findFirst().
                orElseThrow(() -> {
                    throw new GenericOrderBcException("Transaction id is not found");
                });

        OrderHistoryEntity orderHistoryEntity = orderHistoryRepository.
                findByOrderIdAndOrderStatusId(orderTemporal.getId(), ORDER_STATUS_CREATED);
        OrderHistoryEntity orderHistoryEntityTemporal = orderHistoryRepository.
                findByOrderIdAndOrderStatusId(orderId, ORDER_STATUS_CREATED);

        orderHistoryEntityTemporal.setCreatedAt(orderHistoryEntity.getCreatedAt());
        orderHistoryEntityTemporal.setDate(orderHistoryEntity.getDate());
        orderHistoryRepository.save(orderHistoryEntityTemporal);
    }
}
