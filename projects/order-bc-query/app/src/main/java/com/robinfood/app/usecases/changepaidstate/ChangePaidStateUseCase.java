package com.robinfood.app.usecases.changepaidstate;

import com.robinfood.core.entities.OrderEntity;
import com.robinfood.repository.orders.IOrdersRepository;
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
                .orElseThrow(() -> new IllegalArgumentException("order is not created"));

        orderEntity.setPaid(Boolean.TRUE);

        ordersRepository.save(orderEntity);

        return Boolean.TRUE;
    }
}
