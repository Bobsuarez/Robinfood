package com.robinfood.dtos.request.simba.terceros.esquematributarioterceri;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.dtos.request.simba.IdDTO;
import com.robinfood.dtos.request.simba.terceros.tercero.ubicacionfisica.CiudadDTO;
import com.robinfood.dtos.request.simba.terceros.tercero.ubicacionfisica.DepartamentoDTO;
import com.robinfood.dtos.request.simba.terceros.tercero.ubicacionfisica.LineaDireccionDTO;
import com.robinfood.dtos.request.simba.terceros.tercero.ubicacionfisica.PaisDTO;
import com.robinfood.dtos.request.simba.terceros.tercero.ubicacionfisica.SubdivisionPaisCodigoDTO;
import com.robinfood.dtos.request.simba.terceros.tercero.ubicacionfisica.SubdivisionPaisDTO;
import com.robinfood.dtos.request.simba.terceros.tercero.ubicacionfisica.ZonaPostalDTO;
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
public class DireccionParaImpuestosDTO {

    @JsonProperty(value = "Departamento")
    private DepartamentoDTO departamento;

    @JsonProperty(value = "Ciudad")
    private CiudadDTO ciudad;

    @JsonProperty(value = "Id")
    private IdDTO id;

    @JsonProperty(value = "LineaDireccion")
    private List<LineaDireccionDTO> lineaDireccion;

    @JsonProperty(value = "Pais")
    private PaisDTO pais;

    @JsonProperty(value = "SubdivisionPais")
    private SubdivisionPaisDTO subdivisionPais;

    @JsonProperty(value = "SubdivisionPaisCodigo")
    private SubdivisionPaisCodigoDTO subdivisionPaisCodigo;

    @JsonProperty(value = "ZonaPostal")
    private ZonaPostalDTO zonaPostal;
}
