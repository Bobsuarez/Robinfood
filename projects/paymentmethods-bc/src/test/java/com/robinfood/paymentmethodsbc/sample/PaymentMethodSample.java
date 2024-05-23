package com.robinfood.paymentmethodsbc.sample;

import com.robinfood.paymentmethodsbc.model.PaymentMethod;
import com.robinfood.paymentmethodsbc.model.PaymentMethodChannel;
import com.robinfood.paymentmethodsbc.model.PaymentMethodChannelStore;
import java.time.LocalDateTime;

public final class PaymentMethodSample {

    private PaymentMethodSample() {}

    public static PaymentMethodChannelStore getPaymentMethodChannelStore(
        Long paymentMethodId,
        Long channelId
    ) {
        return PaymentMethodChannelStore
            .builder()
            .id(1L)
            .storeId(1L)
            .flowId(1L)
            .paymentMethod(getPaymentMethod(paymentMethodId))
            .channelId(channelId)
            .originId(1L)
            .status(true)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .deletedAt(null)
            .build();
    }

    public static PaymentMethodChannel getPaymentMethodChannel(
        Long paymentMethodId,
        Long channelId,
        String image
    ) {
        return PaymentMethodChannel
            .builder()
            .id(1L)
            .paymentMethod(getPaymentMethod(paymentMethodId))
            .channelId(channelId)
            .imageUrl(image)
            .muyPaymentMethodId(1L)
            .originId(1L)
            .status(true)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .deletedAt(null)
            .build();
    }

    public static PaymentMethod getPaymentMethod(Long paymentMethodId) {
        return PaymentMethod
            .builder()
            .id(paymentMethodId)
            .name("Efectivo")
            .slugName("cash")
            .imageUrl("/defaul.png")
            .status(true)
            .platform(PlatformSample.getPlatform())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .deletedAt(null)
            .build();
    }
}
