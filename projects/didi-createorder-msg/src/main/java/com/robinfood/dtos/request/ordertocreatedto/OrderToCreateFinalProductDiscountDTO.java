package com.robinfood.dtos.request.ordertocreatedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderToCreateFinalProductDiscountDTO implements Serializable {

    private static final long serialVersionUID = -9014487064359177957L;

    @JsonProperty("value")
    private BigDecimal value;

}

