package com.robinfood.dtos.request.ordertocreatedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
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
