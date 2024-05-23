package com.robinfood.dtos.request.simba.parametros;

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
public class ContactoReceptorDTO {

    @JsonProperty(value = "CorreoElectronico")
    private String correoElectronico;

    @JsonProperty(value = "IdEtiquetaUbicacionCorreo")
    private String idEtiquetaUbicacionCorreo;

    @JsonProperty(value = "SoloEnvioCasoDeFalloSpecified")
    private Boolean soloEnvioCasoDeFalloSpecified;
}
