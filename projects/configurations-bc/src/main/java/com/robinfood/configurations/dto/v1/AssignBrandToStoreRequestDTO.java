package com.robinfood.configurations.dto.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignBrandToStoreRequestDTO implements Serializable {

    private static final long serialVersionUID = 812492245112627539L;

    @NotNull
    @Schema(example = "1")
    @JsonProperty(value = "brand_id")
    private Long menuBrandId;

    @NotNull
    @Schema(example = "2")
    @JsonProperty(value = "store_id")
    private Long storeId;
}
