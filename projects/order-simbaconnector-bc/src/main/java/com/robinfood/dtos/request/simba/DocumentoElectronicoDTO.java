package com.robinfood.dtos.request.simba;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.dtos.request.simba.agregadocomercial.AgregadoComercialDTO;
import com.robinfood.dtos.request.simba.encabezado.EncabezadoDTO;
import com.robinfood.dtos.request.simba.extensiones.ExtensionesDTO;
import com.robinfood.dtos.request.simba.lineas.LineasDTO;
import com.robinfood.dtos.request.simba.parametros.ParametrosDTO;
import com.robinfood.dtos.request.simba.terceros.TercerosDTO;
import com.robinfood.dtos.request.simba.totales.TotalesDTO;
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
public class DocumentoElectronicoDTO {

    @JsonProperty(value = "AgregadoComercial")
    private AgregadoComercialDTO agregadoComercial;

    @JsonProperty(value = "Encabezado")
    private EncabezadoDTO encabezado;

    @JsonProperty(value = "Extensiones")
    private ExtensionesDTO extensiones;

    @JsonProperty(value = "Lineas")
    private List<LineasDTO> lineas;

    @JsonProperty(value = "Parametros")
    private ParametrosDTO parametros;

    @JsonProperty(value = "Terceros")
    private TercerosDTO terceros;

    @JsonProperty(value = "Totales")
    private TotalesDTO totales;
}
