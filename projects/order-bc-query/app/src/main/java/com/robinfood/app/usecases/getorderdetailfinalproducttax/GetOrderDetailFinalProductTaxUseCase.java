package com.robinfood.app.usecases.getorderdetailfinalproducttax;

import com.robinfood.app.mappers.OrderProductTaxesMappers;
import com.robinfood.core.dtos.OrderProductTaxDTO;
import com.robinfood.core.dtos.GetOrderDetailFinalProductTaxDTO;
import kotlin.collections.CollectionsKt;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Implementation of IGetOrderDetailFinalProductTaxUseCase
 */
@AllArgsConstructor
@Component
@Slf4j
public class GetOrderDetailFinalProductTaxUseCase implements IGetOrderDetailFinalProductTaxUseCase {

    public Map<Long, List<GetOrderDetailFinalProductTaxDTO>> invoke(List<OrderProductTaxDTO> orderProductTaxDTOS) {

        log.info("Starting process to get order detail final product tax: {}", orderProductTaxDTOS);

        final List<GetOrderDetailFinalProductTaxDTO> getOrderDetailFinalProductTaxDTOS = CollectionsKt.map(
                orderProductTaxDTOS,
                OrderProductTaxesMappers::toGetOrderDetailFinalProductTaxDTO
        );

        return CollectionsKt.groupBy(
                getOrderDetailFinalProductTaxDTOS,
                GetOrderDetailFinalProductTaxDTO::getOrderFinalProductId
        );
    }
}
