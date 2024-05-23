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
public class DiscountDTO implements Serializable {

    private static final long serialVersionUID = -6727754813742690463L;

    @NotNull
    private Long id;

    private Boolean isConsumptionDiscount;

    @Builder.Default
    private Boolean isProductDiscount = Boolean.FALSE;

    private Long orderFinalProductId;

    @Min(0)
    @NotNull
    private BigDecimal value;

    private Long productId;

    private Long typeId;

    @Builder.Default
    private String SKU = "";
}
