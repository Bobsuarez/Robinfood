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
public class OrderToCreateStoreDTO implements Serializable {

    private static final long serialVersionUID = 7390786220964844464L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("posId")
    private Long posId;

    @JsonProperty("sku")
    private String sku;

}
