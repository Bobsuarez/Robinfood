package com.robinfood.core.mappers.transactionv2;

import com.robinfood.core.dtos.ordertocreatedto.OrderToCreateDTO;
import com.robinfood.core.dtos.ordertocreatedto.OrderToCreateOrderDTO;

public final class OrderToCreateV2Mapper {

    private OrderToCreateV2Mapper() {
    }

    public static void orderToCreateV1ToOrderToCreateV2(OrderToCreateDTO orderToCreateDTO) {
        for (OrderToCreateOrderDTO order : orderToCreateDTO.getOrders()) {
            orderV1ToOrderV2(order);
        }
    }

    public static void orderV1ToOrderV2(OrderToCreateOrderDTO order) {
        order.setCriteriaInfo(order.getCouponCriteriaInfo());
    }

}
