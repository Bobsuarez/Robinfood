package com.robinfood.app.mappers;

import com.robinfood.core.dtos.OrderStatusDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseBrandDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseFinalProductDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseOrderDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseOrderStatusDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponsePaymentDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseStoreDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.entities.OrderFinalProductEntity;

import static com.robinfood.core.constants.APIConstants.ZONE_ID_UTC;

public class OrderHistoryMapper {

    public static ResponseOrderDTO mapOrderEntityToResponseDTO(OrderEntity order) {
        return ResponseOrderDTO.builder()
            .id(order.getId())
            .uid(order.getUid())
            .paid(order.getPaid())
            .store(ResponseStoreDTO.builder()
                .id(order.getStoreId())
                .name(order.getStoreName())
                .build()
            )
            .payment(
                ResponsePaymentDTO.builder()
                    .subtotal(order.getSubtotal())
                    .tax(order.getTaxes())
                    .discount(order.getDiscounts())
                    .total(order.getTotal())
                    .co2Total(order.getCo2Total())
                    .build()
            )
            .datetime(order.getCreatedAt())
            .timezone(ZONE_ID_UTC)
            .build();
    }

    public static ResponseFinalProductDTO mapFinalProductToResponseDTO(OrderFinalProductEntity finalProduct) {
        return ResponseFinalProductDTO.builder()
            .id(finalProduct.getId())
            .name(finalProduct.getFinalProductName())
            .image(finalProduct.getImage())
            .price(finalProduct.getBasePrice())
            .value(finalProduct.getTotal())
            .quantity(finalProduct.getQuantity())
            .co2Total(finalProduct.getCo2Total())
            .build();
    }

    public static ResponseOrderStatusDTO mapOrderStatusToResponseDTO(
        OrderStatusDTO status
    ) {
        return ResponseOrderStatusDTO.builder()
            .id(status.getId())
            .name(status.getName())
            .build();
    }

    public static ResponseBrandDTO getBrandFinalProduct(OrderFinalProductEntity finalProduct) {
        return ResponseBrandDTO.builder()
            .brandMenuId(finalProduct.getBrandMenuId())
            .id(finalProduct.getBrandId())
            .name(finalProduct.getBrandName())
            .build();
    }

}
