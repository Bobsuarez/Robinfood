package com.robinfood.decorator.extensiones;

import com.robinfood.dtos.request.simba.extensiones.ExtensionesDTO;

import java.util.List;

public abstract class AbstractExtensiones implements IExtensiones {

    private final IExtensiones extensiones;

    public AbstractExtensiones(IExtensiones extensiones) {
        this.extensiones = extensiones;
    }

    @Override
    public void decorarExtensiones(List<ExtensionesDTO> extensionesDTOList) {
        this.extensiones.decorarExtensiones(extensionesDTOList);
    }

}
