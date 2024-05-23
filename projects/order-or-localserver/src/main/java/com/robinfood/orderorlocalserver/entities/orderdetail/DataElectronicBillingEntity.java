package com.robinfood.orderorlocalserver.entities.orderdetail;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DataElectronicBillingEntity {

    private String CUDE;

    private String FechaYHoraEmision;

    private String NitEmisor;

    private String Numero;

    private String Prefijo;

    private String QR;

    private String TipoDocumento;
}
