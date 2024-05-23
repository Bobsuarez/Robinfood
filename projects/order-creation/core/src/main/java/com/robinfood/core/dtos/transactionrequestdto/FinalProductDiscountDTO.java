package com.robinfood.core.dtos.transactionrequestdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinalProductDiscountDTO implements Serializable {

    private static final long serialVersionUID = -4663775174472937039L;

    @NotNull
    @Min(0)
    private BigDecimal value;

    @Builder.Default
    private Boolean isProductDiscount = Boolean.TRUE;

    @Builder.Default
    private Boolean isCoupons = Boolean.FALSE;

    @Builder.Default
    private String SKU = "";

}
