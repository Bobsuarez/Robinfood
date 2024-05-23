package com.robinfood.storeor.dtos.configurationposbystore;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivateOrDeactivateDTO {

    @NotNull
    @Schema(example = "false")
    @JsonProperty(value = "status")
    private Boolean status;
}
