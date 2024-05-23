package com.robinfood.core.dtos.ordertocreatedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderToCreateProductDTO implements Serializable {

    private static final long serialVersionUID = 5961434321756327958L;

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

}
