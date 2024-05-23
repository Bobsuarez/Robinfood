package com.robinfood.dtos.request.simba.totales;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.dtos.request.simba.lineas.totalimpuestos.ValorAjusteRedondeoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TotalMonetarioDTO {

    @JsonProperty(value = "ValorBruto")
    private ValorBrutoDTO valorBruto;

    @JsonProperty(value = "ValorBaseImpuestos")
    private ValorBaseImpuestosDTO valorBaseImpuestos;

    @JsonProperty(value = "TotalMasImpuestos")
    private TotalMasImpuestosDTO totalMasImpuestos;

    @JsonProperty(value = "TotalCargos")
    private TotalCargosDTO totalCargos;

    @JsonProperty(value = "ValorAjusteRedondeo")
    private ValorAjusteRedondeoDTO valorAjusteRedondeo;

    @JsonProperty(value = "ValorAPagar")
    private ValorAPagarDTO valorAPagar;

    @JsonProperty(value = "ValorAPagarAlternativo")
    private ValorAPagarAlternativoDTO valorAPagarAlternativo;

}
