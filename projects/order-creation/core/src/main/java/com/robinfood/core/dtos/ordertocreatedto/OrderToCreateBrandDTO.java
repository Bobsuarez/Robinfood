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
public class OrderToCreateBrandDTO implements Serializable {

    private static final long serialVersionUID = -8630788696519946027L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

}
