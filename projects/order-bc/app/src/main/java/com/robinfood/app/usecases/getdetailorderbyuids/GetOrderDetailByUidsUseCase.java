package com.robinfood.app.usecases.getdetailorderbyuids;

import com.robinfood.app.usecases.getorderbyuids.IGetOrderByUidsUseCase;
import com.robinfood.app.usecases.getorderdetailorder.IGetOrderDetailOrderUseCase;
import com.robinfood.core.dtos.GetOrderDetailDTO;
import com.robinfood.core.dtos.OrderDTO;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implementation of IGetOrderDetailByUidsUseCase
 */
@Component
@Slf4j
public class GetOrderDetailByUidsUseCase implements IGetOrderDetailByUidsUseCase {

    private final IGetOrderByUidsUseCase getOrderByUidsUseCase;
    private final IGetOrderDetailOrderUseCase getOrderDetailOrderUseCase;

    public GetOrderDetailByUidsUseCase(
            IGetOrderByUidsUseCase getOrderByUidsUseCase,
            IGetOrderDetailOrderUseCase getOrderDetailOrderUseCase
    ) {
        this.getOrderByUidsUseCase = getOrderByUidsUseCase;
        this.getOrderDetailOrderUseCase = getOrderDetailOrderUseCase;
    }

    @Override
    public List<GetOrderDetailDTO> invoke(List<String> uids) {
        log.info("Starting process to get order details by uids: {}", uids);

        final List<OrderDTO> orderDTOS = getOrderByUidsUseCase.invoke(uids);

        final List<Long> orderIds = CollectionsKt.map(orderDTOS, OrderDTO::getId);

        return getOrderDetailOrderUseCase.invoke(orderIds);
    }
}
