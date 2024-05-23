package com.robinfood.decorator.extensiones;

import com.robinfood.dtos.request.orderbill.OrderDTO;
import com.robinfood.dtos.request.simba.extensiones.BeneficiosCompradorDTO;
import com.robinfood.dtos.request.simba.extensiones.ContenidoExtensionDTO;
import com.robinfood.dtos.request.simba.extensiones.ExtensionesDTO;
import com.robinfood.dtos.request.simba.extensiones.InformacionBeneficiosCompradorDTO;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

import static com.robinfood.constants.GeneralConstants.DEFAULT_INTEGER;
import static com.robinfood.constants.simba.ExtensionesConstants.BENEFICIOS_COMPRADOR_CODIGO;
import static com.robinfood.constants.simba.ExtensionesConstants.BENEFICIOS_COMPRADOR_NOMBRE_APELLIDO;
import static com.robinfood.constants.simba.ExtensionesConstants.BENEFICIOS_COMPRADOR_PUNTOS;

public class BeneficiosCompradorDecorador extends AbstractExtensiones {

    private final OrderDTO orderDTO;

    public BeneficiosCompradorDecorador(IExtensiones extensiones, OrderDTO orderDTO) {
        super(extensiones);
        this.orderDTO = orderDTO;
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
                .beneficiosComprador(getBeneficiosComprador())
                .build();
    }

    private @NonNull BeneficiosCompradorDTO getBeneficiosComprador() {
        return BeneficiosCompradorDTO.builder()
                .informacionBeneficiosComprador(getInformacionBeneficiosComprador())
                .build();
    }

    private @NonNull List<InformacionBeneficiosCompradorDTO> getInformacionBeneficiosComprador() {

        List<InformacionBeneficiosCompradorDTO> informacionBeneficiosCompradorDTOList = new ArrayList<>();
        informacionBeneficiosCompradorDTOList.add(getInformacionBeneficiosCompradorDTO(
                BENEFICIOS_COMPRADOR_CODIGO, String.valueOf(DEFAULT_INTEGER)));
        informacionBeneficiosCompradorDTOList.add(getInformacionBeneficiosCompradorDTO(
                BENEFICIOS_COMPRADOR_NOMBRE_APELLIDO, this.orderDTO.getThirdParty().getFullName()));
        informacionBeneficiosCompradorDTOList.add(getInformacionBeneficiosCompradorDTO(
                BENEFICIOS_COMPRADOR_PUNTOS, String.valueOf(DEFAULT_INTEGER)));

        return informacionBeneficiosCompradorDTOList;
    }

    private @NonNull InformacionBeneficiosCompradorDTO getInformacionBeneficiosCompradorDTO(
            String name, String value
    ) {
        return InformacionBeneficiosCompradorDTO.builder()
                .name(name)
                .value(value)
                .build();
    }
}
