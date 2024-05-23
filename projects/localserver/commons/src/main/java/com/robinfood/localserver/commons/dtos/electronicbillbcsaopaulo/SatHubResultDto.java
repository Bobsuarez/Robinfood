package com.robinfood.localserver.commons.dtos.electronicbillbcsaopaulo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SatHubResultDto {

    private String sessionNumber;

    private String eeeee;

    private String cccc;

    private String message;

    private String sefazCode;

    private String sefazMessage;

    private String satCFE;

    private String timestamp;

    private String queryKey;

    private String totalValueCFE;

    private String cpfCnpjNumbers;

    private String qrCode;

    private String IE;

    private String CNPJ;

    private Boolean success;

    private String keyQuery;

    private String satNumber;
}
