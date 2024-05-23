package com.robinfood.core.dtos.ordertocreatedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderToCreateCategoryDTO implements Serializable {

    private static final long serialVersionUID = 100304368625212342L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

}
