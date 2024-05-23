package com.robinfood.taxes.dto.v1.update;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaxRuleDTO implements Serializable {

    private static final long serialVersionUID = 828393192362164200L;

    @Min(value = 1, message = "Tax ID value must be greater than zero.")
    @JsonProperty("tax_id")
    @Schema(example = "1")
    private Long taxId;

    @Min(value = 1, message = "Left Variable ID value must be greater than zero.")
    @JsonProperty("left_variable_id")
    @Schema(example = "1")
    private Long leftVariableId;

    @Min(value = 1, message = "Right Variable ID value must be greater than zero.")
    @JsonProperty("right_variable_id")
    @Schema(example = "2")
    private Long rightVariableId;

    @Min(value = 1, message = "Rule Type ID value must be greater than zero.")
    @JsonProperty("rule_type_id")
    @Schema(example = "1")
    private Long ruleTypeId;

    @Schema(example = "1")
    @Min(value = 1)
    @Max(value = 2, message = "Status value exceeds the max length value must be between 1 and 2")
    @JsonProperty(value = "status")
    private Integer status;

}

