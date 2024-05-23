package com.robinfood.orderorlocalserver.mocks.dtos;

import com.robinfood.orderorlocalserver.dtos.orderdetail.DataElectronicBillingDTO;
import com.robinfood.orderorlocalserver.dtos.orderdetail.ElectronicBillDTO;
import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderThirdPartyDTO;

public class ElectronicBillDTOMock {

    public static ElectronicBillDTO getDataDefault(){

        OrderThirdPartyDTO orderThirdPartyDTO =OrderThirdPartyDTO.builder()
                .documentNumber("123456")
                .documentType(1L)
                .email("test@gmail.com")
                .fullName("test fullName")
                .phone("3113456789").build();

        DataElectronicBillingDTO dataElectronicBillingDTO = DataElectronicBillingDTO.builder()
                .broadcastDateTime("2024-01-01 00:00:00")
                .cufe("Test Cufe")
                .documentType("Test documentType")
                .nitTransmitter("Test Nit")
                .number("Test Number")
                .prefix("Test Prefix")
                .qr("Test QR")
                .build();

        return ElectronicBillDTO.builder()
                .orderElectronicBilling(dataElectronicBillingDTO)
                .orderThirdParty(orderThirdPartyDTO)
                .build();
    }
}