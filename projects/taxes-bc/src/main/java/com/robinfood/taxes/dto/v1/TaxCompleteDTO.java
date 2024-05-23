package com.robinfood.taxes.dto.v1;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.taxes.dto.v1.model.ExpressionDTO;
import com.robinfood.taxes.dto.v1.model.FamilyDTO;
import com.robinfood.taxes.dto.v1.model.TaxTypeDTO;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaxCompleteDTO implements Serializable {

    private static final long serialVersionUID = 11299603870409115L;

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

    @JsonProperty(value = "status")
    private int status;

    @JsonProperty(value = "family")
    private FamilyDTO family;

    @JsonProperty(value = "expressions")
    private ExpressionDTO expression;

    @JsonProperty(value = "tax_types")
    private TaxTypeDTO taxType;

}
