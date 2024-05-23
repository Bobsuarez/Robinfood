package com.robinfood.core.models.retrofit.menu.hallproductdetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuHallProductDetailPortionResponse implements Serializable {

    private static final long serialVersionUID = 3607431581500956288L;

    @JsonProperty("default")
    private Boolean defaultValue;

    private BigDecimal discount;

    private Long id;

    private String image;

    private String name;

    @JsonProperty("parent_id")
    private Long parentId;

    @JsonProperty("premium_price")
    private BigDecimal premiumPrice;

    private BigDecimal price;

    private Long position;

    @JsonProperty("selection_type")
    private String selectionType;

    private String sku;

    private Long unit;

    private Long weight;
}
