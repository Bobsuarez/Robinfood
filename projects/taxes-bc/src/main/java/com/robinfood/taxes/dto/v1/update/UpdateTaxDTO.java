package com.robinfood.taxes.dto.v1.update;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.math.BigDecimal;
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
public class UpdateTaxDTO implements Serializable {

    private static final long serialVersionUID = -3149298492963467021L;

    @Min(value = 0, message = "Value must be greater than or equal to zero")
    @Schema(example = "10.00")
    @JsonProperty(value = "value")
    private BigDecimal value;

    @Size(min = 1, max = 45)
    @Schema(example = "tax name")
    @JsonProperty(value = "name")
    private String name;

    @Size(min = 1, max = 255)
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

    @Schema(example = "1")
    @Min(value = 1, message = "value must be greater than zero")
    @JsonProperty(value = "family_id")
    private Long familyId;

    @Schema(example = "1")
    @Min(value = 1, message = "value must be greater than zero")
    @JsonProperty(value = "tax_type_id")
    private Long taxTypeId;

    @Schema(example = "1")
    @Min(value = 1, message = "value must be greater than zero")
    @JsonProperty(value = "expression_id")
    private Long expressionId;

    @Schema(example = "1")
    @Min(value = 1)
    @Max(value = 2, message = "Status value exceeds the max length value must be between 1 and 2")
    @JsonProperty(value = "status")
    private Integer status;

}
