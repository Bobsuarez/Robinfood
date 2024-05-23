package com.robinfood.localprinterbc.dtos.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class OrderDetailProductDiscountDTO {

    private Long id;

    private Long typeId;

    private BigDecimal value;
}
