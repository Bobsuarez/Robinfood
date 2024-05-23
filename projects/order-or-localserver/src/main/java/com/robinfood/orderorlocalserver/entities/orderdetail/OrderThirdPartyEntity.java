package com.robinfood.orderorlocalserver.entities.orderdetail;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderThirdPartyEntity {

    private String documentNumber;

    private Long documentType;

    private String email;

    private String fullName;

    private String phone;
}
