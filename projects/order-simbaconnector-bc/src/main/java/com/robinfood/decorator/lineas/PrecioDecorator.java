package com.robinfood.decorator.lineas;

import com.robinfood.dtos.request.simba.lineas.CantidadBaseDTO;
import com.robinfood.dtos.request.simba.lineas.LineasDTO;
import com.robinfood.dtos.request.simba.lineas.PrecioDTO;
import com.robinfood.dtos.request.simba.lineas.ValorPrecioDTO;
import lombok.NonNull;

import java.util.List;

public class PrecioDecorator extends AbstractLineas {

    public PrecioDecorator(ILineas lineas) {
        super(lineas);
    }

    @Override
    public void decorarLineas(List<LineasDTO> lineasDTOs) {
        super.decorarLineas(lineasDTOs);
        buildLineaPrecio(lineasDTOs);
    }

    private void buildLineaPrecio(List<LineasDTO> lineasDTOs) {
        lineasDTOs.forEach((LineasDTO linea) ->
                linea.setPrecio(buildPrecio(linea))
        );
    }

    private @NonNull PrecioDTO buildPrecio(LineasDTO lineasDTO) {
        return PrecioDTO.builder()
                .cantidadBase(setCantidad(lineasDTO))
                .valorPrecio(setValorPrecio(lineasDTO))
                .build();
    }

    private @NonNull CantidadBaseDTO setCantidad(LineasDTO lineasDTO) {
        return CantidadBaseDTO.builder()
                .codUnidad(lineasDTO.getCantidad().getCodUnidad())
                .value(lineasDTO.getCantidad().getValue())
                .build();
    }

    private @NonNull ValorPrecioDTO setValorPrecio(LineasDTO lineasDTO) {
        return ValorPrecioDTO.builder()
                .idMoneda(lineasDTO.getValorNeto().getIdMoneda())
                .value(lineasDTO.getDatosAux().getPrecioUnitario())
                .build();
    }
}

