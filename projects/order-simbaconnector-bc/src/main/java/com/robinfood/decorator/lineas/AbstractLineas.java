package com.robinfood.decorator.lineas;

import com.robinfood.dtos.request.simba.lineas.LineasDTO;

import java.util.List;

public abstract class AbstractLineas implements ILineas {

    private final ILineas lineas;

    public AbstractLineas(ILineas lineas) {
        this.lineas = lineas;
    }

    @Override
    public void decorarLineas(List<LineasDTO> lineasDTOs) {
        this.lineas.decorarLineas(lineasDTOs);
    }

}
