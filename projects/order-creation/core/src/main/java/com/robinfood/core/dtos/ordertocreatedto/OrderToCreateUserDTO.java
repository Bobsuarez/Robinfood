package com.robinfood.core.dtos.ordertocreatedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderToCreateUserDTO implements Serializable {

    private static final long serialVersionUID = 3397961135882866493L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("phoneCode")
    private String phoneCode;

    @JsonProperty("mobile")
    private String mobile;

    @JsonProperty("email")
    private String email;

}
