package com.robinfood.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderAddressDTO {

    private String address;

    private Integer cityId;

    private Integer countryId;

    private String latitude;

    private String longitude;

    private String notes;

    private Long orderId;

    private Integer transactionId;

    private String zipCode;

}
