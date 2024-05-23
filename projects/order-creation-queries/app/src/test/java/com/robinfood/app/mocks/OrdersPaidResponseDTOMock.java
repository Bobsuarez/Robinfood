package com.robinfood.app.mocks;


import com.robinfood.core.dtos.orderspaid.OrderPaidResponseDTO;
import com.robinfood.core.dtos.orderspaid.OrdersPaidResponseDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static com.robinfood.core.constants.GlobalConstants.CODE_ORDER_CANCELED;
import static com.robinfood.core.constants.GlobalConstants.NUMBER_OF_DECIMALS;

public class OrdersPaidResponseDTOMock {

    public static OrdersPaidResponseDTO getDataDefault() {

        return OrdersPaidResponseDTO.builder()
                .items(List.of(OrderPaidResponseDTO.builder()
                                .brandName("TEST")
                                .originName("Pos")
                                .statusCode(CODE_ORDER_CANCELED)
                                .storeName("RobinFood 27")
                                .total(BigDecimal.ZERO.setScale(NUMBER_OF_DECIMALS, RoundingMode.DOWN))
                                .totalCo2(BigDecimal.ZERO.setScale(NUMBER_OF_DECIMALS, RoundingMode.DOWN))
                                .totalPlusCo2(BigDecimal.ZERO.setScale(NUMBER_OF_DECIMALS, RoundingMode.DOWN))
                        .build()))
                .pagination(null)
                .build();
    }
}
