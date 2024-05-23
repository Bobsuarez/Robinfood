package com.robinfood.app.usecases.getorderdetailpaymentmethodbyorderids;

import com.robinfood.app.mappers.OrderPaymentMappers;
import com.robinfood.core.dtos.OrderPaymentDTO;
import com.robinfood.core.dtos.GetOrderDetailPaymentMethodDTO;
import kotlin.collections.CollectionsKt;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Implementation of IGetDetailOrderPaymentMethodsByOrderIds
 */
@AllArgsConstructor
@Component
@Slf4j
public class GroupOrderDetailPaymentMethodsByOrderIds implements IGroupOrderDetailPaymentMethodsByOrderIds {

    public Map<Long, List<GetOrderDetailPaymentMethodDTO>> invoke(List<OrderPaymentDTO> orderPaymentDTOS) {

        log.info("Starting process to group order detail payment methods by order ids: {}", orderPaymentDTOS);

        final List<GetOrderDetailPaymentMethodDTO> orderPaymentMethodDTOS = CollectionsKt.map(
                orderPaymentDTOS,
                OrderPaymentMappers::toGetOrderDetailPaymentMethodDTO
        );

        return CollectionsKt.groupBy(
                orderPaymentMethodDTOS,
                GetOrderDetailPaymentMethodDTO::getOrderId
        );
    }
}
