package com.robinfood.app.util;

import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.ServiceDTO;
import java.math.BigDecimal;

public final class ServicesUtil {

    private ServicesUtil() {
    }

    /**
     * @param order transaction order
     *
     * @return the value of the service minus discounts
     */
    public static BigDecimal getNetValueByOrderDto(OrderDTO order) {

        BigDecimal totalServiceNeto = order.getServices()
                .stream()
                .map(ServiceDTO::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalServiceDiscount = order.getServices()
                .stream()
                .map(ServiceDTO::getDiscount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalServiceNeto.subtract(totalServiceDiscount);
    }
}
