package com.robinfood.decorator.agregadocomercial;

import com.robinfood.dtos.request.orderbill.PaymentMethodDTO;
import com.robinfood.dtos.request.orderbill.TransactionRequestDTO;
import com.robinfood.dtos.request.simba.IdDTO;
import com.robinfood.dtos.request.simba.agregadocomercial.AgregadoComercialDTO;
import com.robinfood.dtos.request.simba.agregadocomercial.CodigoMedioDePagoDTO;
import com.robinfood.dtos.request.simba.agregadocomercial.MediosDePagoDTO;
import com.robinfood.dtos.request.simba.agregadocomercial.NotaInstruccionDTO;
import com.robinfood.enums.MetodoPagoEnum;
import lombok.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class AgregadoComercialConcrete implements IAgregadoComercial {

    private final TransactionRequestDTO transactionRequestDTO;

    public AgregadoComercialConcrete(TransactionRequestDTO transactionRequestDTO) {
        this.transactionRequestDTO = transactionRequestDTO;
    }

    @Override
    public void decorarAgregadoComercial(AgregadoComercialDTO agregadoComercialDTO) {
        adicionarPropiedadesAgregadoComercialPorDefecto(agregadoComercialDTO);
    }

    private void adicionarPropiedadesAgregadoComercialPorDefecto(AgregadoComercialDTO agregadoComercialDTO) {

        List<MediosDePagoDTO> mediosDePagoDTOList = new ArrayList<>();

        transactionRequestDTO.getPaymentMethods().forEach((PaymentMethodDTO paymethodDetail) -> {
            MediosDePagoDTO mediosDePagoDTO = new MediosDePagoDTO();
            mediosDePagoDTO.setId(getIdMetodoDePago(paymethodDetail.getId()));
            mediosDePagoDTO.setCodigoMedioDePago(getCodigoMedioDePago(paymethodDetail.getId()));
            mediosDePagoDTO.setNotaInstruccion(getNotaInstruccion(paymethodDetail.getId(), paymethodDetail.getValue()));
            mediosDePagoDTOList.add(mediosDePagoDTO);
        });
        agregadoComercialDTO.setMediosDePago(mediosDePagoDTOList);
    }

    private @NonNull IdDTO getIdMetodoDePago(Long paymentMethodId) {
        return IdDTO.builder()
                .value(MetodoPagoEnum.fromOrderPaymentMethodsId(paymentMethodId).getIdPago().toString())
                .build();
    }

    private @NonNull CodigoMedioDePagoDTO getCodigoMedioDePago(Long paymentMethodId) {
        return CodigoMedioDePagoDTO.builder()
                .value(String.valueOf(MetodoPagoEnum.fromOrderPaymentMethodsId(paymentMethodId).getIdCodDian()))
                .build();
    }

    private @NonNull List<NotaInstruccionDTO> getNotaInstruccion(Long paymentMethodId, BigDecimal valuePaymentMethod) {
        List<NotaInstruccionDTO> notaInstruccionDTOS = new ArrayList<>();

        NotaInstruccionDTO metodoNotaInstruccionDTO = new NotaInstruccionDTO();
        metodoNotaInstruccionDTO.setValue(MetodoPagoEnum.fromOrderPaymentMethodsId(paymentMethodId).getNameMetodo());
        notaInstruccionDTOS.add(metodoNotaInstruccionDTO);

        NotaInstruccionDTO valorNotaInstruccionDTO = new NotaInstruccionDTO();
        valorNotaInstruccionDTO.setValue(valuePaymentMethod.setScale(2, RoundingMode.HALF_UP).toString());
        notaInstruccionDTOS.add(valorNotaInstruccionDTO);

        return notaInstruccionDTOS;
    }

}
