package com.robinfood.dtos.request.simba.totales;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.dtos.request.simba.lineas.totalimpuestos.TotalImpuestoDTO;
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
public class TotalesDTO {

    @JsonProperty(value = "TotalImpuestos")
    private List<TotalImpuestoDTO> totalImpuestos;

    @JsonProperty(value = "TotalMonetario")
    private TotalMonetarioDTO totalMonetario;
}
