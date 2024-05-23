package com.robinfood.decorator.parametrosdecorador;

import com.robinfood.dtos.request.simba.parametros.ParametrosDTO;

public abstract class AbstractParametros implements IParametros {

    private final IParametros iParametros;

    public AbstractParametros(IParametros iParametros) {
        this.iParametros = iParametros;
    }

    @Override
    public void decorarParametros(ParametrosDTO parametrosDTO) {
        this.iParametros.decorarParametros(parametrosDTO);
    }

}
