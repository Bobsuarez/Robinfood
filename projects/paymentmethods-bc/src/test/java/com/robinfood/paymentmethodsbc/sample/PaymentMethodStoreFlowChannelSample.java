package com.robinfood.paymentmethodsbc.sample;

import com.robinfood.paymentmethodsbc.model.PaymentMethodStoreFlowChannel;
import java.util.List;

/**
 * Obtiene objetos de prueba para test unitarios
 */
public class PaymentMethodStoreFlowChannelSample {

    public static List<PaymentMethodStoreFlowChannel> getPaymentMethodStoreFlowChannelList(){
        return List.of(
            getPaymentMethodStoreFlowChannel(),
            getPaymentMethodStoreFlowChannel()
        );
    }

    public static PaymentMethodStoreFlowChannel getPaymentMethodStoreFlowChannel(){
        return PaymentMethodStoreFlowChannel
            .builder()
            .id(1L)
            .generalPaymentMethod(GeneralPaymentMethodSample.getGeneralPaymentMethod())
            .storeId(1L)
            .channelId(1L)
            .flowId(1L)
            .status(true)
            .build();
    }
}
