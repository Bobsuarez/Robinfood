package com.robinfood.dtos.v1.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class EnabledDisabledResolutionDTO implements Serializable {

    @NotNull
    @JsonProperty(value = "status")
    @Valid
    private boolean status;
}
