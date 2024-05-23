package com.robinfood.core.mappers;

import com.robinfood.core.dtos.configuration.BrandDTO;
import com.robinfood.core.dtos.configuration.BrandsDTO;
import com.robinfood.core.dtos.configuration.ChannelDTO;
import com.robinfood.core.dtos.configuration.ChannelsDTO;
import com.robinfood.core.dtos.configuration.StoreDTO;
import com.robinfood.core.dtos.orderspaid.OrderPaidDTO;
import com.robinfood.core.dtos.orderspaid.OrderPaidResponseDTO;

import java.math.RoundingMode;

import static com.robinfood.core.constants.GlobalConstants.CODE_ORDER_CANCELED;
import static com.robinfood.core.constants.GlobalConstants.CODE_ORDER_PAID;
import static com.robinfood.core.constants.GlobalConstants.ID_ORDER_CANCELED;
import static com.robinfood.core.constants.GlobalConstants.ID_ORDER_DELETED;
import static com.robinfood.core.constants.GlobalConstants.NUMBER_OF_DECIMALS;

public final class OrdersPaidResponseMappers {

    private OrdersPaidResponseMappers () {
        throw new IllegalStateException("Utility class");
    }

    public static OrderPaidResponseDTO orderPaidDTOToOrderPaidResponseDTO (
            BrandsDTO brandsDTO,
            ChannelsDTO channelsDTO,
            StoreDTO storeDTO,
            OrderPaidDTO orderPaidDTO
    ) {

        String statusCode = CODE_ORDER_PAID;

        if(orderPaidDTO.getStatus().getId() == ID_ORDER_CANCELED
                ||  orderPaidDTO.getStatus().getId() == ID_ORDER_DELETED){
            statusCode = CODE_ORDER_CANCELED;
        }

        BrandDTO brandDTOResult = brandsDTO.getContent().stream().filter(
                brandDTO -> orderPaidDTO.getBrandId().equals(brandDTO.getId())
        ).findFirst().orElseThrow();

        ChannelDTO channelDTOResult = channelsDTO.getContent().stream().filter(
                channelDTO -> Integer.parseInt(orderPaidDTO.getOriginId().toString()) == channelDTO.getId()
        ).findFirst().orElseThrow();

        return OrderPaidResponseDTO.builder()
                .brandName(brandDTOResult.getName())
                .discounts(orderPaidDTO.getDiscounts())
                .id(orderPaidDTO.getId())
                .localDate(orderPaidDTO.getLocalDate())
                .localTime(orderPaidDTO.getLocalTime())
                .orderInvoiceNumber(orderPaidDTO.getOrderInvoiceNumber())
                .orderIdIntegration(orderPaidDTO.getOrderIdIntegration())
                .orderUserData(orderPaidDTO.getOrderUserData())
                .originName(channelDTOResult.getName())
                .posResolutionsPrefix(orderPaidDTO.getPosResolutionsPrefix())
                .statusCode(statusCode)
                .storeName(storeDTO.getName())
                .subtotal(orderPaidDTO.getSubtotal())
                .taxes(orderPaidDTO.getTaxes())
                .total(orderPaidDTO.getTotal())
                .totalCo2(orderPaidDTO.getTotalCo2().setScale(NUMBER_OF_DECIMALS, RoundingMode.DOWN))
                .totalPlusCo2(orderPaidDTO.getTotal().add(orderPaidDTO.getTotalCo2())
                        .setScale(NUMBER_OF_DECIMALS, RoundingMode.DOWN))
                .uId(orderPaidDTO.getUId())
                .uuId(orderPaidDTO.getUuId())
                .build();
    }
}
