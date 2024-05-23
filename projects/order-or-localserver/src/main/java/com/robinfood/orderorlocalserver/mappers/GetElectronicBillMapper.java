package com.robinfood.orderorlocalserver.mappers;

import com.robinfood.orderorlocalserver.dtos.orderdetail.DataElectronicBillingDTO;
import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderThirdPartyDTO;
import com.robinfood.orderorlocalserver.entities.orderdetail.DataElectronicBillingEntity;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderThirdPartyEntity;

import java.util.Optional;

public final class GetElectronicBillMapper {

    private GetElectronicBillMapper(){

    }

    public static OrderThirdPartyDTO orderThirdPartyEntityToOrderThirdPartyDTO(
            OrderThirdPartyEntity orderThirdPartyEntity
    ){

        return Optional.ofNullable(orderThirdPartyEntity)
                .map(entity -> OrderThirdPartyDTO.builder()
                        .documentNumber(entity.getDocumentNumber())
                        .documentType(entity.getDocumentType())
                        .email(entity.getEmail())
                        .fullName(entity.getFullName())
                        .phone(entity.getPhone())
                        .build())
                .orElse(null);
    }

    public static DataElectronicBillingDTO dataElectronicBillingEntityToDataElectronicBillingDTO(
            DataElectronicBillingEntity dataElectronicBillingEntity
    ){

        return Optional.ofNullable(dataElectronicBillingEntity)
                .map(entity -> DataElectronicBillingDTO.builder()
                        .broadcastDateTime(entity.getFechaYHoraEmision())
                        .cufe(entity.getCUDE())
                        .documentType(entity.getTipoDocumento())
                        .nitTransmitter(entity.getNitEmisor())
                        .number(entity.getNumero())
                        .prefix(entity.getPrefijo())
                        .qr(entity.getQR())
                        .build())
                .orElse(null);
    }

}
