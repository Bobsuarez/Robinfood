package com.robinfood.dtos.request.simba.terceros.esquematributarioterceri;

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
public class EsquemaTributarioTerceroDTO {

    @JsonProperty(value = "DireccionParaImpuestos")
    private DireccionParaImpuestosDTO direccionParaImpuestos;

    @JsonProperty(value = "EsquemaTributario")
    private EsquemaTributarioDTO esquemaTributario;

    @JsonProperty(value = "NivelTributario")
    private NivelTributarioDTO nivelTributario;

    @JsonProperty(value = "NombreRegistrado")
    private NombreRegistradoDTO nombreRegistrado;

    @JsonProperty(value = "NumeroIdTributario")
    private NumeroIdTributarioDTO numeroIdTributario;
}
