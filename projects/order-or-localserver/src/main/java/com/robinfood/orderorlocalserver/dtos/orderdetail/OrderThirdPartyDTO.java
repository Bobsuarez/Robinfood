package com.robinfood.orderorlocalserver.dtos.orderdetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderThirdPartyDTO {

    private String documentNumber;

    private Long documentType;

    private String email;

    private String fullName;

    private String phone;
}
