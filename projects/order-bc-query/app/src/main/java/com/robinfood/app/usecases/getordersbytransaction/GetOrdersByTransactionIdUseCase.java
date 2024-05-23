package com.robinfood.app.usecases.getordersbytransaction;

import com.robinfood.app.mappers.OrderMappers;
import com.robinfood.core.dtos.OrderDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.repository.orders.IOrdersRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class GetOrdersByTransactionIdUseCase implements IGetOrdersByTransactionIdUseCase {

    private final IOrdersRepository ordersRepository;

    public GetOrdersByTransactionIdUseCase(IOrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public List<OrderDTO> invoke(Long transactionId) {
        log.info("Starting process to get order by [{}] transactionId", transactionId);

        List<OrderEntity> orderEntities = ordersRepository.findOrderEntitiesByTransactionId(transactionId);

        return CollectionsKt.map(
            orderEntities,
            OrderMappers::toOrderDTO
        );
    }
}
