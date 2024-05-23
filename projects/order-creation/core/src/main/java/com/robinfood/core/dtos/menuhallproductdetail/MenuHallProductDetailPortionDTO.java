package com.robinfood.core.dtos.menuhallproductdetail;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuHallProductDetailPortionDTO implements Serializable {

    private static final long serialVersionUID = 3607431581500956288L;

    private Boolean defaultValue;

    private BigDecimal discount;

    private Long id;

    private String image;

    private String name;

    private Long parentId;

    private BigDecimal premiumPrice;

    private BigDecimal price;

    private Long position;

    private String selectionType;

    private String sku;

    private Long unit;

    private Long weight;
}
