package com.robinfood.decorator.agregadocomercial;

import com.robinfood.dtos.request.simba.agregadocomercial.AgregadoComercialDTO;
import com.robinfood.dtos.request.simba.agregadocomercial.IdPagoDTO;
import com.robinfood.dtos.request.simba.agregadocomercial.MediosDePagoDTO;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.robinfood.constants.GeneralConstants.DEFAULT_INTEGER;

public class IdPagoDecorator extends AbstractAgregadoComercial {

    public IdPagoDecorator(IAgregadoComercial agregadoComercial) {
        super(agregadoComercial);
    }

    @Override
    public void decorarAgregadoComercial(AgregadoComercialDTO agregadoComercialDTO) {
        super.decorarAgregadoComercial(agregadoComercialDTO);
        buildIdPago(agregadoComercialDTO);
    }

    private void buildIdPago(AgregadoComercialDTO agregadoComercialDTO) {

        AtomicInteger id = new AtomicInteger(DEFAULT_INTEGER);

        agregadoComercialDTO.getMediosDePago().forEach((MediosDePagoDTO mediosDePagoDTO) ->
                mediosDePagoDTO.setIdPago(List.of(IdPagoDTO.builder()
                        .value(String.valueOf(id.incrementAndGet()))
                        .build()))
        );
    }
}

