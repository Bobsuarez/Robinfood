package com.robinfood.core.dtos.menuhallproductdetail;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuHallProductDetailDTO implements Serializable {

    private static final long serialVersionUID = -7912191049942740411L;

    private Long articleId;

    private String bannerImage;

    private Long brandId;

    private String brandName;

    private String description;

    private Long discount;

    private Long displayType;

    private List<MenuHallProductDetailGroupDTO> groups;

    private Long id;

    private String image;

    private String name;

    private Long parentId;

    private Long position;

    private BigDecimal price;

    private Long productCategoryId;

    private Long productFlowId;

    private String productFlowDescription;

    private String productFlowName;

    private BigDecimal referencePrice;

    private Long sizeId;

    private String sizeName;

    private String sku;

    private Long stickerId;

    private String stickerName;

    private List<String> tags;

    private Long type;

    private String typeName;
}
