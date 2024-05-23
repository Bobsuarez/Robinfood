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
public class OrderToCreatePaymentMethodDTO implements Serializable {

    private static final long serialVersionUID = 1214510153436356155L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("originId")
    private Long originId;

    @JsonProperty("value")
    private BigDecimal value;

}
