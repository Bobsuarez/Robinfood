package com.robinfood.repository.mocks;

import com.robinfood.core.dtos.orderspaid.GetOrdersPaidDTO;
import com.robinfood.core.dtos.orderspaid.OrderPaidDTO;

import java.util.List;

public class GetOrderPaidDTOMock {

    public static GetOrdersPaidDTO getDataDefault() {

        return GetOrdersPaidDTO.builder()
                .items(List.of(OrderPaidDTO.builder().build()))
                .pagination(null)
                .build();
    }
}
