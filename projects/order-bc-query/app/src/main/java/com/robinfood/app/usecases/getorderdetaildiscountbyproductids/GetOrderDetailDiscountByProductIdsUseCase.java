package com.robinfood.app.usecases.getorderdetaildiscountbyproductids;

import com.robinfood.app.mappers.OrderDiscountMappers;
import com.robinfood.core.dtos.GetOrderDetailDiscountDTO;
import kotlin.collections.CollectionsKt;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Implementation of IGetOrderDetailDiscountByProductIdsUseCase
 */
@AllArgsConstructor
@Component
@Slf4j
public class GetOrderDetailDiscountByProductIdsUseCase implements IGetOrderDetailDiscountByProductIdsUseCase {

    public Map<Long, List<GetOrderDetailDiscountDTO>> invoke(
            List<com.robinfood.core.dtos.OrderDiscountDTO> orderDiscountDTOS
    ) {

        log.info("Starting process to get order detail discount by product id: {}", orderDiscountDTOS);

        final List<GetOrderDetailDiscountDTO> getOrderDetailDiscountDTOS = CollectionsKt.map(
                orderDiscountDTOS,
                OrderDiscountMappers::toGetOrderDetailDiscountDTO
        );

        return CollectionsKt.groupBy(
                getOrderDetailDiscountDTOS,
                GetOrderDetailDiscountDTO::getProductId
        );
    }
}
