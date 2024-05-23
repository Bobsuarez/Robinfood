package com.robinfood.taxes.dto.v1.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateArticleFamilyDTO implements Serializable {

    private static final long serialVersionUID = 5287928827197593471L;

    @NotNull(message = "product_type_id must be not null.")
    @Min(value = 1, message = "product_type_id value must be greater than zero.")
    @Schema(example = "1")
    @JsonProperty(value = "product_type_id")
    private Long productTypeId;

    @NotNull(message = "article_id must be not null.")
    @Min(value = 1, message = "article_id value must be greater than zero.")
    @Schema(example = "1")
    @JsonProperty(value = "article_id")
    private Long articleId;

    @NotNull(message = "family_id must be not null.")
    @Min(value = 1, message = "family_id value must be greater than zero.")
    @Schema(example = "1")
    @JsonProperty(value = "family_id")
    private Long familyId;

}
