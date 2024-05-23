package com.robinfood.core.dtos.ordertocreatedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
