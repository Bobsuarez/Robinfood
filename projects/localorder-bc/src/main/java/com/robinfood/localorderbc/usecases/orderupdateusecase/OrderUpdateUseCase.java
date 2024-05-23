package com.robinfood.localorderbc.usecases.orderupdateusecase;

import com.robinfood.localorderbc.entities.OrderEntity;
import com.robinfood.localorderbc.repositories.IOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class OrderUpdateUseCase implements IOrderUpdateUseCase {

    private final IOrderRepository orderRepository;

    public OrderUpdateUseCase(
            IOrderRepository orderRepository
    ) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void invoke(Long orderId, String orderJson) {

        Optional<OrderEntity> optionalOrderEntity = orderRepository.findById(orderId);

        if (optionalOrderEntity.isPresent()) {
            OrderEntity orderEntity = optionalOrderEntity.get();
            orderEntity.setData(orderJson);
            orderRepository.save(orderEntity);
        }
    }
}
