package com.robinfood.decorator.terceros;

import com.robinfood.dtos.request.simba.terceros.TercerosDTO;

public abstract class AbstractTerceroProveedor implements ITerceros {

    private final ITerceros terceroProveedor;

    public AbstractTerceroProveedor(ITerceros terceroProveedor) {
        this.terceroProveedor = terceroProveedor;
    }

    @Override
    public void decorarTerceroProveedor(TercerosDTO tercerosDTO) {
        this.terceroProveedor.decorarTerceroProveedor(tercerosDTO);
    }
}
