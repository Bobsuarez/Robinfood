package com.robinfood.core.dtos;

import lombok.Builder;
import lombok.Data;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_LONG_VALUE;

@Data
@Builder
public class OrderDetailUserDTO {

    private final String email;

    private final String firstName;

    private final Long id;

    private final String lastName;

    private Long loyaltyStatus = DEFAULT_LONG_VALUE;

    private final String mobile;
}
