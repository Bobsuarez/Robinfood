package com.robinfood.dtos.request.orderbill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ThirdPartyDTO {

    private String documentNumber;

    private int documentType;

    private String email;

    private String fullName;

    private String phone;

}
