package com.robinfood.app.usecases.getorderhistoryitems;

import com.robinfood.app.mappers.OrderMappers;
import com.robinfood.core.dtos.OrderHistoryItemDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.utilities.LocalDateTimeUtil;
import com.robinfood.repository.orders.IOrdersRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Implementation of IGetOrderHistoryItemsUseCase
 */
@Component
@Slf4j
public class GetOrderHistoryItemsUseCase implements IGetOrderHistoryItemsUseCase {

    private final IOrdersRepository ordersRepository;

    public GetOrderHistoryItemsUseCase(IOrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public Page<OrderHistoryItemDTO> invoke(
            LocalDate createdAt,
            List<Long> deliveryTypeIds,
            Long originId,
            PageRequest pageRequest,
            Boolean isPaid,
            Long storeId
    ) {
        log.info("Starting process to get order history item");

        final LocalDateTimeUtil localDateTimeUtilWithNow = new LocalDateTimeUtil(LocalDateTime.now());
        LocalDateTime initialDateTime = localDateTimeUtilWithNow.startOfDay();
        LocalDateTime endDateTime = localDateTimeUtilWithNow.lastOfDay();
        Page<OrderEntity> orderToMap = null;

        if (Objects.nonNull(createdAt)) {
            final LocalDateTimeUtil localDateTimeUtilByCreatedAt = new LocalDateTimeUtil(createdAt.atStartOfDay());
            initialDateTime = localDateTimeUtilByCreatedAt.startOfDay();
            endDateTime = localDateTimeUtilByCreatedAt.lastOfDay();
        }

        if (Objects.nonNull(originId) && Objects.isNull(isPaid)) {
            orderToMap = ordersRepository
                    .findAllByOriginIdAndStoreIdAndDeliveryTypeIdInAndCreatedAtBetweenOrderByCreatedAtDesc(
                            originId,
                            storeId,
                            deliveryTypeIds,
                            initialDateTime,
                            endDateTime,
                            pageRequest
                    );
        }

        if (Objects.nonNull(originId) && Objects.nonNull(isPaid)) {
            orderToMap = ordersRepository
                    .findAllByOriginIdAndStoreIdAndDeliveryTypeIdInAndPaidAndCreatedAtBetweenOrderByCreatedAtDesc(
                            originId,
                            storeId,
                            deliveryTypeIds,
                            isPaid,
                            initialDateTime,
                            endDateTime,
                            pageRequest
                    );
        }

        if (Objects.isNull(originId) && Objects.isNull(isPaid)) {
            orderToMap = ordersRepository.findAllByStoreIdAndDeliveryTypeIdInAndCreatedAtBetweenOrderByCreatedAtDesc(
                    storeId,
                    deliveryTypeIds,
                    initialDateTime,
                    endDateTime,
                    pageRequest
            );
        }

        if (Objects.isNull(originId) && Objects.nonNull(isPaid)) {
            orderToMap = ordersRepository
                    .findAllByStoreIdAndDeliveryTypeIdInAndPaidAndCreatedAtBetweenOrderByCreatedAtDesc(
                            storeId,
                            deliveryTypeIds,
                            isPaid,
                            initialDateTime,
                            endDateTime,
                            pageRequest
                    );
        }

        return orderToMap.map(
                OrderMappers::toOrderHistoryItemDTO
        );
    }
}
