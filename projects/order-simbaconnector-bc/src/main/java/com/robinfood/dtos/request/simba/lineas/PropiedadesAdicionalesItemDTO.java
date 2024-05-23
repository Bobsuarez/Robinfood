package com.robinfood.dtos.request.simba.lineas;

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
public class PropiedadesAdicionalesItemDTO {

    @JsonProperty(value = "Nombre")
    private NombreDTO nombre;

    @JsonProperty(value = "Valor")
    private ValorDTO valor;
}
