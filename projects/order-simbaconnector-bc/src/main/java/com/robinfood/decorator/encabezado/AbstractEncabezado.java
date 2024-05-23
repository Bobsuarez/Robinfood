package com.robinfood.decorator.encabezado;

import com.robinfood.dtos.request.simba.encabezado.EncabezadoDTO;

public abstract class AbstractEncabezado implements IEncabezado {

    private final IEncabezado iEncabezado;

    public AbstractEncabezado(IEncabezado iEncabezado) {
        this.iEncabezado = iEncabezado;
    }

    @Override
    public void decorarEncabezado(EncabezadoDTO encabezadoDTO) {
        this.iEncabezado.decorarEncabezado(encabezadoDTO);
    }

}
