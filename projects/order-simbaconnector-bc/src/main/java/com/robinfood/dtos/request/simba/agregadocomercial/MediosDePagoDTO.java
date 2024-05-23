package com.robinfood.dtos.request.simba.agregadocomercial;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.dtos.request.simba.IdDTO;
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
public class MediosDePagoDTO {

    @JsonProperty(value = "Id")
    private IdDTO id;

    @JsonProperty(value = "CodigoMedioDePago")
    private CodigoMedioDePagoDTO codigoMedioDePago;

    @JsonProperty(value = "FechaLimitePago")
    private String fechaLimitePago;

    @JsonProperty(value = "FechaLimitePagoSpecified")
    private Boolean fechaLimitePagoSpecified;

    @JsonProperty(value = "NotaInstruccion")
    private List<NotaInstruccionDTO> notaInstruccion;

    @JsonProperty(value = "IdPago")
    private List<IdPagoDTO> idPago;
}
