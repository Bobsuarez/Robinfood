package com.robinfood.app.usecases.getorderdetailpaymentmethodbyorderids;

import com.robinfood.app.mappers.GetOrderPaymentMappers;
import com.robinfood.core.dtos.OrderPaymentDTO;
import com.robinfood.core.dtos.GetOrderDetailPaymentMethodDTO;
import com.robinfood.core.entities.PaymentMethodEntity;
import com.robinfood.core.exceptions.GenericOrderBcException;
import com.robinfood.repository.paymentmethod.IPaymentMethodRepository;
import kotlin.collections.CollectionsKt;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of IGetDetailOrderPaymentMethodsByOrderIds
 */
@AllArgsConstructor
@Component
@Slf4j
public class GroupOrderDetailPaymentMethodsByOrderIds implements IGroupOrderDetailPaymentMethodsByOrderIds {

    private final IPaymentMethodRepository paymentMethodRepository;

    public Map<Long, List<GetOrderDetailPaymentMethodDTO>> invoke(List<OrderPaymentDTO> orderPaymentDTOS) {

        log.info("Starting process to group order detail payment methods by order ids: {}", orderPaymentDTOS);

        final List<Long> paymentIds = orderPaymentDTOS.stream()
                .map(OrderPaymentDTO::getPaymentMethodId)
                .collect(Collectors.toList());

        List<PaymentMethodEntity> paymentMethodEntities = this.paymentMethodRepository.findAllById(paymentIds);

        if (paymentMethodEntities.isEmpty()) {
            throw new GenericOrderBcException(
                    String.format("Payment methods with ids %s not found", paymentIds.toString())
            );
        }

        final List<GetOrderDetailPaymentMethodDTO> orderPaymentMethodDTOS = orderPaymentDTOS.stream()
                .map(orderPaymentDTO -> GetOrderPaymentMappers.orderPaymentDtoToGetOrderDetailPaymentMethodDTO(
                        orderPaymentDTO, paymentMethodEntities
                )
        ).collect(Collectors.toList());

        return CollectionsKt.groupBy(
                orderPaymentMethodDTOS,
                GetOrderDetailPaymentMethodDTO::getOrderId
        );
    }
}
