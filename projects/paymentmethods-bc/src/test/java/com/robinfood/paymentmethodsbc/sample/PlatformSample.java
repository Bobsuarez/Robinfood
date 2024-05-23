package com.robinfood.paymentmethodsbc.sample;

import com.robinfood.paymentmethodsbc.model.Platform;

/**
 * Obtiene objetos de prueba para test unitarios
 * @author Hernán A. Ramírez O.
 */
public class PlatformSample {

    public static Platform getPlatform() {
        return Platform
                .builder()
                .id(1L)
                .name("Platform sample")
                .description("Platform sample for unit test")
                .build();
    }
    public static Platform getValidatePlatform() {
        return Platform
            .builder()
            .id(6L)
            .name("Platform sample")
            .description("Platform sample for unit test")
            .build();
    }
}
