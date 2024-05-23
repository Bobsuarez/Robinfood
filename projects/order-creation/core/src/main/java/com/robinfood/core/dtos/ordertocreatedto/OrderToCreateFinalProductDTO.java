package com.robinfood.core.dtos.ordertocreatedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderToCreateFinalProductDTO implements Serializable {

    private static final long serialVersionUID = 6802939609643912610L;

    @JsonProperty("article")
    private OrderToCreateArticleDTO article;

    @JsonProperty("brand")
    private OrderToCreateBrandDTO brand;

    @JsonProperty("category")
    private OrderToCreateCategoryDTO category;

    @JsonProperty("discounts")
    private List<OrderToCreateFinalProductDiscountDTO> discounts;

    @JsonProperty("groups")
    private List<OrderToCreateGroupDTO> groups;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("image")
    private String image;

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("removedPortions")
    private List<OrderToCreateRemovedPortionDTO> removedPortions;

    @JsonProperty("size")
    private OrderToCreateSizeDTO size;

    @JsonProperty("sku")
    private String sku;

    @JsonProperty("taxes")
    private List<OrderToCreateTaxDTO> taxes;

    @JsonProperty("totalPrice")
    private BigDecimal totalPrice;

}
