package com.robinfood.core.utilities;

import com.robinfood.core.dtos.OrderPaymentDTO;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrdersPaymentUtil {

    public static BigDecimal sumTotalOrdersPayment(Map<Long, List<OrderPaymentDTO>> orderPaymentList, Long idPayment) {
        return Optional.ofNullable(orderPaymentList.get(idPayment))
                .stream().flatMap(Collection::stream)
                .map(convertTotal -> BigDecimal.valueOf(convertTotal.getValue()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static BigDecimal sumTotalOrdersPayment(List<OrderPaymentDTO> orderPaymentDTOList) {
        return orderPaymentDTOList
                .stream()
                .map(convertTotal -> BigDecimal.valueOf(convertTotal.getValue()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static Long sumCounterOrders(Map<Long, List<OrderPaymentDTO>> orderPaymentList, Long idPayment) {
        return Optional.ofNullable(orderPaymentList.get(idPayment))
                .stream().flatMap(Collection::stream)
                .mapToLong(OrderPaymentDTO::getOrderId)
                .count();
    }
}
