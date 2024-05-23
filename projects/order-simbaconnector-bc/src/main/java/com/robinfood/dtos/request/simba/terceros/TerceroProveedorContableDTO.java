package com.robinfood.dtos.request.simba.terceros;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.dtos.request.simba.terceros.tercero.TerceroDTO;
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
public class TerceroProveedorContableDTO {

    @JsonProperty(value = "IdAdicional")
    private List<IdAdicionalDTO> idAdicional;

    @JsonProperty(value = "Tercero")
    private TerceroDTO tercero;
}
