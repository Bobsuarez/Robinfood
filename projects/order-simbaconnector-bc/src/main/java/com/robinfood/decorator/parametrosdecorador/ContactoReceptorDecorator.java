package com.robinfood.decorator.parametrosdecorador;

import com.robinfood.dtos.request.orderbill.OrderDTO;
import com.robinfood.dtos.request.simba.parametros.ContactoReceptorDTO;
import com.robinfood.dtos.request.simba.parametros.ParametrosDTO;

import java.util.List;

import static com.robinfood.constants.simba.ParametrosConstants.ID_ETIQUETA_UBICACION_CORREO;

public class ContactoReceptorDecorator extends AbstractParametros {

    private final OrderDTO orderDTO;

    public ContactoReceptorDecorator(IParametros parametros, OrderDTO orderDTO) {
        super(parametros);
        this.orderDTO = orderDTO;
    }

    @Override
    public void decorarParametros(ParametrosDTO parametrosDTO) {
        super.decorarParametros(parametrosDTO);
        buildContactoReceptor(parametrosDTO);
    }

    private void buildContactoReceptor(ParametrosDTO parametrosDTO) {
        parametrosDTO.setContactoReceptor(
                List.of(
                        ContactoReceptorDTO.builder()
                                .correoElectronico(orderDTO.getThirdParty().getEmail())
                                .idEtiquetaUbicacionCorreo(ID_ETIQUETA_UBICACION_CORREO)
                                .build()

                )
        );
    }

}

