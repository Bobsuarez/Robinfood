package com.robinfood.core.entities.transactionrequestentities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class AddressEntity {

    private String address;

    private Integer cityId;

    private Integer countryId;

    private String extra;

    private String latitude;

    private String longitude;

    private String orderId;

    private String zipCode;

}
