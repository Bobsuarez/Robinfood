package com.robinfood.taxes.dto.v1.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "CreateExpressionDTO")
public class CreateExpressionDTO implements Serializable {

    private static final long serialVersionUID = -3601418493740702878L;

    @NotEmpty(message = "Value must not be empty.")
    @Size(min = 1, max = 255, message = "Length of value must be between 1 and 255 characters.")
    @JsonProperty
    @Schema(example = "(price - discount)")
    private String value;

    @NotEmpty(message = "Description must not be empty.")
    @Size(min = 1, max = 255,
        message = "Length of description must less or equal than 255 characters.")
    @JsonProperty
    @Schema(example = "Price minus discount.")
    private String description;

}
