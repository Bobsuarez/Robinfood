package com.robinfood.decorator.terceros;

import com.robinfood.dtos.request.simba.terceros.IdAdicionalDTO;
import com.robinfood.dtos.request.simba.terceros.TerceroProveedorContableDTO;
import com.robinfood.dtos.request.simba.terceros.TercerosDTO;

import java.util.List;

import static com.robinfood.constants.simba.TercerosConstants.ID_ADICIONAL;

public class TerceroProveedorConcrete implements ITerceros {

    public TerceroProveedorConcrete() {
        //Empty
    }

    @Override
    public void decorarTerceroProveedor(TercerosDTO tercerosDTO) {
        buildPropiedadesTercerosProveedorPorDefecto(tercerosDTO);
    }

    private void buildPropiedadesTercerosProveedorPorDefecto(TercerosDTO tercerosDTO) {

        TerceroProveedorContableDTO terceroProveedorContableDTO = TerceroProveedorContableDTO
                .builder()
                .idAdicional(List.of(IdAdicionalDTO.builder()
                        .value(ID_ADICIONAL)
                        .build()))
                .build();

        tercerosDTO.setTerceroProveedorContable(terceroProveedorContableDTO);
    }
}
