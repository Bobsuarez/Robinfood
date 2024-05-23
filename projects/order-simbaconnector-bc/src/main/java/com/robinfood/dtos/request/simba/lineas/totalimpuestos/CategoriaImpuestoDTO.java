package com.robinfood.dtos.request.simba.lineas.totalimpuestos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.robinfood.dtos.request.simba.terceros.esquematributarioterceri.EsquemaTributarioDTO;
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
public class CategoriaImpuestoDTO {

    @JsonProperty(value = "Porcentaje")
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal porcentaje;

    @JsonProperty(value = "PorcentajeSpecified")
    private Boolean porcentajeSpecified;

    @Builder.Default
    @JsonProperty(value = "PorcentajeRangoSpecified")
    private Boolean porcentajeRangoSpecified = Boolean.FALSE;

    @JsonProperty(value = "EsquemaTributario")
    private EsquemaTributarioDTO esquemaTributario;
}
