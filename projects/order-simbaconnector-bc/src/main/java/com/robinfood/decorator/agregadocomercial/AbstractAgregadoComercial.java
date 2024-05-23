package com.robinfood.decorator.agregadocomercial;

import com.robinfood.dtos.request.simba.agregadocomercial.AgregadoComercialDTO;

public abstract class AbstractAgregadoComercial implements IAgregadoComercial {

    private final IAgregadoComercial agregadoComercial;

    public AbstractAgregadoComercial(IAgregadoComercial agregadoComercial) {
        this.agregadoComercial = agregadoComercial;
    }

    @Override
    public void decorarAgregadoComercial(AgregadoComercialDTO agregadoComercialDTO) {
        this.agregadoComercial.decorarAgregadoComercial(agregadoComercialDTO);
    }
}
