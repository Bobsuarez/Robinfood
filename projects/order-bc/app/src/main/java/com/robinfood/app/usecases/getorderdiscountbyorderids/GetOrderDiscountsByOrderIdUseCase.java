package com.robinfood.app.usecases.getorderdiscountbyorderids;

import com.robinfood.app.mappers.OrderDiscountMappers;
import com.robinfood.core.dtos.OrderDiscountDTO;
import com.robinfood.repository.orderdiscount.IOrderDiscountCRUDRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class GetOrderDiscountsByOrderIdUseCase implements IGetOrderDiscountsByOrderIdUseCase {

    private final IOrderDiscountCRUDRepository orderDiscountCRUDRepository;

    public GetOrderDiscountsByOrderIdUseCase(IOrderDiscountCRUDRepository orderDiscountCRUDRepository) {
        this.orderDiscountCRUDRepository = orderDiscountCRUDRepository;
    }

    @Override
    public List<OrderDiscountDTO> invoke(Long orderId) {

        log.info(
            "Start of the process that obtains the discounts of an order with orderId {}",
            orderId
        );

        List<OrderDiscountDTO> orderDiscountDTOS = orderDiscountCRUDRepository
            .findOrderDiscountEntitiesByOrderId(orderId).stream()
            .map(OrderDiscountMappers::toOrderDiscountDTO)
            .collect(Collectors.toList());

        log.info("Order discounts found {}", orderDiscountDTOS);

        return orderDiscountDTOS;
    }

}
