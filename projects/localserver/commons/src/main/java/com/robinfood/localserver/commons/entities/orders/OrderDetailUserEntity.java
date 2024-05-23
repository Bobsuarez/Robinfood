package com.robinfood.localserver.commons.entities.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class OrderDetailUserEntity {

    private String email;

    private String firstName;

    private Long id;

    private String lastName;

    private Long loyaltyStatus;

    private String mobile;

}
