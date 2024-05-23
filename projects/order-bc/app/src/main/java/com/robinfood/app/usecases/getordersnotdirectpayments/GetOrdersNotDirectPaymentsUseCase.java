package com.robinfood.app.usecases.getordersnotdirectpayments;

import com.robinfood.core.dtos.OrderDTO;
import com.robinfood.core.dtos.OrderPaymentDTO;
import com.robinfood.core.utilities.PaymentMethodsUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of IGetOrdersNotDirectPaymentsUseCase
 */
@Service
public class GetOrdersNotDirectPaymentsUseCase implements IGetOrdersNotDirectPaymentsUseCase {

    private final PaymentMethodsUtil paymentMethodsUtil;

    public GetOrdersNotDirectPaymentsUseCase(PaymentMethodsUtil paymentMethodsUtil) {
        this.paymentMethodsUtil = paymentMethodsUtil;
    }

    @Override
    public List<OrderDTO> invoke(List<OrderDTO> orderDTOS, List<OrderPaymentDTO> orderPaymentDTOS) {

        List<Long> paymentMethodIdIds = paymentMethodsUtil.getPaymentMethodIds();

        final List<Long> ordersIdsDelete = orderPaymentDTOS.stream()
                .filter(paymentMethod -> paymentMethodIdIds.contains(
                        paymentMethod.getPaymentMethodId())
                )
                .map(OrderPaymentDTO::getOrderId)
                .collect(Collectors.toList());

        return orderDTOS.stream()
                .filter(orderDTO -> !ordersIdsDelete.contains(orderDTO.getId()))
                .collect(Collectors.toList());
    }

}
