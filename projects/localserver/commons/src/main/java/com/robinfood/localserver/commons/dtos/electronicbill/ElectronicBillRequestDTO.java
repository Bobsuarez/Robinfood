package com.robinfood.localserver.commons.dtos.electronicbill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class ElectronicBillRequestDTO {

    private String buyerIdentifier;

    private String cnpj;

    private String ie;

    private String keyQuery;

    private String qrCode;

    private String queryKey;

    private String satNumber;

    private String storeEmail;

    private String storePhone;

    private Boolean success;

}
