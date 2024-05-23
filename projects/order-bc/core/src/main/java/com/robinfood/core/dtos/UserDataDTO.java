package com.robinfood.core.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDataDTO {

    private final String email;

    private final String firstName;

    private final Long id;

    private final String lastName;

    private final String mobile;

    private final Long orderId;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
