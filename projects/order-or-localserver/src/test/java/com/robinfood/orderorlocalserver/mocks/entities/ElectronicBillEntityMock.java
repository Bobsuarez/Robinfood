package com.robinfood.orderorlocalserver.mocks.entities;

import com.robinfood.orderorlocalserver.entities.orderdetail.DataElectronicBillingEntity;
import com.robinfood.orderorlocalserver.entities.orderdetail.ElectronicBillEntity;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderThirdPartyEntity;

public class ElectronicBillEntityMock {

    public static ElectronicBillEntity getDataDefault(){

        OrderThirdPartyEntity orderThirdPartyEntity = OrderThirdPartyEntity.builder()
                .documentNumber("123456")
                .documentType(1L)
                .email("test@gmail.com")
                .fullName("test fullName")
                .phone("3113456789").build();

        DataElectronicBillingEntity dataElectronicBillingEntity = DataElectronicBillingEntity.builder()
                .FechaYHoraEmision("2024-01-01 00:00:00")
                .CUDE("Test Cufe")
                .TipoDocumento("Test documentType")
                .NitEmisor("Test Nit")
                .Numero("Test Number")
                .Prefijo("Test Prefix")
                .QR("Test QR")
                .build();

        return ElectronicBillEntity.builder()
                .orderElectronicBilling(dataElectronicBillingEntity)
                .orderThirdParty(orderThirdPartyEntity)
                .build();
    }
}
