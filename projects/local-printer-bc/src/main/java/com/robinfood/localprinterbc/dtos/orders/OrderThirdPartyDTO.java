package com.robinfood.localprinterbc.dtos.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class OrderThirdPartyDTO {

    private String email;

    private String fullName;

    private String documentType;

    private String documentNumber;

    @Builder.Default
    private String phone = "--";
}
