package com.robinfood.app.usecases.getorderdiscountbyfinalproductids;

import com.robinfood.app.mappers.OrderDiscountMappers;
import com.robinfood.core.dtos.OrderDiscountDTO;
import com.robinfood.repository.orderdiscount.IOrderDiscountCRUDRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implementation of IGetOrderDiscountByFinalProductIdsUseCase
 */
@Component
@Slf4j
public class GetOrderDiscountByFinalProductIdsUseCase implements IGetOrderDiscountByFinalProductIdsUseCase {

    private final IOrderDiscountCRUDRepository orderDiscountCRUDRepository;

    public GetOrderDiscountByFinalProductIdsUseCase(IOrderDiscountCRUDRepository orderDiscountCRUDRepository) {
        this.orderDiscountCRUDRepository = orderDiscountCRUDRepository;
    }

    @Override
    public List<OrderDiscountDTO> invoke(List<Long> finaProductIds) {

        log.info("Starting process to get order discount by final product ids: {}", finaProductIds);

        return CollectionsKt.map(
                orderDiscountCRUDRepository.findOrderDiscountEntitiesByOrderFinalProductIdIn(finaProductIds),
                OrderDiscountMappers::toOrderDiscountDTO
        );
    }
}
