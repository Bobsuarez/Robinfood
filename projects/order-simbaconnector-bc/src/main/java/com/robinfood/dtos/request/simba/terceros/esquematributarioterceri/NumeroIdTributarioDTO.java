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
public class NumeroIdTributarioDTO {

    @JsonProperty(value = "SmaIdCodigo")
    private String smaIdCodigo;

    @JsonProperty(value = "SmaIdNombre")
    private String smaIdNombre;

    @JsonProperty(value = "Value")
    private String value;
}
