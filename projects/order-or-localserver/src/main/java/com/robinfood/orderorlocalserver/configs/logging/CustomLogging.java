package com.robinfood.orderorlocalserver.configs.logging;

import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailDTO;
import com.robinfood.orderorlocalserver.enums.CompanyEnum;
import com.robinfood.orderorlocalserver.utilities.SlugGeneratorUtil;
import org.slf4j.MDC;

public class CustomLogging {

    public static void invoke(OrderDetailDTO orderDetailDTO){
        MDC.clear();

        MDC.put("brand", orderDetailDTO.getBrandName());
        MDC.put("origin", orderDetailDTO.getOriginName());
        MDC.put("company", null);
        MDC.put("uuid", orderDetailDTO.getOrderUuid());
        MDC.put("store", SlugGeneratorUtil.toSlug(orderDetailDTO.getStoreName()));
    }
}
