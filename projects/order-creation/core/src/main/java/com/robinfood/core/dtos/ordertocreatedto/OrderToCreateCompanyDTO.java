package com.robinfood.core.dtos.ordertocreatedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderToCreateCompanyDTO implements Serializable {

    private static final long serialVersionUID = 8274435031461494673L;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("id")
    private Long id;

}
