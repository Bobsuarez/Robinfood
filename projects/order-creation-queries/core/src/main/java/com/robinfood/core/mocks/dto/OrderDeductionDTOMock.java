package com.robinfood.core.mocks.dto;

import com.robinfood.core.dtos.OrderDeductionDTO;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class OrderDeductionDTOMock {

    public static OrderDeductionDTO getDataDefault() {
        return new OrderDeductionDTO(
                BigDecimal.valueOf(10.0)
        );
    }

    public static List<OrderDeductionDTO> list() {
        return Collections.singletonList(
                getDataDefault()
        );
    }
}
