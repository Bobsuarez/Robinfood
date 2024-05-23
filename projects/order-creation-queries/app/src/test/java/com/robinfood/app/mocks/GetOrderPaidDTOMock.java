package com.robinfood.app.mocks;

import com.robinfood.core.dtos.orderspaid.GetOrdersPaidDTO;
import com.robinfood.core.dtos.orderspaid.OrderPaidDTO;
import com.robinfood.core.dtos.orderspaid.StatusOrderDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static com.robinfood.core.constants.GlobalConstants.NUMBER_OF_DECIMALS;

public class GetOrderPaidDTOMock {

    public static GetOrdersPaidDTO getDataDefault() {

        return GetOrdersPaidDTO.builder()
                .items(List.of(OrderPaidDTO.builder()
                                .brandId(1L)
                                .originId(1L)
                                .status(StatusOrderDTO.builder()
                                        .id(6L)
                                        .build())
                                .storeId(1L)
                                .total(BigDecimal.ZERO.setScale(NUMBER_OF_DECIMALS, RoundingMode.DOWN))
                                .totalCo2(BigDecimal.ZERO.setScale(NUMBER_OF_DECIMALS, RoundingMode.DOWN))
                        .build()))
                .pagination(null)
                .build();
    }
}
