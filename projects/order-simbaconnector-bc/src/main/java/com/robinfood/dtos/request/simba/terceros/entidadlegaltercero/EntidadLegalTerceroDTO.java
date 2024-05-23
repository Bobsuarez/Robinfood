package com.robinfood.dtos.request.simba.terceros.entidadlegaltercero;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.dtos.request.simba.terceros.esquematributarioterceri.NombreRegistradoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EntidadLegalTerceroDTO {

    @JsonProperty(value = "EsquemaRegistroCorporativo")
    private EsquemaRegistroCorporativoDTO esquemaRegistroCorporativo;

    @JsonProperty(value = "NombreRegistrado")
    private NombreRegistradoDTO nombreRegistrado;

    @JsonProperty(value = "IndicaAccionesPagadasSpecified")
    private Boolean indicaAccionesPagadasSpecified;

    @JsonProperty(value = "IndicaPropietarioPersonaSpecified")
    private Boolean indicaPropietarioPersonaSpecified;

    @JsonProperty(value = "NumeroIdLegal")
    private NumeroIdLegalDTO numeroIdLegal;

    @JsonProperty(value = "FechaExpiracionRegistroSpecified")
    private Boolean fechaExpiracionRegistroSpecified;

    @JsonProperty(value = "FechaRegistroSpecified")
    private Boolean fechaRegistroSpecified;
}
