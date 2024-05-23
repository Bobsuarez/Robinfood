package com.robinfood.dtos.v1.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class StoreResolutionDTO implements Serializable {

    @NotNull
    @JsonProperty(value = "resolutions")
    @Valid
    private List<ResolutionDTO> resolutions;

    @JsonProperty(value = "storeId")
    private Long storeId;
}
