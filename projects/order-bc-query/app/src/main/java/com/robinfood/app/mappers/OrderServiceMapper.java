package com.robinfood.app.mappers;

import com.robinfood.core.dtos.request.order.ServiceDTO;
import com.robinfood.core.entities.OrderServicesEntity;

public class OrderServiceMapper {

    public static OrderServicesEntity orderServiceDTOToEntity (ServiceDTO serviceDTO, Long orderId) {
        return OrderServicesEntity.builder()
                .orderId(orderId)
                .serviceId(serviceDTO.getId())
                .taxPrice(serviceDTO.getTaxPrice())
                .priceNt(serviceDTO.getPriceNt())
                .discount(serviceDTO.getDiscount())
                .taxPercentage(serviceDTO.getTaxPercentage())
                .total(serviceDTO.getTotal())
                .build();
    }

}
