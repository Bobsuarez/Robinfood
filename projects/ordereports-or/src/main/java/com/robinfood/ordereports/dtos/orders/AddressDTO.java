package com.robinfood.ordereports.dtos.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AddressDTO implements Serializable {

    private String address;

    private String latitude;

    private String longitude;

    private String notes;

    private String zipCode;
}
