package com.robinfood.dtos.request.simba.encabezado;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.robinfood.util.serializer.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EncabezadoDTO {

    @JsonProperty(value = "TipoDocElectronico")
    private String tipoDocElectronico;

    @JsonProperty(value = "IdPersonalizacion")
    private IdPersonalizacionDTO idPersonalizacion;

    @JsonProperty(value = "PrefijoDocumento")
    private String prefijoDocumento;

    @JsonProperty(value = "NumeroDocumento")
    private String numeroDocumento;

    @JsonProperty(value = "IndicaCopiaSpecified")
    private Boolean indicaCopiaSpecified;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Builder.Default
    private ZonedDateTime fechaYHoraDocumento = ZonedDateTime.now(ZoneId.of("America/Bogota"));

    @JsonProperty(value = "FechaDeVencimientoSpecified")
    private Boolean fechaDeVencimientoSpecified;

    @JsonProperty(value = "TipoDeFactura")
    private TipoDeFacturaDTO tipoDeFactura;

    @JsonProperty(value = "CodigoMoneda")
    private CodigoMonedaDTO codigoMoneda;

    @JsonProperty(value = "CantidadLineas")
    private BigDecimal cantidadLineas;

    @JsonProperty(value = "CantidadLineasSpecified")
    private Boolean cantidadLineasSpecified;
}
