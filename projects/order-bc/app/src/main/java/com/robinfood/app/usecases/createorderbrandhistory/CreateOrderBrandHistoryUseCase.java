package com.robinfood.app.usecases.createorderbrandhistory;

import com.robinfood.app.mappers.input.OrderBrandHistoryMappers;
import com.robinfood.app.usecases.getbrandidsbyorder.IGetBrandIdsByOrder;
import com.robinfood.core.constants.GlobalConstants;
import com.robinfood.core.dtos.request.order.FinalProductDTO;
import com.robinfood.core.dtos.request.order.OrderBrandHistoryDTO;
import com.robinfood.core.dtos.request.transaction.RequestOrderTransactionDTO;
import com.robinfood.core.entities.OrderBrandHistoryEntity;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.exceptions.GenericOrderBcException;
import com.robinfood.repository.orderbrandhistory.IOrderBrandHistoryRepository;
import com.robinfood.repository.orders.IOrdersRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.robinfood.core.constants.GlobalConstants.ORDER_STATUS_CREATED;

/**
 * Implementation of ICreateOrderBrandHistoryUseCase
 */
@Component
@Slf4j
public class CreateOrderBrandHistoryUseCase implements ICreateOrderBrandHistoryUseCase {

    private final IGetBrandIdsByOrder getBrandIdsByOrder;
    private final IOrderBrandHistoryRepository orderBrandHistoryRepository;
    private final IOrdersRepository ordersRepository;

    public CreateOrderBrandHistoryUseCase(
            IGetBrandIdsByOrder getBrandIdsByOrder,
            IOrderBrandHistoryRepository orderBrandHistoryRepository,
            IOrdersRepository ordersRepository
    ) {

        this.getBrandIdsByOrder = getBrandIdsByOrder;
        this.orderBrandHistoryRepository = orderBrandHistoryRepository;
        this.ordersRepository = ordersRepository;

    }

    @Override
    public CompletableFuture<Boolean> invoke(
            List<FinalProductDTO> finalProductDTO,
            Long orderId,
            Double orderValueTotal,
            Boolean paid,
            RequestOrderTransactionDTO transactionDTO,
            Double totalPaymentMethods,
            Long userId

    ) {

        log.info(
                "Starting process to save order brand history with final products: [{}], order id: {}, order value total: {}, paid: {}, total payment methods: {} and user id: {}",
                finalProductDTO, orderId, orderValueTotal, paid, totalPaymentMethods, userId);

        List<Long> brandIds = getBrandIdsByOrder.invoke(finalProductDTO);

        List<OrderBrandHistoryDTO> orderBrandHistoryDTOList = new ArrayList<>();

        for (Long brandId : brandIds) {
            orderBrandHistoryDTOList.add(
                    new OrderBrandHistoryDTO(
                            brandId,
                            orderId,
                            ORDER_STATUS_CREATED,
                            userId
                    )
            );
        }

        List<OrderBrandHistoryEntity> orderBrandHistoryEntities = CollectionsKt.map(
                orderBrandHistoryDTOList,
                OrderBrandHistoryMappers::toOrderBrandHistoryEntity
        );

        orderBrandHistoryRepository.saveAll(orderBrandHistoryEntities);

        if (Objects.nonNull(transactionDTO.getId())) {
            updateCreatedDateOrder(orderId, transactionDTO);
        }

        return CompletableFuture.completedFuture(true);
    }

    private void updateCreatedDateOrder(Long orderId, RequestOrderTransactionDTO transactionDTO) {

        OrderEntity orderTemporal = ordersRepository.findAllByTransactionIdOrderByCreatedAtAsc(
                transactionDTO.getId())
                .stream().findFirst().orElseThrow(() -> {
            throw new GenericOrderBcException("Transaction is not found");
        });

        List<OrderBrandHistoryEntity> orderBrandHistoryEntity = orderBrandHistoryRepository
                .findAllByOrderIdAndOrderStatusId(orderTemporal.getId(), ORDER_STATUS_CREATED);
        List<OrderBrandHistoryEntity> orderBrandHistoryEntityTemporal = orderBrandHistoryRepository
                .findAllByOrderIdAndOrderStatusId(orderId, ORDER_STATUS_CREATED);
        List<OrderBrandHistoryEntity> orderBrandEntity = orderBrandHistoryEntityTemporal.stream().map(order -> {
            order.setCreatedAt(orderBrandHistoryEntity.get(GlobalConstants.DEFAULT_INTEGER_VALUE).getCreatedAt());
            order.setStatusChangeDate(orderBrandHistoryEntity.get(GlobalConstants.DEFAULT_INTEGER_VALUE)
                    .getStatusChangeDate());
            return order;
        }).collect(Collectors.toList());
        orderBrandHistoryRepository.saveAll(orderBrandEntity);
    }
}
