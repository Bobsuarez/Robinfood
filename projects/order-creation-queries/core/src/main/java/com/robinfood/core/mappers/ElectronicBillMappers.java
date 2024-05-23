package com.robinfood.core.mappers;

import com.robinfood.core.dtos.DataElectronicBillingDTO;
import com.robinfood.core.dtos.ElectronicBillDTO;
import com.robinfood.core.dtos.OrderThirdPartyDTO;
import com.robinfood.core.entities.DataElectronicBillingEntity;
import com.robinfood.core.entities.ElectronicBillEntity;
import com.robinfood.core.entities.OrderThirdPartyEntity;

import java.util.Objects;

public final class ElectronicBillMappers {

    private ElectronicBillMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static ElectronicBillDTO toElectronicBillDTO(ElectronicBillEntity electronicBillEntity){
        if (Objects.isNull(electronicBillEntity)) {
            return null;
        }

        DataElectronicBillingDTO dataElectronicBillingDTO = toDataElectronicBillingDTO(
                electronicBillEntity.getOrderElectronicBilling());

        OrderThirdPartyDTO orderThirdPartyDTO = toOrderThirdPartyDTO(
                electronicBillEntity.getOrderThirdParty());

        return ElectronicBillDTO.builder()
                .orderElectronicBilling(dataElectronicBillingDTO)
                .orderThirdParty(orderThirdPartyDTO)
                .build();
    }

    private static OrderThirdPartyDTO toOrderThirdPartyDTO(OrderThirdPartyEntity orderThirdPartyEntity){
        if (Objects.isNull(orderThirdPartyEntity)) {
            return null;
        }

        return OrderThirdPartyDTO.builder()
                .documentNumber(orderThirdPartyEntity.getDocumentNumber())
                .documentType(orderThirdPartyEntity.getDocumentType())
                .email(orderThirdPartyEntity.getEmail())
                .fullName(orderThirdPartyEntity.getFullName())
                .phone(orderThirdPartyEntity.getPhone())
                .build();
    }

    private static DataElectronicBillingDTO toDataElectronicBillingDTO(
            DataElectronicBillingEntity dataElectronicBillingEntity
    ){
        if (Objects.isNull(dataElectronicBillingEntity)) {
            return null;
        }

        return DataElectronicBillingDTO.builder()
                .broadcastDateTime(dataElectronicBillingEntity.getFechaYHoraEmision())
                .cufe(dataElectronicBillingEntity.getCUDE())
                .documentType(dataElectronicBillingEntity.getTipoDocumento())
                .nitTransmitter(dataElectronicBillingEntity.getNitEmisor())
                .number(dataElectronicBillingEntity.getNumero())
                .prefix(dataElectronicBillingEntity.getPrefijo())
                .qr(dataElectronicBillingEntity.getQR())
                .build();
    }
}
