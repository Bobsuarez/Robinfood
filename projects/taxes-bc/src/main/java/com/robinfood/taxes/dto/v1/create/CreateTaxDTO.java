package com.robinfood.taxes.dto.v1.create;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.math.BigDecimal;
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
public class CreateTaxDTO implements Serializable {

    private static final long serialVersionUID = 7559424910497386379L;

    @NotNull
    @Min(value = 0, message = "Value must be greater than or equal to zero")
    @Schema(example = "10.00")
    @JsonProperty(value = "value")
    private BigDecimal value;

    @NotBlank
    @Size(max = 45)
    @Schema(example = "tax name")
    @JsonProperty(value = "name")
    private String name;

    @NotBlank
    @Size(max = 255)
    @Schema(example = "tax description")
    @JsonProperty(value = "description")
    private String description;

    @Schema(example = "true")
    @JsonProperty(value = "apply_rules")
    private Boolean applyRules;

    @Size(max = 45)
    @Schema(example = "123")
    @JsonProperty(value = "sap_id")
    private String sapId;

    @NotNull
    @Schema(example = "1")
    @Min(value = 1, message = "value must be greater than zero")
    @JsonProperty(value = "family_id")
    private Long familyId;

    @NotNull
    @Schema(example = "1")
    @Min(value = 1, message = "value must be greater than zero")
    @JsonProperty(value = "tax_type_id")
    private Long taxTypeId;

    @NotNull
    @Schema(example = "1")
    @Min(value = 1, message = "value must be greater than zero")
    @JsonProperty(value = "expression_id")
    private Long expressionId;

}
