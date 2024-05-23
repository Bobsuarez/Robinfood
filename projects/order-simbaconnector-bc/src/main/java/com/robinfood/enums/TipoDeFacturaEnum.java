package com.robinfood.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoDeFacturaEnum {

    FACTURA("01"),
    NOTA_CREDITO("02"),
    NOTA_DEBITO("03"),
    POS_ELECTRONIC("20");

    private final String tipoDoucmeto;
}
