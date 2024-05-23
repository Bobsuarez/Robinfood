package com.robinfood.dtos.request.simba.lineas;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.dtos.request.simba.IdDTO;
import com.robinfood.dtos.request.simba.lineas.totalimpuestos.TotalImpuestoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LineasDTO {

    @JsonProperty(value = "Id")
    private IdDTO id;

    @JsonProperty(value = "Nota")
    private List<NotaDTO> nota;

    @JsonProperty(value = "Cantidad")
    private CantidadDTO cantidad;

    @JsonProperty(value = "ValorNeto")
    private ValorNetoDTO valorNeto;

    @JsonProperty(value = "CargoDescuento")
    private List<CargoDescuentoDTO> cargoDescuento;

    @JsonProperty(value = "TotalImpuesto")
    private List<TotalImpuestoDTO> totalImpuesto;

    @JsonProperty(value = "Item")
    private ItemDTO item;

    @JsonProperty(value = "Precio")
    private PrecioDTO precio;

    @JsonProperty(value = "FechaVigenciaImpuestoSpecified")
    private Boolean fechaVigenciaImpuestoSpecified;

    @JsonProperty(value = "IndicaEsGratisSpecified")
    private Boolean indicaEsGratisSpecified;

    @JsonIgnore
    private BigDecimal precioUnitario;

    @JsonIgnore
    private DatosAuxDTO datosAux;
}
