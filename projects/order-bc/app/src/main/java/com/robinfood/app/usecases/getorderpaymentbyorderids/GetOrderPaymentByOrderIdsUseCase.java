package com.robinfood.app.usecases.getorderpaymentbyorderids;

import com.robinfood.app.mappers.OrderPaymentMappers;
import com.robinfood.core.dtos.OrderPaymentDTO;
import com.robinfood.repository.orderpayment.IOrderPaymentRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implementation of IGetOrderPaymentByOrderIds
 */
@Component
@Slf4j
public class GetOrderPaymentByOrderIdsUseCase implements IGetOrderPaymentByOrderIdsUseCase {

    private final IOrderPaymentRepository orderPaymentRepository;

    public GetOrderPaymentByOrderIdsUseCase(IOrderPaymentRepository orderPaymentRepository) {
        this.orderPaymentRepository = orderPaymentRepository;
    }

    @Override
    public List<OrderPaymentDTO> invoke(List<Long> orderIds) {

        log.info("Starting process to get order payment by order ids: {}", orderIds);

        return CollectionsKt.map(
            orderPaymentRepository.findOrderPaymentEntitiesByOrderIdIn(orderIds),
            OrderPaymentMappers::toOrderPaymentDTO
        );
    }
}
