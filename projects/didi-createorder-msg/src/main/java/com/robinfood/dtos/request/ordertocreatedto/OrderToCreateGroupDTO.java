package com.robinfood.dtos.request.ordertocreatedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderToCreateGroupDTO implements Serializable {

    private static final long serialVersionUID = -5072353488321181392L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("portions")
    private List<OrderToCreatePortionDTO> portions;

    @JsonProperty("sku")
    private String sku;

}
