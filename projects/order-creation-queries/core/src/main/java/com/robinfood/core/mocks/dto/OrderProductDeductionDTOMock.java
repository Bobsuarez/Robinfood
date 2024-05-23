package com.robinfood.core.mocks.dto;

import com.robinfood.core.dtos.OrderProductDeductionDTO;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class OrderProductDeductionDTOMock {

    public static OrderProductDeductionDTO getDataDefault() {
        return new OrderProductDeductionDTO(
                BigDecimal.valueOf(10.0)
        );
    }

    public static List<OrderProductDeductionDTO> list() {
        return Collections.singletonList(
                getDataDefault()
        );
    }
}
