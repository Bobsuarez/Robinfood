package com.robinfood.configurations.dto.v1.listposresponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class PosResponseDTO implements Serializable {

    @JsonProperty(value = "code")
    private String code;

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "posTypeId")
    private Long posTypeId;

    private ResolutionResponseDTO resolution;

    @JsonProperty(value = "store")
    private StorePosResponseDTO store;

    @JsonProperty(value = "status")
    private Boolean status;
}
