package com.robinfood.dtos.request.simba;

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
public class NombreDTO {

    @JsonProperty(value = "IdLenguaje")
    private String idLenguaje;

    @JsonProperty(value = "Value")
    private String value;
}
