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
public class OrderToCreateServiceDTO implements Serializable {

    private static final long serialVersionUID = -2995958409181561777L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("value")
    public BigDecimal value;

}
