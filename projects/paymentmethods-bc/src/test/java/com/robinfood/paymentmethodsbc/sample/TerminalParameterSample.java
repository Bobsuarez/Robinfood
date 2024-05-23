package com.robinfood.paymentmethodsbc.sample;

import com.robinfood.paymentmethodsbc.model.TerminalParameter;

/**
 * Obtiene objetos de prueba para test unitarios
 */
public class TerminalParameterSample {

    public static TerminalParameter getTerminalParameter(){
        return TerminalParameter
            .builder()
            .id(1L)
            .key("Key")
            .name("Name")
            .type("Type")
            .build();
    }
}
