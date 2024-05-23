package com.robinfood.dtos.request.simba.lineas.totalimpuestos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubTotalImpuestoDTO {

    @JsonProperty(value = "BaseImponible")
    private BaseImponibleDTO baseImponible;

    @JsonProperty(value = "ValorImpuesto")
    private ValorImpuestoDTO valorImpuesto;

    @Builder.Default
    @JsonProperty(value = "SecuenciaNumericaSpecified")
    private Boolean secuenciaNumericaSpecified = Boolean.FALSE;

    @Builder.Default
    @JsonProperty(value = "PorcentajeSpecified")
    private Boolean porcentajeSpecified = Boolean.FALSE;

    @Builder.Default
    @JsonProperty(value = "PorcentajeRangoSpecified")
    private Boolean porcentajeRangoSpecified = Boolean.FALSE;

    @JsonProperty(value = "CategoriaImpuesto")
    private CategoriaImpuestoDTO categoriaImpuesto;
}
