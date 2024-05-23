package com.robinfood.taxes.dto.v1.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
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
@Schema(name = "CreateTaxRuleDTO")
public class CreateTaxRuleDTO implements Serializable {

    private static final long serialVersionUID = 2654028688700060555L;

    @NotNull(message = "Tax ID must be not null.")
    @Min(value = 1, message = "Tax ID value must be greater than zero.")
    @JsonProperty("tax_id")
    @Schema(example = "1")
    private Long taxId;

    @NotNull(message = "Left Variable ID must be not null.")
    @Min(value = 1, message = "Left Variable ID value must be greater than zero.")
    @JsonProperty("left_variable_id")
    @Schema(example = "1")
    private Long leftVariableId;

    @NotNull(message = "Right Variable ID must be not null.")
    @Min(value = 1, message = "Right Variable ID value must be greater than zero.")
    @JsonProperty("right_variable_id")
    @Schema(example = "2")
    private Long rightVariableId;

    @NotNull(message = "Rule Type ID must be not null.")
    @Min(value = 1, message = "Rule Type ID value must be greater than zero.")
    @JsonProperty("rule_type_id")
    @Schema(example = "1")
    private Long ruleTypeId;

}
