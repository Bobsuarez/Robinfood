package com.robinfood.dtos.request.simba.lineas;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.robinfood.dtos.request.simba.IdDTO;
import com.robinfood.util.serializer.BigDecimalSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CargoDescuentoDTO {

    @JsonProperty(value = "Id")
    private IdDTO id;

    @JsonProperty(value = "IndicaCargoODescuento")
    private Boolean indicaCargoODescuento;

    @JsonProperty(value = "RazonCargoDescuentoCod")
    private RazonCargoDescuentoCodDTO razonCargoDescuentoCod;

    @JsonProperty(value = "RazonCargoDescuentoTexto")
    private RazonCargoDescuentoTextoDTO razonCargoDescuentoTexto;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
    @JsonProperty(value = "Porcentaje")
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal porcentaje;

    @JsonProperty(value = "PorcentajeSpecified")
    private Boolean porcentajeSpecified;

    @Builder.Default
    @JsonProperty(value = "IndicaPagoAdelantadoSpecified")
    private Boolean indicaPagoAdelantadoSpecified = Boolean.FALSE;

    @Builder.Default
    @JsonProperty(value = "SecuenciaNumericaSpecified")
    private Boolean secuenciaNumericaSpecified = Boolean.FALSE;

    @JsonProperty(value = "Valor")
    private ValorCargoDescuentoDTO valor;

    @JsonProperty(value = "Base")
    private BaseDTO base;
}
