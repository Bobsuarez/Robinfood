package com.robinfood.dtos.request.simba.terceros.tercero.ubicacionfisica;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.dtos.request.simba.IdDTO;
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
public class DireccionDTO {

    @JsonProperty(value = "Departamento")
    private DepartamentoDTO departamento;

    @JsonProperty(value = "Ciudad")
    private CiudadDTO ciudad;

    @JsonProperty(value = "Id")
    private IdDTO id;

    @JsonProperty(value = "lineaDireccion")
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
