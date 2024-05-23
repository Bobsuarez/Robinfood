package com.robinfood.decorator.terceros;

import com.robinfood.dtos.request.orderbill.OrderDTO;
import com.robinfood.dtos.request.simba.IdDTO;
import com.robinfood.dtos.request.simba.NombreDTO;
import com.robinfood.dtos.request.simba.terceros.TercerosDTO;
import com.robinfood.dtos.request.simba.terceros.esquematributarioterceri.EsquemaTributarioDTO;
import com.robinfood.dtos.request.simba.terceros.esquematributarioterceri.EsquemaTributarioTerceroDTO;
import com.robinfood.dtos.request.simba.terceros.esquematributarioterceri.NivelTributarioDTO;
import com.robinfood.dtos.request.simba.terceros.esquematributarioterceri.NombreRegistradoDTO;
import com.robinfood.dtos.request.simba.terceros.esquematributarioterceri.NumeroIdTributarioDTO;
import com.robinfood.enums.IdentificationTypeEnum;
import com.robinfood.enums.TaxesEnum;
import com.robinfood.util.CalculationNitUtil;
import lombok.NonNull;

import java.util.List;

import static com.robinfood.constants.simba.TercerosConstants.NIVEL_TRIBUTARIO_LISTA_NOMBRE_CLIENTE;
import static com.robinfood.constants.simba.TercerosConstants.NIVEL_TRIBUTARIO_LISTA_VALUE;

public class EsquemaTributarioTerceroClienteDecorator extends AbstractTerceroProveedor {

    private final OrderDTO orderDTO;

    public EsquemaTributarioTerceroClienteDecorator(ITerceros terceroProveedor, OrderDTO orderDTO) {
        super(terceroProveedor);
        this.orderDTO = orderDTO;
    }

    @Override
    public void decorarTerceroProveedor(TercerosDTO tercerosDTOO) {
        super.decorarTerceroProveedor(tercerosDTOO);
        buildUbicacionFisicaTercerosClienteDecorator(tercerosDTOO);
    }

    private void buildUbicacionFisicaTercerosClienteDecorator(TercerosDTO tercerosDTO) {

        EsquemaTributarioTerceroDTO esquemaTributarioTerceroDTO = new EsquemaTributarioTerceroDTO();
        esquemaTributarioTerceroDTO.setNombreRegistrado(getNombreRegistradoDTO());
        esquemaTributarioTerceroDTO.setNumeroIdTributario(getNumeroIdTributario());
        esquemaTributarioTerceroDTO.setNivelTributario(getNivelTributario());
        esquemaTributarioTerceroDTO.setEsquemaTributario(getEsquemaTributario());

        tercerosDTO.getTerceroClienteContable().getTercero()
                .setEsquemaTributarioTercero(List.of(esquemaTributarioTerceroDTO));
    }

    private @NonNull NombreRegistradoDTO getNombreRegistradoDTO() {
        return NombreRegistradoDTO.builder()
                .value(orderDTO.getThirdParty().getFullName())
                .build();
    }

    private @NonNull NumeroIdTributarioDTO getNumeroIdTributario() {
        NumeroIdTributarioDTO numeroIdTributarioDTO = NumeroIdTributarioDTO.builder()
                .smaIdNombre(String.valueOf(IdentificationTypeEnum.valueOfIdentificationInDian(
                        orderDTO.getThirdParty().getDocumentType()).getCodeDian()))
                .value(orderDTO.getThirdParty().getDocumentNumber())
                .build();

        if (orderDTO.getThirdParty().getDocumentType() == IdentificationTypeEnum.NIT.getIdentificationId()) {
            int verificationDigit = CalculationNitUtil.getVerificationDigit(orderDTO.getThirdParty()
                    .getDocumentNumber());
            numeroIdTributarioDTO.setSmaIdCodigo(String.valueOf(verificationDigit));
        }

        return numeroIdTributarioDTO;
    }

    private @NonNull NivelTributarioDTO getNivelTributario() {
        return NivelTributarioDTO.builder()
                .listaNombre(NIVEL_TRIBUTARIO_LISTA_NOMBRE_CLIENTE)
                .value(NIVEL_TRIBUTARIO_LISTA_VALUE)
                .build();
    }

    private @NonNull EsquemaTributarioDTO getEsquemaTributario() {

        String esquemaTribuarioClienteId = TaxesEnum.ZZ.getCodeDian();
        String esquemaTribuarioClienteName = TaxesEnum.ZZ.getNameDian();

        if (orderDTO.getThirdParty().getDocumentType() == IdentificationTypeEnum.NIT.getIdentificationId()) {
            esquemaTribuarioClienteId = TaxesEnum.IVA.getCodeDian();
            esquemaTribuarioClienteName = TaxesEnum.IVA.getNameDian();
        }

        return EsquemaTributarioDTO.builder()
                .id(IdDTO.builder().value(esquemaTribuarioClienteId).build())
                .nombre(NombreDTO.builder().value(esquemaTribuarioClienteName).build())
                .build();
    }
}

