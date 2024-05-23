package com.robinfood.storeor.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.storeor.dtos.resolutions.ResolutionsIdsDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PosDTO {

    @NotBlank
    @Schema(example = "AWS123")
    private String code;

    @NotBlank
    @Schema(example = "Pos")
    private String name;

    @NotNull
    @Schema(example = "1")
    private Long posTypeId;

    @JsonProperty(value = "resolutionsIds")
    private ResolutionsIdsDTO resolutionsIds;

    @NotNull
    @Schema(example = "true")
    private Boolean status;


}
