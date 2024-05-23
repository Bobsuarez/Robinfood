package com.robinfood.taxes.dto.v1.update;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateFamilyDTO implements Serializable {

    private static final long serialVersionUID = 545399227132431955L;

    @NotBlank
    @Size(max = 45)
    @Schema(example = "family name")
    @JsonProperty(value = "name")
    private String name;

    @NotNull
    @Schema(example = "1")
    @Min(value = 1, message = "value must be greater than zero")
    @JsonProperty(value = "family_type_id")
    private Long familyTypeId;

    @Schema(example = "1")
    @Min(value = 1)
    @Max(value = 2, message = "Status value exceeds the max length value must be between 1 and 2")
    @JsonProperty(value = "status")
    private Integer status;

}
