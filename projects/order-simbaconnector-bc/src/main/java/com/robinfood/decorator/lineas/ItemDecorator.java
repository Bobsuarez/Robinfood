package com.robinfood.decorator.lineas;

import com.robinfood.dtos.request.simba.NombreDTO;
import com.robinfood.dtos.request.simba.lineas.DatosAuxDTO;
import com.robinfood.dtos.request.simba.lineas.DescripcionDTO;
import com.robinfood.dtos.request.simba.lineas.IdItemDTO;
import com.robinfood.dtos.request.simba.lineas.IdItemEstandarDTO;
import com.robinfood.dtos.request.simba.lineas.ItemDTO;
import com.robinfood.dtos.request.simba.lineas.LineasDTO;
import com.robinfood.dtos.request.simba.lineas.NotaDTO;
import com.robinfood.dtos.request.simba.lineas.PropiedadesAdicionalesItemDTO;
import com.robinfood.dtos.request.simba.lineas.ValorDTO;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

import static com.robinfood.constants.simba.LineasConstants.PROPIEDADES_ADICIONALES_NOMBRE;
import static com.robinfood.constants.simba.LineasConstants.PROPIEDADES_ADICIONALES_VALOR;
import static com.robinfood.constants.simba.LineasConstants.SMA_ID_CODIGO;
import static com.robinfood.constants.simba.LineasConstants.SMA_ID_NOMBRE;

public class ItemDecorator extends AbstractLineas {

    public ItemDecorator(ILineas lineas) {
        super(lineas);
    }

    @Override
    public void decorarLineas(List<LineasDTO> lineasDTOs) {
        super.decorarLineas(lineasDTOs);
        buildLineaItem(lineasDTOs);
    }

    private void buildLineaItem(List<LineasDTO> lineasDTOs) {

        lineasDTOs.forEach(linea -> linea.setItem(buildItem(linea)));
    }

    private @NonNull ItemDTO buildItem(LineasDTO lineasDTO) {
        return ItemDTO.builder()
                .descripcion(setDescripcion(lineasDTO.getNota()))
                .nombre(setNombre(lineasDTO.getNota()))
                .idItemEstandar(getSetItemStandard(lineasDTO.getDatosAux()))
                .propiedadesAdicionalesItem(setPropiedadesAdicionales())
                .build();
    }

    private @NonNull List<DescripcionDTO> setDescripcion(List<NotaDTO> notaDTOS) {
        return notaDTOS.stream()
                .map(notaDTO ->
                        DescripcionDTO.builder()
                                .value(notaDTO.getValue())
                                .build())
                .collect(Collectors.toList());
    }

    private @NonNull NombreDTO setNombre(List<NotaDTO> notaDTOS) {
        return notaDTOS.stream()
                .map(notaDTO ->
                        NombreDTO.builder()
                                .value(notaDTO.getValue())
                                .build())
                .findFirst()
                .orElse(NombreDTO.builder().value("").build());
    }

    private @NonNull IdItemEstandarDTO getSetItemStandard(DatosAuxDTO datosAuxDTO) {

        return IdItemEstandarDTO.builder()
                .id(IdItemDTO.builder()
                        .smaIdCodigo(SMA_ID_CODIGO)
                        .smaIdNombre(SMA_ID_NOMBRE)
                        .value(datosAuxDTO.getFinalProductId().toString())
                        .build())
                .build();
    }

    private @NonNull List<PropiedadesAdicionalesItemDTO> setPropiedadesAdicionales() {

        return List.of(PropiedadesAdicionalesItemDTO.builder()
                .nombre(NombreDTO.builder().value(PROPIEDADES_ADICIONALES_NOMBRE).build())
                .valor(ValorDTO.builder().value(PROPIEDADES_ADICIONALES_VALOR).build())
                .build());
    }
}

