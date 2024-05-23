package com.robinfood.taxes.dto.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.taxes.dto.v1.model.RuleTypeDTO;
import com.robinfood.taxes.dto.v1.model.RuleVariableDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "TaxRuleDTO")
public class TaxRuleCompleteDTO {

    @JsonProperty
    @Schema(example = "1")
    private Long id;

    @JsonProperty("tax_id")
    private TaxSummaryDTO tax;

    @JsonProperty("left_variable_id")
    private RuleVariableDTO leftVariable;

    @JsonProperty("right_variable_id")
    private RuleVariableDTO rightVariable;

    @JsonProperty("rule_type_id")
    private RuleTypeDTO ruleType;

    @JsonProperty(value = "status")
    @Schema(example = "1")
    private Integer status;
}
