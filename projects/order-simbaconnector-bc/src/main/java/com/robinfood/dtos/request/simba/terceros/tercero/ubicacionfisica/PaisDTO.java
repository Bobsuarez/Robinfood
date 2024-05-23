package com.robinfood.dtos.request.simba.terceros.tercero.ubicacionfisica;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.dtos.request.simba.NombreDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaisDTO {

    @JsonProperty(value = "Codigo")
    private CodigoDTO codigo;

    @JsonProperty(value = "Nombre")
    private NombreDTO nombre;
}
