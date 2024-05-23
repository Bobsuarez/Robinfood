package com.robinfood.decorator.terceros;

import com.robinfood.dtos.request.orderbill.OrderDTO;
import com.robinfood.dtos.request.orderbill.ThirdPartyDTO;
import com.robinfood.dtos.request.simba.terceros.IdAdicionalDTO;
import com.robinfood.dtos.request.simba.terceros.TerceroClienteContableDTO;
import com.robinfood.dtos.request.simba.terceros.TercerosDTO;
import com.robinfood.dtos.request.simba.terceros.tercero.IdTerceroDTO;
import com.robinfood.dtos.request.simba.terceros.tercero.NombreTerceroDTO;
import com.robinfood.dtos.request.simba.terceros.tercero.TerceroDTO;
import com.robinfood.enums.IdentificationTypeEnum;
import com.robinfood.util.CalculationNitUtil;
import lombok.NonNull;

import java.util.List;

import static com.robinfood.constants.simba.TercerosConstants.ID_ADICIONAL_TERCERO_CLIENTE;

public class TerceroClienteDecorator extends AbstractTerceroProveedor {

    private final OrderDTO orderDTO;

    public TerceroClienteDecorator(ITerceros terceroProveedor, OrderDTO orderDTO) {
        super(terceroProveedor);
        this.orderDTO = orderDTO;
    }

    @Override
    public void decorarTerceroProveedor(TercerosDTO tercerosDTOO) {
        super.decorarTerceroProveedor(tercerosDTOO);
        buildUbicacionFisicaTercerosClienteDecorator(tercerosDTOO);
    }

    private void buildUbicacionFisicaTercerosClienteDecorator(TercerosDTO tercerosDTO) {

        TerceroClienteContableDTO terceroClienteContableDTO = new TerceroClienteContableDTO();
        TerceroDTO terceroDTO = new TerceroDTO();
        terceroDTO.setIdTercero(List.of(getIdTerceroDTO()));
        terceroDTO.setNombreTercero(List.of(getNombreTerceroDTO()));

        terceroClienteContableDTO.setIdAdicional(List.of(getIdAdicionalDTO()));
        terceroClienteContableDTO.setTercero(terceroDTO);

        tercerosDTO.setTerceroClienteContable(terceroClienteContableDTO);
    }

    private @NonNull IdAdicionalDTO getIdAdicionalDTO() {
        return IdAdicionalDTO.builder()
                .value(ID_ADICIONAL_TERCERO_CLIENTE)
                .build();
    }

    private @NonNull NombreTerceroDTO getNombreTerceroDTO() {
        return NombreTerceroDTO.builder()
                .value(orderDTO.getThirdParty().getFullName())
                .build();
    }

    private @NonNull IdTerceroDTO getIdTerceroDTO() {

        IdTerceroDTO idTerceroDTO = new IdTerceroDTO();
        ThirdPartyDTO thirdPartyDTO = orderDTO.getThirdParty();

        idTerceroDTO.setSmaIdNombre(String.valueOf(IdentificationTypeEnum
                .valueOfIdentificationInDian(thirdPartyDTO.getDocumentType()).getCodeDian()));

        if (orderDTO.getThirdParty().getDocumentType() == IdentificationTypeEnum.NIT.getIdentificationId()) {
            int verificationDigit = CalculationNitUtil.getVerificationDigit(thirdPartyDTO.getDocumentNumber());
            idTerceroDTO.setSmaIdCodigo(String.valueOf(verificationDigit));
        }

        idTerceroDTO.setValue(thirdPartyDTO.getDocumentNumber());

        return idTerceroDTO;
    }
}

