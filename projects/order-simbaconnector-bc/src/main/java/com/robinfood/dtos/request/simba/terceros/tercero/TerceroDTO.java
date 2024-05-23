package com.robinfood.dtos.request.simba.terceros.tercero;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.dtos.request.simba.terceros.contacto.ContactoDTO;
import com.robinfood.dtos.request.simba.terceros.entidadlegaltercero.EntidadLegalTerceroDTO;
import com.robinfood.dtos.request.simba.terceros.esquematributarioterceri.EsquemaTributarioTerceroDTO;
import com.robinfood.dtos.request.simba.terceros.tercero.ubicacionfisica.UbicacionFisicaDTO;
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
public class TerceroDTO {

    @JsonProperty(value = "CodigoClasificacionIndustria")
    private CodigoClasificacionIndustriaDTO codigoClasificacionIndustria;

    @JsonProperty(value = "IdTercero")
    private List<IdTerceroDTO> idTercero;

    @JsonProperty(value = "NombreTercero")
    private List<NombreTerceroDTO> nombreTercero;

    @JsonProperty(value = "UbicacionFisica")
    private UbicacionFisicaDTO ubicacionFisica;

    @JsonProperty(value = "EsquemaTributarioTercero")
    private List<EsquemaTributarioTerceroDTO> esquemaTributarioTercero;

    @JsonProperty(value = "EntidadLegalTercero")
    private List<EntidadLegalTerceroDTO> entidadLegalTercero;

    @JsonProperty(value = "Contacto")
    private ContactoDTO contacto;

    @JsonProperty(value = "IndicaATravesDeSpecified")
    private Boolean indicaATravesDeSpecified;

    @JsonProperty(value = "IndicaAtencionASpecified")
    private Boolean indicaAtencionASpecified;
}
