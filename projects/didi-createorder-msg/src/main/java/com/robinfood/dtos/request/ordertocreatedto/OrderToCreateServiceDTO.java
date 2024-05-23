package com.robinfood.dtos.request.ordertocreatedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderToCreateServiceDTO implements Serializable {

    private static final long serialVersionUID = -2995958409181561777L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("value")
    public BigDecimal value;

}
