package com.robinfood.ordereports.dtos.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDTO implements Serializable {

    private  Double basePrice;

    private Long brandId;

    private BigDecimal co2Total;

    private  BigDecimal discount;

    private List<ProductDiscountDTO> discounts;

    private List<ProductGroupDTO> groups;

    private  Long id;

    private String image;

    private  String name;

    private Double price;

    private Double productPrice;

    private Integer quantity;

    private Long sizeId;

    private String sizeName;

    private List<TaxesDTO> taxes;

    private BigDecimal totalPrice;
}
