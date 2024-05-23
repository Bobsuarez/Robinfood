package com.robinfood.core.entities;

import lombok.Data;

@Data
public class OrderDetailUserEntity {

    private final String email;

    private final String firstName;

    private final Long id;

    private final String lastName;

    private final String mobile;

}
