package com.robinfood.changestatusbc.usecases.changepaidstate;

import com.robinfood.changestatusbc.entities.OrderEntity;
import com.robinfood.changestatusbc.repositories.orders.IOrdersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ChangePaidStateUseCase implements IChangePaidStateUseCase{

    private final IOrdersRepository ordersRepository;

    public ChangePaidStateUseCase(IOrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public Boolean changePaid(Long idOrder) {

        OrderEntity orderEntity = ordersRepository.findById(idOrder)
                .orElseThrow(() -> new IllegalArgumentException("Order is not created"));

        orderEntity.setPaid(Boolean.TRUE);

        ordersRepository.save(orderEntity);

        return Boolean.TRUE;
    }
}
