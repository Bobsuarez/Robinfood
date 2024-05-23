package com.robinfood.configurations.dto.v1.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PosDTO implements Serializable {

    private static final long serialVersionUID = 321027122227852282L;

    @Schema(example = "pos")
    @JsonProperty(value = "id")
    private Long id;

    @Schema(example = "pos")
    @JsonProperty(value = "name")
    private String name;

    @Schema(example = "AWS123")
    @JsonProperty(value = "code")
    private String code;

    @JsonProperty(value = "resolutions")
    private List<ResolutionDTO> resolutions;
}
