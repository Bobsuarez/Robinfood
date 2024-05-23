package com.robinfood.taxes.dto.v1.create;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateTaxTypeDTO implements Serializable {

    private static final long serialVersionUID = -9081073526420039041L;

    @NotBlank
    @Size(max = 45)
    @Schema(example = "tax name")
    @JsonProperty(value = "name")
    private String name;

    @NotNull
    @Schema(example = "1")
    @Min(value = 1, message = "value must be greater than zero")
    @JsonProperty(value = "country_id")
    private Long countryId;

}
