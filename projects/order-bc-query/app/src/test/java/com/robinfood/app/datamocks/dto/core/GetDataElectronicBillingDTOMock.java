package com.robinfood.app.datamocks.dto.core;

import com.robinfood.core.dtos.DataElectronicBillingDTO;

public class GetDataElectronicBillingDTOMock {

    public static DataElectronicBillingDTO getDataDefault(){

        return DataElectronicBillingDTO.builder()
                .broadcastDateTime("2024-04-18T11:27:08.5472815-05:00")
                .cufe("cufe test")
                .documentType("document type test")
                .nitTransmitter("nit test")
                .number("number test")
                .prefix("prefix test")
                .qr("qr test")
                .build();
    }
}
