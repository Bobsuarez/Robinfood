package com.robinfood.decorator.terceros;

import com.robinfood.dtos.request.simba.terceros.TercerosDTO;
import com.robinfood.dtos.request.simba.terceros.tercero.CodigoClasificacionIndustriaDTO;
import com.robinfood.dtos.request.simba.terceros.tercero.IdTerceroDTO;
import com.robinfood.dtos.request.simba.terceros.tercero.NombreTerceroDTO;
import com.robinfood.dtos.request.simba.terceros.tercero.TerceroDTO;

import java.util.List;

import static com.robinfood.constants.simba.TercerosConstants.CODIGO_CLASIFICACION_INDUSTRIA;
import static com.robinfood.constants.simba.TercerosConstants.NIT;
import static com.robinfood.constants.simba.TercerosConstants.NOMBRE_EMPRESA;
import static com.robinfood.constants.simba.TercerosConstants.SMA_ID_CODIGO;
import static com.robinfood.constants.simba.TercerosConstants.SMA_ID_NOMBRE;

public class TerceroProveedorContableFacturaDecorator extends AbstractTerceroProveedor {

    public TerceroProveedorContableFacturaDecorator(ITerceros terceroProveedor) {
        super(terceroProveedor);
    }

    @Override
    public void decorarTerceroProveedor(TercerosDTO tercerosDTOO) {
        super.decorarTerceroProveedor(tercerosDTOO);
        buildTercerosProveedorDecorator(tercerosDTOO);
    }

    private void buildTercerosProveedorDecorator(TercerosDTO tercerosDTO) {
        TerceroDTO terceroDTO = new TerceroDTO();

        terceroDTO.setCodigoClasificacionIndustria(CodigoClasificacionIndustriaDTO.builder()
                .value(CODIGO_CLASIFICACION_INDUSTRIA)
                .build());
        terceroDTO.setIdTercero(
                List.of(
                        IdTerceroDTO.builder()
                                .smaIdCodigo(SMA_ID_CODIGO)
                                .smaIdNombre(SMA_ID_NOMBRE)
                                .value(NIT)
                                .build()

                )
        );
        terceroDTO.setNombreTercero(
                List.of(NombreTerceroDTO.builder()
                        .value(NOMBRE_EMPRESA)
                        .build())

        );

        tercerosDTO.getTerceroProveedorContable().setTercero(terceroDTO);
    }

}

