package com.robinfood.app.usecases.getorderdiscountbyorderids;

import com.robinfood.app.mappers.OrderDiscountMappers;
import com.robinfood.core.dtos.OrderDiscountDTO;
import com.robinfood.repository.orderdiscount.IOrderDiscountCRUDRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implementation of IGetOrderDiscountByOrderIdsUseCase
 */
@Component
@Slf4j
public class GetOrderDiscountByOrderIdsUseCase implements IGetOrderDiscountByOrderIdsUseCase {

    private final IOrderDiscountCRUDRepository orderDiscountCRUDRepository;

    public GetOrderDiscountByOrderIdsUseCase(IOrderDiscountCRUDRepository orderDiscountCRUDRepository) {
        this.orderDiscountCRUDRepository = orderDiscountCRUDRepository;
    }

    @Override
    public List<OrderDiscountDTO> invoke(List<Long> orderIds) {

        log.info("Starting process to get order discount by order ids: {}", orderIds);

        return CollectionsKt.map(
            orderDiscountCRUDRepository.findOrderDiscountEntitiesByOrderIdInAndOrderFinalProductIdIsNull(
                orderIds),
            OrderDiscountMappers::toOrderDiscountDTO
        );
    }
}
