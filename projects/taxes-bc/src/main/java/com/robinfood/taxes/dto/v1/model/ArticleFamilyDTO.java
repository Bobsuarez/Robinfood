package com.robinfood.taxes.dto.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.taxes.dto.v1.AbstractBaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ArticleFamilyDTO extends AbstractBaseDTO {

    private static final long serialVersionUID = -8564269537100124572L;

    @JsonProperty(value = "product_type_id")
    private Long productTypeId;

    @JsonProperty(value = "article_id")
    private Long articleId;

    @JsonProperty(value = "family_id")
    private Long familyId;

    @JsonProperty(value = "status")
    private Integer status;
}
