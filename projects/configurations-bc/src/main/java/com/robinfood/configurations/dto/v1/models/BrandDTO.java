package com.robinfood.configurations.dto.v1.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.configurations.models.CompanyBrand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandDTO implements Serializable {

    @Min(0)
    @Schema(example = "0")
    @JsonProperty(value = "id")
    private Long id;

    @NotBlank
    @Size(min = 1, max = 50)
    @Schema(example = "brand name")
    @JsonProperty(value = "name")
    private String name;

    @NotBlank
    @Schema(example = "brand enabled")
    @JsonProperty(value = "enabled")
    private Boolean enabled;

}
