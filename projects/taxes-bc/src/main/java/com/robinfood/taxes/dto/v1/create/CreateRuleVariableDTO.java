package com.robinfood.taxes.dto.v1.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "CreateRuleVariableDTO")
public class CreateRuleVariableDTO implements Serializable {

    private static final long serialVersionUID = 5062495246543387287L;

    @NotBlank(message = "Name must not be blank.")
    @Size(min = 1, max = 255, message = "Length of name must be between 1 and 255 characters.")
    @JsonProperty
    @Schema(example = "Name")
    private String name;

    @NotBlank(message = "Type must not be blank.")
    @Size(min = 1, max = 45, message = "Length of type must be between 1 and 45 characters.")
    @JsonProperty("type")
    @Schema(example = "Array")
    private String type;

    @JsonProperty("description")
    @Size(min = 1, max = 255, message = "Length of description must be between 1 and 255 characters.")
    @Schema(example = "Rule variable description")
    private String description;

    @JsonProperty("path")
    @Size(min = 1, max = 45, message = "Length of path must be between 1 and 45 characters.")
    @Schema(example = "Path")
    private String path;

    @JsonProperty("value")
    @Size(min = 1, max = 45, message = "Length of value must be between 1 and 45 characters.")
    @Schema(example = "Value")
    private String value;

}
