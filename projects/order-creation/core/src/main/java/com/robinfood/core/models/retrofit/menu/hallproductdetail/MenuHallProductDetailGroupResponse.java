package com.robinfood.core.models.retrofit.menu.hallproductdetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuHallProductDetailGroupResponse implements Serializable {

    private static final long serialVersionUID = -2574951755729433412L;

    private Long free;

    private Long id;

    @JsonProperty("group_type_id")
    private Long groupTypeId;

    private Long max;

    private Long min;

    @JsonProperty("name_singular")
    private String nameSingular;

    @JsonProperty("name_plural")
    private String namePlural;

    private Long position;

    private List<MenuHallProductDetailPortionResponse> portions;

    @JsonProperty("selection_name_singular")
    private String selectionNameSingular;

    @JsonProperty("selection_name_plural")
    private String selectionNamePlural;

    private String sku;

    private Long subsidy;

    private Long visible;
}
