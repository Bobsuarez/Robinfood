package com.robinfood.core.models.retrofit.menu.hallproductdetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuHallProductDetailResponse implements Serializable {

    private static final long serialVersionUID = -7912191049942740411L;

    @JsonProperty("article_id")
    private Long articleId;

    @JsonProperty("banner_image")
    private String bannerImage;

    @JsonProperty("brand_id")
    private Long brandId;

    @JsonProperty("brand_name")
    private String brandName;

    private String description;

    private Long discount;

    @JsonProperty("display_type")
    private Long displayType;

    private List<MenuHallProductDetailGroupResponse> groups;

    @JsonProperty("id")
    private Long id;

    private String image;

    private String name;

    @JsonProperty("parent_id")
    private Long parentId;

    private Long position;

    private BigDecimal price;

    @JsonProperty("product_category_id")
    private Long productCategoryId;

    @JsonProperty("product_flow_id")
    private Long productFlowId;

    @JsonProperty("product_flow_description")
    private String productFlowDescription;

    @JsonProperty("product_flow_name")
    private String productFlowName;

    @JsonProperty("reference_price")
    private BigDecimal referencePrice;

    @JsonProperty("size_id")
    private Long sizeId;

    @JsonProperty("size_name")
    private String sizeName;

    private String sku;

    @JsonProperty("sticker_id")
    private Long stickerId;

    @JsonProperty("sticker_name")
    private String stickerName;

    private List<String> tags;

    private Long type;

    @JsonProperty("type_name")
    private String typeName;
}
