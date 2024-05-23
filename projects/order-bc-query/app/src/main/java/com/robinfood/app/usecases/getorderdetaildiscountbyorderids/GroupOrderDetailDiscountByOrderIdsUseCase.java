package com.robinfood.app.usecases.getorderdetaildiscountbyorderids;

import com.robinfood.app.mappers.OrderDiscountMappers;
import com.robinfood.core.dtos.OrderDiscountDTO;
import com.robinfood.core.dtos.GetOrderDetailDiscountDTO;
import kotlin.collections.CollectionsKt;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Implementation of IGetDetailOrderDiscountByOrderIdsUseCase
 */
@AllArgsConstructor
@Component
@Slf4j
public class GroupOrderDetailDiscountByOrderIdsUseCase implements IGroupOrderDetailDiscountByOrderIdsUseCase {

    public Map<Long, List<GetOrderDetailDiscountDTO>> invoke(List<OrderDiscountDTO> orderDiscountDTOS) {

        log.info("Starting process to group order detail discount by order ids: {}", orderDiscountDTOS);

        final List<GetOrderDetailDiscountDTO> getOrderDetailDiscountDTOS = CollectionsKt.map(
                orderDiscountDTOS,
                OrderDiscountMappers::toGetOrderDetailDiscountDTO
        );

        return CollectionsKt.groupBy(
                getOrderDetailDiscountDTOS,
                GetOrderDetailDiscountDTO::getOrderId
        );
    }
}
