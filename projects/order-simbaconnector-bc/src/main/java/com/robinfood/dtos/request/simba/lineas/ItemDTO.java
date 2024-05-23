package com.robinfood.dtos.request.simba.lineas;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.dtos.request.simba.IdDTO;
import com.robinfood.dtos.request.simba.NombreDTO;
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
public class ItemDTO {

    @JsonProperty(value = "Id")
    private IdDTO id;

    @JsonProperty(value = "Descripcion")
    private List<DescripcionDTO> descripcion;

    @JsonProperty(value = "Nombre")
    private NombreDTO nombre;

    @JsonProperty(value = "IdItemEstandar")
    private IdItemEstandarDTO idItemEstandar;

    @JsonProperty(value = "IndicaDesdeCatalogoSpecified")
    private Boolean indicaDesdeCatalogoSpecified;

    @JsonProperty(value = "IndicadorDePeligroSpecified")
    private Boolean indicadorDePeligroSpecified;

    @JsonProperty(value = "ItemsPorEmpaqueSpecified")
    private Boolean itemsPorEmpaqueSpecified;

    @JsonProperty(value = "PropiedadesAdicionalesItem")
    private List<PropiedadesAdicionalesItemDTO> propiedadesAdicionalesItem;
}
