package com.robinfood.dtos.request.ordertocreatedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
