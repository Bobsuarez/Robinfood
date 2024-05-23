package com.robinfood.paymentmethodsbc.sample;

import com.robinfood.paymentmethodsbc.model.Entity;

/**
 * Obtiene objetos de prueba para test unitarios
 * @author Hernán A. Ramírez O.
 */
public class EntitySample {

    public static Entity getEntity() {
        return Entity
                .builder()
                .id(1L)
                .name("Entity sample")
                .description("Entity description sample")
                .build();
    }
}
