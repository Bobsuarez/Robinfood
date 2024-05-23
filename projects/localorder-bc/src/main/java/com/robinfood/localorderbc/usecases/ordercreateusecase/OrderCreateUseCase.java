package com.robinfood.localorderbc.usecases.ordercreateusecase;

import com.robinfood.localorderbc.dtos.OrderDTO;
import com.robinfood.localorderbc.entities.OrderEntity;
import com.robinfood.localorderbc.mappers.IOrderMapper;
import com.robinfood.localorderbc.repositories.IOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class OrderCreateUseCase implements IOrderCreateUseCase {

    private final IOrderMapper orderMapper;
    private final IOrderRepository orderRepository;

    public OrderCreateUseCase(IOrderMapper orderMapper, IOrderRepository orderRepository) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
    }


    @Override
    public OrderDTO invoke(OrderDTO orderDTO) {
        log.info("Order Create Use Case Execute {}", orderDTO);

        final OrderEntity orderEntityMapper = this.orderMapper.orderDtoToOrderEntity(orderDTO);
        log.info("Order Mapper To Order Entity Result {}", orderEntityMapper);

        final OrderEntity orderEntity = this.orderRepository.save(orderEntityMapper);
        log.info("Order Repository Save Result {}", orderEntityMapper);

        return this.orderMapper.orderEntityToOrderDto(orderEntity);
    }

    @Override
    public OrderDTO update(Long orderId, Long statusId) {
        Optional<OrderEntity> optionalOrderEntity = orderRepository.findById(orderId);
        if (optionalOrderEntity.isPresent()) {
            OrderEntity orderEntity = optionalOrderEntity.get();
            orderEntity.setStatusId(statusId);
            OrderEntity updatedOrderEntity = orderRepository.save(orderEntity);
            return orderMapper.orderEntityToOrderDto(updatedOrderEntity);
        } else {
            return null;
        }
    }
}
