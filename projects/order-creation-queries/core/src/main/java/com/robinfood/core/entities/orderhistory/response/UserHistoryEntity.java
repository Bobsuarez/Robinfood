package com.robinfood.core.entities.orderhistory.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class UserHistoryEntity {

    private final String email;

    private final String firstName;

    private final String fullName;

    private final Long id;

    private final String lastName;

    private final String mobile;

    private final Long orderId;

}
