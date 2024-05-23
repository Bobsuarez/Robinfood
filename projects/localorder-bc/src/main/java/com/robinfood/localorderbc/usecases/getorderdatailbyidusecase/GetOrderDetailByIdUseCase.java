package com.robinfood.localorderbc.usecases.getorderdatailbyidusecase;
import com.robinfood.localorderbc.entities.OrderEntity;
import com.robinfood.localorderbc.repositories.IOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class GetOrderDetailByIdUseCase implements IGetOrderDetailByIdUseCase {

    private final IOrderRepository orderRepository;

    public GetOrderDetailByIdUseCase(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String invoke(Long orderId) {

        OrderEntity order = this.orderRepository.getById(orderId);

        return order.getData();
    }

}
