package com.robinfood.app.usecases.getorderbyuids;

import com.robinfood.app.mappers.OrderMappers;
import com.robinfood.core.dtos.OrderDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.repository.orders.IOrdersRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implementation of IGetOrderByUidsUseCase
 */
@Component
@Slf4j
public class GetOrderByUidsUseCase implements IGetOrderByUidsUseCase {

    private final IOrdersRepository ordersRepository;

    public GetOrderByUidsUseCase(IOrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public List<OrderDTO> invoke(List<String> uids) {
        log.info("Starting process to get order by [{}] uids", uids.size());

        List<OrderEntity> orderEntities = ordersRepository.findAllByUidIn(uids);

        return CollectionsKt.map(
                orderEntities,
                OrderMappers::toOrderDTO
        );
    }
}
