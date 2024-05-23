package com.robinfood.core.dtos.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserDTO {

    private String email;

    private String firstName;

    private Long id;

    private Boolean isEmployee;

    private String lastName;

    private String phoneCode;

    private String phoneNumber;
}
