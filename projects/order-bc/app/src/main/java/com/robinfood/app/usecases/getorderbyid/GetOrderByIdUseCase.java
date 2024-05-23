package com.robinfood.app.usecases.getorderbyid;

import com.robinfood.app.mappers.OrderMappers;
import com.robinfood.core.dtos.OrderDTO;
import com.robinfood.core.exceptions.GenericOrderBcException;
import com.robinfood.repository.orders.IOrdersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GetOrderByIdUseCase implements IGetOrderByIdUseCase {

    private final IOrdersRepository ordersRepository;

    public GetOrderByIdUseCase(IOrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public OrderDTO invoke(Long orderId) {

        log.info("Start of the process that obtains an order with {}", orderId);

        OrderDTO orderDTO = ordersRepository.findById(orderId)
            .map(OrderMappers::toOrderDTO)
            .orElseThrow(()->new GenericOrderBcException("Order not found"));

        log.info("Order found {}", orderDTO);

        return orderDTO;
    }
}
