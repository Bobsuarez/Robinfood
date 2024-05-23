package com.robinfood.core.dtos.ordertocreatedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderToCreateCouponDTO implements Serializable {

    private static final long serialVersionUID = 8661375456527638610L;

    @JsonProperty("code")
    private String code;

}
