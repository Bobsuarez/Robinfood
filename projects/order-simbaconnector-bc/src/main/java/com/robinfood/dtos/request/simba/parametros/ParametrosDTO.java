package com.robinfood.dtos.request.simba.parametros;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class ParametrosDTO {

    @JsonProperty(value = "VersionDocElectronico")
    private String versionDocElectronico;

    @JsonProperty(value = "NombreSistemaEmisor")
    private String nombreSistemaEmisor;

    @JsonProperty(value = "VersionSistemaEmisor")
    private String versionSistemaEmisor;

    @JsonProperty(value = "ModoRespuesta")
    private String modoRespuesta;

    @JsonProperty(value = "TipoAmbiente")
    private String tipoAmbiente;

    @JsonProperty(value = "TokenEmpresa")
    private String tokenEmpresa;

    @JsonProperty(value = "PasswordEmpresa")
    private String passwordEmpresa;

    @JsonProperty(value = "TipoReporte")
    private String tipoReporte;

    @JsonProperty(value = "Personalizacion")
    private String personalizacion;

    @JsonProperty(value = "ContactoReceptor")
    private List<ContactoReceptorDTO> contactoReceptor;

    @JsonProperty(value = "IndicadoresAdicionales")
    private List<IndicadoresAdicionalesDTO> indicadoresAdicionales;
}
