package com.robinfood.decorator.parametrosdecorador;

import com.robinfood.dtos.request.simba.parametros.IndicadoresAdicionalesDTO;
import com.robinfood.dtos.request.simba.parametros.ParametrosDTO;

import java.util.List;

import static com.robinfood.constants.simba.ParametrosConstants.ACTIVADO;
import static com.robinfood.constants.simba.ParametrosConstants.NOMBRE_INDICADOR;
import static com.robinfood.constants.simba.ParametrosConstants.NOMBRE_INDICADOR_DIAN_REQUI;

public class IndicadoresAdicionalesDecorator extends AbstractParametros {

    public IndicadoresAdicionalesDecorator(IParametros parametros) {
        super(parametros);
    }

    @Override
    public void decorarParametros(ParametrosDTO parametrosDTO) {
        super.decorarParametros(parametrosDTO);
        buildIndicadoresAdicionales(parametrosDTO);
    }

    private void buildIndicadoresAdicionales(ParametrosDTO parametrosDTO) {
        parametrosDTO.setIndicadoresAdicionales(
                List.of(
                        IndicadoresAdicionalesDTO.builder()
                                .activado(ACTIVADO)
                                .nombreIndicador(NOMBRE_INDICADOR)
                                .build(),
                        IndicadoresAdicionalesDTO.builder()
                                .activado(ACTIVADO)
                                .nombreIndicador(NOMBRE_INDICADOR_DIAN_REQUI)
                                .build()

                )
        );
    }
}

