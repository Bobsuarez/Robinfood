package com.robinfood.configurations.dto.v1.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.configurations.dto.v1.ResolutionsIdsDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdatePosDTO implements Serializable {

    private static final long serialVersionUID = 321027122227852282L;

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

    private ResolutionsIdsDTO resolutionsIds;

    @NotNull
    @Schema(example = "false")
    @JsonProperty(value = "status")
    private Boolean status;
}
