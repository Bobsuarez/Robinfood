package com.robinfood.localprinterbc.dtos.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

import static com.robinfood.localprinterbc.configs.constants.APIConstants.CONSUMER;
import static com.robinfood.localprinterbc.configs.constants.APIConstants.UNIDENTIFIED_CONSUMER;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
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

    public String getBuyerIdentifier() {
        String consumer = UNIDENTIFIED_CONSUMER;
        if(Objects.isNull(buyerIdentifier)){
            consumer = CONSUMER + buyerIdentifier;
        }
        return consumer;
    }
}
