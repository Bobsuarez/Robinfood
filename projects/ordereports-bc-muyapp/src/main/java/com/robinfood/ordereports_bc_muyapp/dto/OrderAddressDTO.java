package com.robinfood.ordereports_bc_muyapp.dto;

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

    private Integer orderId;

    private Integer transactionId;

    private String zipCode;

}
