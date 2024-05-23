package com.robinfood.changestatusbc.usecases.changestatewithsubstate;

import com.robinfood.changestatusbc.dtos.OrderStateDTO;
import com.robinfood.changestatusbc.entities.OrderBrandHistoryEntity;
import com.robinfood.changestatusbc.entities.OrderEntity;
import com.robinfood.changestatusbc.entities.OrderHistoryEntity;
import com.robinfood.changestatusbc.exceptions.GenericChangeStatusBcException;
import com.robinfood.changestatusbc.repositories.orderbrandhistory.IOrderBrandHistoryRepository;
import com.robinfood.changestatusbc.repositories.orderfinalproducts.IOrderFinalProductRepository;
import com.robinfood.changestatusbc.repositories.orderhistory.IOrderHistoryRepository;
import com.robinfood.changestatusbc.repositories.orders.IOrdersRepository;
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
                .orElseThrow(() -> new GenericChangeStatusBcException("Order is not created"));

        if (nextState.getSubState() != null) {
            orderHistoryEntities.add(
                    OrderHistoryEntity.builder()
                            .observation(nextState.getNotes())
                            .orderId(orderEntity.getId())
                            .orderStatusId(nextState.getSubState().getId())
                            .userId(nextState.getIdUser())
                            .build()
            );
        }

        if (!(Objects.equals(orderEntity.getStatusId(), nextState.getId()))) {
            orderHistoryEntities.add(
                    OrderHistoryEntity.builder()
                            .observation(nextState.getNotes())
                            .orderId(orderEntity.getId())
                            .orderStatusId(nextState.getId())
                            .userId(nextState.getIdUser())
                            .build()
            );
        }

        final Iterable<Long> responseOrderFinalProductEntities = orderFinalProductRepository
                .findAllDistinctBrandIdByOrderId(orderEntity.getId());

        for (Long brandId : responseOrderFinalProductEntities) {
            orderBrandHistoryEntities.add(
                    OrderBrandHistoryEntity.builder()
                            .brandId(brandId)
                            .orderId(orderEntity.getId())
                            .orderStatusId(nextState.getId())
                            .userId(nextState.getIdUser())
                            .build()
            );
        }

        orderEntity.setStatusId(nextState.getId());

        ordersRepository.save(orderEntity);
        orderHistoryRepository.saveAll(orderHistoryEntities);
        orderBrandHistoryRepository.saveAll(orderBrandHistoryEntities);

        return Boolean.TRUE;
    }
}
