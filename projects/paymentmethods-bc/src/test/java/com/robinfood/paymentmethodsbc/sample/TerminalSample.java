package com.robinfood.paymentmethodsbc.sample;

import com.robinfood.paymentmethodsbc.model.Terminal;

/**
 * Obtiene objetos de prueba para test unitarios
 */
public class TerminalSample {

    public static Terminal getTerminal(){
        return Terminal
            .builder()
            .id(1L)
            .uuid("ABC")
            .countryId(1L)
            .storeId(1L)
            .status(true)
            .name("Terminal")
            .build();
    }
}
