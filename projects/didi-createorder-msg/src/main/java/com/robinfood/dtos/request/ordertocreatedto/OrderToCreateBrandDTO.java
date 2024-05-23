package com.robinfood.dtos.request.ordertocreatedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
