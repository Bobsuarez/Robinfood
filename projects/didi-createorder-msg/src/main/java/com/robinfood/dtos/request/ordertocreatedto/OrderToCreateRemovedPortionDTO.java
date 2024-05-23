package com.robinfood.dtos.request.ordertocreatedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderToCreateRemovedPortionDTO implements Serializable {

    private static final long serialVersionUID = 193480826909685803L;

    @JsonProperty("groupId")
    public Long groupId;

    @JsonProperty("id")
    public Long id;

    @JsonProperty("portionName")
    public String portionName;

}
