package com.robinfood.taxes.dto.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.taxes.dto.v1.AbstractBaseDTO;
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
public class TaxRuleDTO extends AbstractBaseDTO {

    private static final long serialVersionUID = -1237659535250788723L;

    @JsonProperty("tax_id")
    @Schema(example = "1")
    private Long taxId;

    @JsonProperty("left_variable_id")
    @Schema(example = "1")
    private Long leftVariableId;

    @JsonProperty("right_variable_id")
    @Schema(example = "2")
    private Long rightVariableId;

    @JsonProperty("rule_type_id")
    @Schema(example = "1")
    private Long ruleTypeId;

    @JsonProperty(value = "status")
    @Schema(example = "1")
    private Integer status;

}
