package com.robinfood.core.mappers;

import com.robinfood.core.dtos.OrderDetailServiceDTO;
import com.robinfood.core.dtos.orderdetailplushistory.OrderServicesDetailsSummaryDTO;

import java.util.ArrayList;
import java.util.List;

public final class OrderServicesDetailsSummaryMappers {

    private OrderServicesDetailsSummaryMappers(){
        throw new IllegalStateException("Utility class");
    }

    public static List<OrderServicesDetailsSummaryDTO> orderServicesDetailsDTOListToOrderServicesDetailsSummaryDTO(
            List<OrderDetailServiceDTO> orderServicesDetailsList
    ){
        List<OrderServicesDetailsSummaryDTO> orderServicesDetailsSummaryDTOList = new ArrayList<>();

        orderServicesDetailsList.stream().forEach((OrderDetailServiceDTO orderServicesDetail) ->
            orderServicesDetailsSummaryDTOList.add(OrderServicesDetailsSummaryDTO.builder()
                            .id(orderServicesDetail.getId())
                            .name(orderServicesDetail.getName())
                            .quantity(orderServicesDetail.getQuantity())
                            .unitPrice(orderServicesDetail.getUnitPrice())
                            .total(orderServicesDetail.getUnitPrice() * orderServicesDetail.getQuantity())
                    .build()));

        return orderServicesDetailsSummaryDTOList;
    }
}
