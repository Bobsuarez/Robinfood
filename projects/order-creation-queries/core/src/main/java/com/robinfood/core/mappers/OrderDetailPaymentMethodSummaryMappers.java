package com.robinfood.core.mappers;

import com.robinfood.core.dtos.OrderDetailPaymentMethodDTO;
import com.robinfood.core.dtos.PaymentMethodsFilterDTO;
import com.robinfood.core.dtos.orderdetailplushistory.OrderDetailPaymentMethodSummaryDTO;

import java.util.ArrayList;
import java.util.List;

public final class OrderDetailPaymentMethodSummaryMappers {

    private OrderDetailPaymentMethodSummaryMappers (){
        throw new IllegalStateException("Utility class");
    }

    public static List<OrderDetailPaymentMethodSummaryDTO>
    orderDetailPaymentMethodDTOListToOrderDetailPaymentMethodSummaryDTOList(
            Iterable<OrderDetailPaymentMethodDTO> orderDetailPaymentMethodDTOList,
            List<PaymentMethodsFilterDTO> paymentMethodsFilterList
    ){

        List<OrderDetailPaymentMethodSummaryDTO> orderDetailPaymentMethodSummaryDTOList = new ArrayList<>();

        for (OrderDetailPaymentMethodDTO orderDetailPaymentMethodDTO : orderDetailPaymentMethodDTOList) {

            PaymentMethodsFilterDTO paymentMethodsResult = paymentMethodsFilterList.stream().filter(
                    paymentMethodsFilter -> orderDetailPaymentMethodDTO.getId().equals(paymentMethodsFilter.getId())
            ).findFirst().orElseThrow();

            orderDetailPaymentMethodSummaryDTOList.add(
                    OrderDetailPaymentMethodSummaryDTO.builder()
                            .id(orderDetailPaymentMethodDTO.getId())
                            .name(paymentMethodsResult.getName())
                            .value(orderDetailPaymentMethodDTO.getValue())
                    .build()
            );
        }

        return orderDetailPaymentMethodSummaryDTOList;
    }
}
