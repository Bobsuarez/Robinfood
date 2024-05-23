package com.robinfood.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CouponEntity {

    private String code;
    private BigDecimal value;

}
