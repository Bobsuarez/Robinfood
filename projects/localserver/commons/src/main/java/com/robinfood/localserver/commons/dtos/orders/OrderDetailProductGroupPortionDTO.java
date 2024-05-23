package com.robinfood.localserver.commons.dtos.orders;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class OrderDetailProductGroupPortionDTO {

    private  Boolean addition;

    private OrderDetailChangedPortionDTO changedPortion;

    private  BigDecimal discount;

    private Long id;

    private String name;

    private Long parentId;

    private  Double price;

    private  Integer quantity;

    @JsonProperty("free")
    private  Integer quantityFree;

    private String sku;

    private  Long units;

    private  Double weight;

    public boolean hasReplacement() {
        return changedPortion != null;
    }
}
