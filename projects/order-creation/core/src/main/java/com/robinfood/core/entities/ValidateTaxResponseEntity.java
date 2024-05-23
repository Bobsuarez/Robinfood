package com.robinfood.core.entities;

import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class ValidateTaxResponseEntity {

    private final Long articleId;

    @SerializedName("productTypeId")
    private final Long articleTypeId;

    private final BigDecimal discount;

    private final BigDecimal price;

    private final Integer quantity;

    private final List<ValidateTaxItemResponseEntity> taxes;

    private final BigDecimal totalTax;
}
