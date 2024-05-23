package com.robinfood.taxes.dto.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.taxes.dto.v1.AbstractBaseDTO;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxDTO extends AbstractBaseDTO {

    private static final long serialVersionUID = 9017910080448127025L;

    @JsonProperty(value = "value")
    private BigDecimal value;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "apply_rules")
    private Boolean applyRules;

    @JsonProperty(value = "sap_id")
    private String sapId;

    @JsonProperty(value = "family_id")
    private Long familyId;

    @JsonProperty(value = "tax_type_id")
    private Long taxTypeId;

    @JsonProperty(value = "expression_id")
    private Long expressionId;

    @JsonProperty(value = "status")
    private int status;
}
