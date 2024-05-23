package com.robinfood.app.usecases.getorderdetailbyuuids;

import com.robinfood.app.usecases.getorderdetailorder.IGetOrderDetailUseCase;
import com.robinfood.core.dtos.GetOrderDetailDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.repository.orders.IOrdersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of IGetOrderDetailByUuidsUseCase
 */
@Service
@Slf4j
public class GetOrderDetailByUuidsUseCase implements IGetOrderDetailByUuidsUseCase {

    private final IGetOrderDetailUseCase getOrderDetailUseCase;
    private final IOrdersRepository ordersRepository;

    public GetOrderDetailByUuidsUseCase(
            IGetOrderDetailUseCase getOrderDetailUseCase,
            IOrdersRepository ordersRepository
    ) {
        this.getOrderDetailUseCase = getOrderDetailUseCase;
        this.ordersRepository = ordersRepository;
    }

    @Override
    public List<GetOrderDetailDTO> invoke(List<String> orderUuids) {

        final List<OrderEntity> orderEntities = this.ordersRepository.findAllByUuidIn(orderUuids);

        final List<Long> orderIds = orderEntities.stream().map(
                OrderEntity::getId
        ).collect(Collectors.toList());

        return this.getOrderDetailUseCase.invoke(orderIds);
    }

}
