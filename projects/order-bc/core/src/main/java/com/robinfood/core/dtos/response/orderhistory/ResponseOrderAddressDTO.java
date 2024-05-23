package com.robinfood.core.dtos.response.orderhistory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResponseOrderAddressDTO {

    private String address;

    private String latitude;

    private String longitude;

    private String notes;

    private String zipCode;

}
