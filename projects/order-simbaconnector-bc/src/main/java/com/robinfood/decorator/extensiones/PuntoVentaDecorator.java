package com.robinfood.decorator.extensiones;

import com.robinfood.dtos.request.simba.extensiones.ContenidoExtensionDTO;
import com.robinfood.dtos.request.simba.extensiones.ExtensionesDTO;
import com.robinfood.dtos.request.simba.extensiones.InformacionCajaVentaDTO;
import com.robinfood.dtos.request.simba.extensiones.PuntoVentaDTO;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

import static com.robinfood.constants.GeneralConstants.DEFAULT_INTEGER;
import static com.robinfood.constants.GeneralConstants.NO_APLICA;
import static com.robinfood.constants.simba.ExtensionesConstants.PUNTOVENTA_CAJERO;
import static com.robinfood.constants.simba.ExtensionesConstants.PUNTOVENTA_CODIGO_VENTA;
import static com.robinfood.constants.simba.ExtensionesConstants.PUNTOVENTA_PLACA_CAJA;
import static com.robinfood.constants.simba.ExtensionesConstants.PUNTOVENTA_SUBTOTAL;
import static com.robinfood.constants.simba.ExtensionesConstants.PUNTOVENTA_TIPO_CAJA;
import static com.robinfood.constants.simba.ExtensionesConstants.PUNTOVENTA_UBICACION_CAJA;

public class PuntoVentaDecorator extends AbstractExtensiones {

    public PuntoVentaDecorator(IExtensiones extensiones) {
        super(extensiones);
    }

    @Override
    public void decorarExtensiones(List<ExtensionesDTO> extensionesDTOList) {
        super.decorarExtensiones(extensionesDTOList);
        buildDataBenefits(extensionesDTOList);
    }

    private void buildDataBenefits(List<ExtensionesDTO> extensionesDTOList) {
        ExtensionesDTO extensionesDTO = new ExtensionesDTO();
        extensionesDTO.setContenidoExtension(getContenidoExtension());
        extensionesDTOList.add(extensionesDTO);
    }

    private @NonNull ContenidoExtensionDTO getContenidoExtension() {
        return ContenidoExtensionDTO.builder()
                .puntoVenta(getPuntoVenta())
                .build();
    }

    private @NonNull PuntoVentaDTO getPuntoVenta() {
        return PuntoVentaDTO.builder()
                .informacionCajaVenta(getInformacionCajaVenta())
                .build();
    }

    private @NonNull List<InformacionCajaVentaDTO> getInformacionCajaVenta() {

        List<InformacionCajaVentaDTO> informacionCajaVentaDTOList = new ArrayList<>();
        informacionCajaVentaDTOList.add(getInformacionCajaVentaDTO(
                PUNTOVENTA_PLACA_CAJA, NO_APLICA));
        informacionCajaVentaDTOList.add(getInformacionCajaVentaDTO(
                PUNTOVENTA_UBICACION_CAJA, NO_APLICA));
        informacionCajaVentaDTOList.add(getInformacionCajaVentaDTO(
                PUNTOVENTA_CAJERO, NO_APLICA));
        informacionCajaVentaDTOList.add(getInformacionCajaVentaDTO(
                PUNTOVENTA_TIPO_CAJA, NO_APLICA));
        informacionCajaVentaDTOList.add(getInformacionCajaVentaDTO(
                PUNTOVENTA_CODIGO_VENTA, NO_APLICA));
        informacionCajaVentaDTOList.add(getInformacionCajaVentaDTO(
                PUNTOVENTA_SUBTOTAL, String.valueOf(DEFAULT_INTEGER)));

        return informacionCajaVentaDTOList;
    }

    private @NonNull InformacionCajaVentaDTO getInformacionCajaVentaDTO(
            String name, String value
    ) {
        return InformacionCajaVentaDTO.builder()
                .name(name)
                .value(value)
                .build();
    }
}
