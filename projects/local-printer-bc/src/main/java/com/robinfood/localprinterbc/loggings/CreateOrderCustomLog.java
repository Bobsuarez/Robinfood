package com.robinfood.localprinterbc.loggings;

import com.robinfood.localprinterbc.dtos.orders.OrderDetailDTO;
import org.slf4j.MDC;

public final class CreateOrderCustomLog {

    private CreateOrderCustomLog() {
        throw new IllegalStateException("Utility class");
    }

    public static void invoke(OrderDetailDTO orderDetailDTO) {

        final String originName = orderDetailDTO.getOriginName();
        final String storeName = orderDetailDTO.getStoreName();
        final String brandName = orderDetailDTO.getBrandName();
        final String uuid = orderDetailDTO.getOrderUuid();

        MDC.clear();

        MDC.put("uuid", uuid);
        MDC.put("origin", originName);
        MDC.put("_store", storeName);
        MDC.put("brand", brandName);
    }
}
