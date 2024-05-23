package com.robinfood.paymentmethodsbc.sample;

import com.robinfood.paymentmethodsbc.model.GeneralPaymentMethod;
import java.time.LocalDateTime;

/**
 * Obtiene objetos de prueba para test unitarios
 */
public final class GeneralPaymentMethodSample {

    private GeneralPaymentMethodSample() {}

    public static GeneralPaymentMethod getGeneralPaymentMethod() {
        return GeneralPaymentMethod
            .builder()
            .id(1L)
            .uuid("uuid")
            .name("Efectivo")
            .slugName("cash")
            .imageUrl("/defaul.png")
            .status(true)
            .paymentGateway(PaymentGatewaySamples.getPaymentGateway())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
    }
}
