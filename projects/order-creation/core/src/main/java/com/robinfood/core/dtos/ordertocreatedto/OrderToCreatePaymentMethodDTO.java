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
public class OrderToCreatePaymentMethodDTO implements Serializable {

    private static final long serialVersionUID = 1214510153436356155L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("originId")
    private Long originId;

    @JsonProperty("value")
    private BigDecimal value;

}
