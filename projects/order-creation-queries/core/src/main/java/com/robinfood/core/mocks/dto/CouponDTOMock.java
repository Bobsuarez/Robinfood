package com.robinfood.core.mocks.dto;

import com.robinfood.core.dtos.CouponDTO;

import java.math.BigDecimal;
import java.util.List;

public class CouponDTOMock {

    public static final List<CouponDTO> getDefaultList() {

        return List.of(CouponDTO.builder()
                        .code("prueba")
                        .value(BigDecimal.ONE)
                .build());
    }
}
