package com.robinfood.paymentmethodsbc.sample;

import com.robinfood.paymentmethodsbc.model.Country;

/**
 * Obtiene objetos de prueba para test unitarios
 * @author Hernán A. Ramírez O.
 */
public class CountrySample {

    public static Country getCountry() {
        return Country
                .builder()
                .id(1L)
                .name("Country sample")
                .build();
    }
}
