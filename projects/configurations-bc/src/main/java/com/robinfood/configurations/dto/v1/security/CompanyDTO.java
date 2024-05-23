package com.robinfood.configurations.dto.v1.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO implements Serializable {

    private static final long serialVersionUID = -2780880194250415564L;

    @Schema(example = "1")
    @JsonProperty(value = "id")
    private Long id;

    @Schema(example = "America/Bogota")
    @JsonProperty(value = "timezone")
    private String timezone;

    @Schema(example = "Company Name")
    @JsonProperty(value = "name")
    private String name;

}
