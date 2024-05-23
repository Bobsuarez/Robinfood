package com.robinfood.decorator.parametrosdecorador;

import com.robinfood.dtos.request.simba.parametros.ParametrosDTO;

import static com.robinfood.constants.GeneralConstants.NOMBRE_SOFTWARE;
import static com.robinfood.constants.GeneralConstants.SOFTWARE_VERSION;
import static com.robinfood.constants.simba.ParametrosConstants.ENVIRONMENT_TYPE;
import static com.robinfood.constants.simba.ParametrosConstants.MODO_RESPUESTA;
import static com.robinfood.constants.simba.ParametrosConstants.PASSWORD_EMPRESA;
import static com.robinfood.constants.simba.ParametrosConstants.TIPO_REPORTE;
import static com.robinfood.constants.simba.ParametrosConstants.TOKEN_EMPRESA;
import static com.robinfood.constants.simba.ParametrosConstants.VERSION_DOC_ELECTRONICO;

public class ParametrosConcrete implements IParametros {

    public ParametrosConcrete() {
        //Empty
    }

    @Override
    public void decorarParametros(ParametrosDTO parametrosDTO) {
        adicionarParametrosPorDefecto(parametrosDTO);
    }

    private void adicionarParametrosPorDefecto(ParametrosDTO parametrosDTO) {
        parametrosDTO.setVersionDocElectronico(VERSION_DOC_ELECTRONICO);
        parametrosDTO.setNombreSistemaEmisor(NOMBRE_SOFTWARE);
        parametrosDTO.setVersionSistemaEmisor(SOFTWARE_VERSION);
        parametrosDTO.setModoRespuesta(MODO_RESPUESTA);
        parametrosDTO.setTipoAmbiente(ENVIRONMENT_TYPE);
        parametrosDTO.setTokenEmpresa(TOKEN_EMPRESA);
        parametrosDTO.setPasswordEmpresa(PASSWORD_EMPRESA);
        parametrosDTO.setTipoReporte(TIPO_REPORTE);
    }
}
