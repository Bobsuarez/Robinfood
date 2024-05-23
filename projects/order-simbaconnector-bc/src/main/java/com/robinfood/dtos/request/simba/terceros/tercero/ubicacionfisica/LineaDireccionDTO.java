package com.robinfood.dtos.request.simba.terceros.tercero.ubicacionfisica;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.dtos.request.simba.terceros.TextoLineaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LineaDireccionDTO {

    @JsonProperty(value = "TextoLinea")
    private TextoLineaDTO textoLinea;
}
