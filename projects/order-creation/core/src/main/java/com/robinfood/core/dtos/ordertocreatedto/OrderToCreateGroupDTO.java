package com.robinfood.core.dtos.ordertocreatedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
