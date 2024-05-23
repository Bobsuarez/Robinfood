package com.robinfood.dtos.request.simba.parametros;

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
public class IndicadoresAdicionalesDTO {

    @JsonProperty(value = "Activado")
    private Boolean activado;

    @JsonProperty(value = "NombreIndicador")
    private String nombreIndicador;
}
