package com.robinfood.core.entities;

import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class ValidateTaxItemRequestEntity {

    private final Long articleId;

    @SerializedName("productTypeId")
    private final Long articleTypeId;

    private final BigDecimal discount;

    private final BigDecimal price;

    private final Integer quantity;
}
