package com.robinfood.app.usecases.getdetailorderbyuids;

import com.robinfood.app.usecases.getorderbyuids.IGetOrderByUidsUseCase;
import com.robinfood.app.usecases.getorderdetailorder.IGetOrderDetailUseCase;
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
    private final IGetOrderDetailUseCase getOrderDetailUseCase;

    public GetOrderDetailByUidsUseCase(
            IGetOrderByUidsUseCase getOrderByUidsUseCase,
            IGetOrderDetailUseCase getOrderDetailUseCase
    ) {
        this.getOrderByUidsUseCase = getOrderByUidsUseCase;
        this.getOrderDetailUseCase = getOrderDetailUseCase;
    }

    @Override
    public List<GetOrderDetailDTO> invoke(List<String> uids) {
        log.info("Starting process to get order details by uids: {}", uids);

        final List<OrderDTO> orderDTOS = getOrderByUidsUseCase.invoke(uids);

        final List<Long> orderIds = CollectionsKt.map(orderDTOS, OrderDTO::getId);

        return getOrderDetailUseCase.invoke(orderIds);
    }
}
