package com.robinfood.core.models.retrofit.menu.validate;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class DiscountProductsResponse {

    @JsonProperty("discount_products")
    private List<ProductResponse> discountProducts;

}
