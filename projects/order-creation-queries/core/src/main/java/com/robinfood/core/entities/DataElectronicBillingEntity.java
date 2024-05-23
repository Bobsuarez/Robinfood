package com.robinfood.core.entities;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DataElectronicBillingEntity {

    private String FechaYHoraEmision;

    private String CUDE;

    private String TipoDocumento;

    private String NitEmisor;

    private String Numero;

    private String Prefijo;

    private String QR;
}
