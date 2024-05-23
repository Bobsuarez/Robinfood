package com.robinfood.core.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
