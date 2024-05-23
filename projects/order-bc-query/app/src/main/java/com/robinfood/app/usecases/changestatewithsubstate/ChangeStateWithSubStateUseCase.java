package com.robinfood.app.usecases.changestatewithsubstate;

import com.robinfood.core.dtos.OrderStateDTO;
import com.robinfood.core.entities.OrderBrandHistoryEntity;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.entities.OrderHistoryEntity;
import com.robinfood.core.exceptions.GenericOrderBcException;
import com.robinfood.repository.orderbrandhistory.IOrderBrandHistoryRepository;
import com.robinfood.repository.orderfinalproducts.IOrderFinalProductRepository;
import com.robinfood.repository.orderhistory.IOrderHistoryRepository;
import com.robinfood.repository.orders.IOrdersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class ChangeStateWithSubStateUseCase implements IChangeStateWithSubStateUseCase {

    private final IOrderBrandHistoryRepository orderBrandHistoryRepository;
    private final IOrderFinalProductRepository orderFinalProductRepository;
    private final IOrderHistoryRepository orderHistoryRepository;
    private final IOrdersRepository ordersRepository;

    public ChangeStateWithSubStateUseCase(
            IOrderBrandHistoryRepository orderBrandHistoryRepository,
            IOrderFinalProductRepository orderFinalProductRepository,
            IOrderHistoryRepository orderHistoryRepository,
            IOrdersRepository ordersRepository
    ) {
        this.orderBrandHistoryRepository = orderBrandHistoryRepository;
        this.orderFinalProductRepository = orderFinalProductRepository;
        this.orderHistoryRepository = orderHistoryRepository;
        this.ordersRepository = ordersRepository;
    }

    @Override
    public Boolean invoke(Long idOrder, OrderStateDTO nextState) {

        final List<OrderHistoryEntity> orderHistoryEntities = new ArrayList<>();
        final List<OrderBrandHistoryEntity> orderBrandHistoryEntities = new ArrayList<>();

        OrderEntity orderEntity = ordersRepository.findById(idOrder)
                .orElseThrow(() -> new GenericOrderBcException("order is not created"));

        if (nextState.getSubState() != null) {
            orderHistoryEntities.add(
                    new OrderHistoryEntity(
                            null,
                            null,
                            null,
                            nextState.getNotes(),
                            orderEntity.getId(),
                            nextState.getSubState().getId(),
                            nextState.getIdUser()
                    ));
        }

        if (!(Objects.equals(orderEntity.getStatusId(), nextState.getId()))) {
            orderHistoryEntities.add(
                    new OrderHistoryEntity(
                            null,
                            null,
                            null,
                            nextState.getNotes(),
                            orderEntity.getId(),
                            nextState.getId(),
                            nextState.getIdUser()
                    ));
        }

        final Iterable<Long> responseOrderFinalProductEntities = orderFinalProductRepository
                .findAllDistinctBrandIdByOrderId(orderEntity.getId());

        for (Long brandId : responseOrderFinalProductEntities) {
            orderBrandHistoryEntities.add(
                    new OrderBrandHistoryEntity(
                            brandId,
                            null,
                            null,
                            orderEntity.getId(),
                            nextState.getId(),
                            null,
                            nextState.getIdUser()
                    )
            );
        }

        orderEntity.setStatusId(nextState.getId());

        ordersRepository.save(orderEntity);
        orderHistoryRepository.saveAll(orderHistoryEntities);
        orderBrandHistoryRepository.saveAll(orderBrandHistoryEntities);

        return Boolean.TRUE;
    }

}
