package com.robinfood.core.dtos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CouponDTO {

    private final String code;
    private final BigDecimal value;

}
