package com.robinfood.core.dtos.transactionrequestdto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class AddressDTO implements Serializable {

    private String address;

    private Integer cityId;

    private Integer countryId;

    private String extra;

    private String latitude;

    private String longitude;

    private String orderId;

    private String zipCode;

}
