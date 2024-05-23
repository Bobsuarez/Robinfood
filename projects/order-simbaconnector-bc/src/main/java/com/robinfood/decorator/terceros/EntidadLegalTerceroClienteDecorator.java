package com.robinfood.decorator.terceros;

import com.robinfood.dtos.request.orderbill.OrderDTO;
import com.robinfood.dtos.request.orderbill.ThirdPartyDTO;
import com.robinfood.dtos.request.simba.IdDTO;
import com.robinfood.dtos.request.simba.terceros.TercerosDTO;
import com.robinfood.dtos.request.simba.terceros.entidadlegaltercero.EntidadLegalTerceroDTO;
import com.robinfood.dtos.request.simba.terceros.entidadlegaltercero.EsquemaRegistroCorporativoDTO;
import com.robinfood.dtos.request.simba.terceros.entidadlegaltercero.NumeroIdLegalDTO;
import com.robinfood.dtos.request.simba.terceros.esquematributarioterceri.NombreRegistradoDTO;
import com.robinfood.enums.IdentificationTypeEnum;
import com.robinfood.util.CalculationNitUtil;
import lombok.NonNull;

import java.util.List;

public class EntidadLegalTerceroClienteDecorator extends AbstractTerceroProveedor {

    private final OrderDTO orderDTO;

    public EntidadLegalTerceroClienteDecorator(ITerceros terceroProveedor, OrderDTO orderDTO) {
        super(terceroProveedor);
        this.orderDTO = orderDTO;
    }

    @Override
    public void decorarTerceroProveedor(TercerosDTO tercerosDTOO) {
        super.decorarTerceroProveedor(tercerosDTOO);
        buildEntidadLegalTerceroClienteDecorator(tercerosDTOO);
    }

    private void buildEntidadLegalTerceroClienteDecorator(TercerosDTO tercerosDTO) {

        EntidadLegalTerceroDTO entidadLegalTerceroDTO = new EntidadLegalTerceroDTO();

        entidadLegalTerceroDTO.setNombreRegistrado(getNombreRegistrado());
        entidadLegalTerceroDTO.setNumeroIdLegal(getNumeroIdLegal());
        entidadLegalTerceroDTO.setEsquemaRegistroCorporativo(getEsquemaRegistroCorporativo());

        tercerosDTO.getTerceroClienteContable().getTercero().setEntidadLegalTercero(
                List.of(entidadLegalTerceroDTO)
        );
    }

    private @NonNull NombreRegistradoDTO getNombreRegistrado() {
        return NombreRegistradoDTO.builder()
                .value(orderDTO.getThirdParty().getFullName())
                .build();
    }

    private @NonNull NumeroIdLegalDTO getNumeroIdLegal() {

        ThirdPartyDTO thirdPartyDTO = orderDTO.getThirdParty();

        NumeroIdLegalDTO numeroIdLegalDTO = NumeroIdLegalDTO.builder()
                .smaIdNombre(String.valueOf(IdentificationTypeEnum.valueOfIdentificationInDian(
                        orderDTO.getThirdParty().getDocumentType()).getCodeDian()))
                .value(orderDTO.getThirdParty().getDocumentNumber())
                .build();

        if (orderDTO.getThirdParty().getDocumentType() == IdentificationTypeEnum.NIT.getIdentificationId()) {
            int verificationDigit = CalculationNitUtil.getVerificationDigit(thirdPartyDTO.getDocumentNumber());
            numeroIdLegalDTO.setSmaIdCodigo(String.valueOf(verificationDigit));
        }

        return numeroIdLegalDTO;
    }

    private @NonNull EsquemaRegistroCorporativoDTO getEsquemaRegistroCorporativo() {
        return EsquemaRegistroCorporativoDTO.builder()
                .id(IdDTO.builder().value(orderDTO.getPosResolution().getPrefix().toString()).build())
                .build();
    }
}

