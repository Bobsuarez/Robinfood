package com.robinfood.core.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderThirdPartyDTO {

    private String documentNumber;
    private Long documentType;
    private String email;
    private String fullName;
    private String phone;
}
