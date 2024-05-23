package org.example.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class FinalProductDiscountDTO {

    private BigDecimal value;

    private Boolean isProductDiscount = Boolean.TRUE;

    private Boolean isCoupons = Boolean.FALSE;

    private String SKU = "";

}
