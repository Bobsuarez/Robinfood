package com.robinfood.dtos.request.simba.lineas.totalimpuestos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TotalImpuestoDTO {

    @JsonProperty(value = "ValorImpuesto")
    private ValorImpuestoDTO valorImpuesto;

    @JsonProperty(value = "ValorAjusteRedondeo")
    private ValorAjusteRedondeoDTO valorAjusteRedondeo;

    @Builder.Default
    @JsonProperty(value = "IndicaEsSoloEvidencia")
    private Boolean indicaEsSoloEvidencia = Boolean.FALSE;

    @Builder.Default
    @JsonProperty(value = "IndicaEsSoloEvidenciaSpecified")
    private Boolean indicaEsSoloEvidenciaSpecified = Boolean.FALSE;

    @Builder.Default
    @JsonProperty(value = "IndicaImpuestoIncluidoSpecified")
    private Boolean indicaImpuestoIncluidoSpecified = Boolean.TRUE;

    @JsonProperty(value = "SubTotalImpuesto")
    private List<SubTotalImpuestoDTO> subTotalImpuesto;
}
