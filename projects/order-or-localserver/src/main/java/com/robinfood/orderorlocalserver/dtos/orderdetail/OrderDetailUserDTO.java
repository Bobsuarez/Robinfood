package com.robinfood.orderorlocalserver.dtos.orderdetail;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDetailUserDTO {

    private final String email;

    private final String firstName;

    private final Long id;

    private final String lastName;

    private final String mobile;
}
