package com.robinfood.taxes.dto.v1.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "CreateFormulaVariableDTO")
public class CreateFormulaVariableDTO implements Serializable {

    private static final long serialVersionUID = -613030399203504927L;

    @NotBlank(message = "Name must not be blank.")
    @Size(max = 45)
    @Pattern(regexp = "^[a-zA-Z]*$")
    @JsonProperty(value = "name")
    @Schema(example = "Name")
    private String name;

    @NotBlank(message = "Type must not be blank.")
    @Size(max = 45)
    @JsonProperty
    @Schema(example = "Inner")
    private String type;

    @NotBlank
    @Size(max = 255)
    @JsonProperty
    @Schema(example = "Description")
    private String description;

    @NotBlank(message = "Value must not be blank.")
    @Size(max = 45)
    @JsonProperty
    @Schema(example = "Value")
    private String value;
}
