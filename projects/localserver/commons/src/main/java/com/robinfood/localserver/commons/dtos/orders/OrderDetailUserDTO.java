package com.robinfood.localserver.commons.dtos.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.robinfood.localserver.commons.constants.GlobalConstants.DEFAULT_INTEGER_VALUE;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class OrderDetailUserDTO {

    private  String email;

    private  String firstName;

    private  Long id;

    private  String lastName;

    private Long loyaltyStatus = (long) DEFAULT_INTEGER_VALUE;

    private  String mobile;
}
