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
public class OrderToCreateOriginDTO implements Serializable {

    private static final long serialVersionUID = -2558458111506259942L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

}
