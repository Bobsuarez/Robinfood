package com.robinfood.core.dtos.ordertocreatedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderToCreateDiscountDTO implements Serializable {

    private static final long serialVersionUID = -9014487064359177957L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("value")
    private BigDecimal value;

}

