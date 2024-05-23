package com.robinfood.dtos.request.simba.terceros.esquematributarioterceri;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.dtos.request.simba.IdDTO;
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
public class EsquemaTributarioDTO {

    @JsonProperty(value = "Id")
    private IdDTO id;

    @JsonProperty(value = "Nombre")
    private NombreDTO nombre;
}
