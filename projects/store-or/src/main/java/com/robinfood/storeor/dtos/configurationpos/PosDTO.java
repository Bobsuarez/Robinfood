package com.robinfood.storeor.dtos.configurationpos;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class PosDTO {

    @NotBlank
    @Schema(example = "AWS123")
    @JsonProperty(value = "code")
    private String code;

    @NotBlank
    @Schema(example = "pos")
    @JsonProperty(value = "name")
    private String name;

    @NotNull
    @Schema(example = "pos")
    @JsonProperty(value = "posTypeId")
    private Long posTypeId;

    @JsonProperty(value = "resolutionsIds")
    private ResolutionsIdsDTO resolutionsIds;

    @NotNull
    @Schema(example = "false")
    @JsonProperty(value = "status")
    private Boolean status;

}
