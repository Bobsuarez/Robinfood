package com.robinfood.core.dtos.ordertocreatedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderToCreateTaxDTO implements Serializable {

    private static final long serialVersionUID = -8538508397257469215L;

    @JsonProperty("articleId")
    private Long articleId;

    @JsonProperty("articleTypeId")
    private Long articleTypeId;

    @JsonProperty("dicTaxId")
    private Long dicTaxId;

    @JsonProperty("familyTaxTypeId")
    private Long familyTaxTypeId;

    @JsonProperty("taxPrice")
    private BigDecimal taxPrice;

    @JsonProperty("taxValue")
    private BigDecimal taxValue;

}

