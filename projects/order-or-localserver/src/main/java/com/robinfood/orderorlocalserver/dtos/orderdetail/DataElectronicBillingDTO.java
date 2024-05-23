package com.robinfood.orderorlocalserver.dtos.orderdetail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataElectronicBillingDTO {

    @JsonProperty("FechaYHoraEmision")
    private String broadcastDateTime;

    @JsonProperty("CUDE")
    private String cufe;

    @JsonProperty("TipoDocumento")
    private String documentType;

    @JsonProperty("NitEmisor")
    private String nitTransmitter;

    @JsonProperty("Numero")
    private String number;

    @JsonProperty("Prefijo")
    private String prefix;

    @JsonProperty("QR")
    private String qr;
}