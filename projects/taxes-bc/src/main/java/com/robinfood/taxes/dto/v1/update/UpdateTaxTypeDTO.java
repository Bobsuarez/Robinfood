package com.robinfood.taxes.dto.v1.update;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateTaxTypeDTO {

    @Size(min = 1, max = 45, message = "Length of field name must be between 1 and 45 characters.")
    @Schema(example = "tax name")
    @JsonProperty(value = "name")
    private String name;

    @Min(value = 1, message = "Status value does not meet minimum length.")
    @Max(value = 99, message = "Status value exceeds the max length")
    @Schema(example = "1")
    @JsonProperty(value = "status")
    private Integer status;

}
