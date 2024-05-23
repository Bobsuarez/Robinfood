package com.robinfood.decorator.totales;

import com.robinfood.dtos.request.simba.totales.TotalesDTO;

public abstract class AbstractTotales implements ITotales {

    private final ITotales totales;

    public AbstractTotales(ITotales totales) {
        this.totales = totales;
    }

    @Override
    public void decorarTotales(TotalesDTO totalesDTO) {
        this.totales.decorarTotales(totalesDTO);
    }

}
