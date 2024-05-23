package com.robinfood.dtos.request.ordertocreatedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderToCreatePortionDTO implements Serializable {

    private static final long serialVersionUID = 3793716270225578966L;

    @JsonProperty("discount")
    private BigDecimal discount;

    @JsonProperty("free")
    private Integer free;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("isIncluded")
    private Boolean isIncluded;

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("product")
    private OrderToCreateProductDTO product;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("sku")
    private String sku;

    @JsonProperty("unitId")
    private Long unitId;

    @JsonProperty("unitNumber")
    private Long unitNumber;

}
