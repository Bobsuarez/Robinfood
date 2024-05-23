package com.robinfood.dtos.request.ordertocreatedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderToCreateSizeDTO implements Serializable {

    private static final long serialVersionUID = 1513080141156612141L;

    @JsonProperty("id")
    public Long id;

    @JsonProperty("name")
    public String name;

}
