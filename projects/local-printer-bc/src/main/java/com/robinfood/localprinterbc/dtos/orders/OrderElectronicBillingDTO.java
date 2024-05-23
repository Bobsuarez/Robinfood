package com.robinfood.localprinterbc.dtos.orders;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class OrderElectronicBillingDTO {

    @JsonProperty("NitEmisor")
    private String NitEmisor;

    @JsonProperty("TipoDocumento")
    private String TipoDocumento;

    @JsonProperty("Prefijo")
    private String Prefijo;

    @JsonProperty("Numero")
    private String Numero;

    @JsonProperty("CUDE")
    private String CUDE;

    @JsonProperty("QR")
    private String QR;

    @JsonProperty("FechaYHoraEmision")
    private String FechaYHoraEmision;

    @JsonProperty("FechaYHoraEmisionSpecified")
    private boolean FechaYHoraEmisionSpecified;
}
