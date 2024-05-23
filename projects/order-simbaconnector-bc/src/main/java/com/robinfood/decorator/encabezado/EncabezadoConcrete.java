package com.robinfood.decorator.encabezado;

import com.robinfood.dtos.request.simba.encabezado.CodigoMonedaDTO;
import com.robinfood.dtos.request.simba.encabezado.EncabezadoDTO;
import com.robinfood.dtos.request.simba.encabezado.IdPersonalizacionDTO;
import com.robinfood.dtos.request.simba.encabezado.TipoDeFacturaDTO;
import com.robinfood.enums.TipoDeFacturaEnum;

import static com.robinfood.constants.simba.EncabezadoConstants.ID_PERSONALIZACION;
import static com.robinfood.constants.simba.EncabezadoConstants.TIPO_DOC_ELECTRONICO;
import static com.robinfood.constants.simba.SimbaGeneralConstants.CODIGO_MONEDA;

public class EncabezadoConcrete implements IEncabezado {

    public EncabezadoConcrete() {
        // Empty
    }

    @Override
    public void decorarEncabezado(EncabezadoDTO encabezadoDTO) {
        adicionarPropiedadEncabezadoPorDefecto(encabezadoDTO);
    }

    private void adicionarPropiedadEncabezadoPorDefecto(EncabezadoDTO encabezadoDTO) {
        encabezadoDTO.setTipoDocElectronico(TIPO_DOC_ELECTRONICO);
        encabezadoDTO.setIdPersonalizacion(
                IdPersonalizacionDTO.builder()
                        .value(ID_PERSONALIZACION)
                        .build()
        );
        encabezadoDTO.setTipoDeFactura(
                TipoDeFacturaDTO.builder()
                        .value(TipoDeFacturaEnum.POS_ELECTRONIC.getTipoDoucmeto())
                        .build()
        );
        encabezadoDTO.setCodigoMoneda(
                CodigoMonedaDTO.builder()
                        .value(CODIGO_MONEDA)
                        .build()

        );
    }
}
