package com.robinfood.dtos.request.ordertocreatedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderToCreateCouponDTO implements Serializable {

    private static final long serialVersionUID = 8661375456527638610L;

    @JsonProperty("code")
    private String code;

}
