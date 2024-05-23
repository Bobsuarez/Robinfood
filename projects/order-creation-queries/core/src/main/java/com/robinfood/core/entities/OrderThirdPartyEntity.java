package com.robinfood.core.entities;

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
