package com.robinfood.core.dtos.transactionrequestdto;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceDTO implements Serializable {

    private static final long serialVersionUID = 9107975168357571112L;

    @Min(0)
    private BigDecimal discount;

    @NotNull
    @Positive
    private Long id;

    private BigDecimal priceNt;

    private BigDecimal taxPercentage;

    private BigDecimal taxPrice;

    private BigDecimal total;

    @NotNull
    @Min(0)
    private BigDecimal value;

}
