package com.robinfood.dtos.response;

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

    @JsonProperty(value = "CUFE")
    private String cufe;

    @JsonProperty(value = "FechaYHoraEmision")
    private String fechaYHoraEmision;

    @JsonProperty(value = "FechaYHoraEmisionSpecified")
    private Boolean fechaYHoraEmisionSpecified;

    @JsonProperty(value = "NitEmisor")
    private String nitEmisor;

    @JsonProperty(value = "Numero")
    private String numero;

    private Object payload;

    @JsonProperty(value = "Prefijo")
    private String prefijo;

    @JsonProperty(value = "QR")
    private String qr;

    private int statusCode;

    @JsonProperty(value = "TipoDocumento")
    private String tipoDocumento;
}
