package com.robinfood.core.dtos.transactionrequestdto;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortionDTO implements Serializable {

    private static final long serialVersionUID = 8609026979201840943L;

    @NotNull
    @Min(0)
    private BigDecimal discount;

    @PositiveOrZero
    private Integer free;

    @NotNull
    @Positive
    private Long id;

    @NotNull
    private Boolean isIncluded;

    @NotBlank
    private String name;

    @NotNull
    @Min(0)
    private BigDecimal price;

    @NotNull
    @Valid
    private PortionProductDTO product;

    @PositiveOrZero
    private Integer quantity;

    @Valid
    private ReplacementPortionDTO replacementPortion;

    private String sku;

    @NotNull
    @Positive
    private Long unitId;

    @NotNull
    private Long unitNumber;
}
