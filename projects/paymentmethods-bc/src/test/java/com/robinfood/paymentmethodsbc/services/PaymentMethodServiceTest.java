package com.robinfood.paymentmethodsbc.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import com.robinfood.paymentmethodsbc.repositories.PaymentMethodChannelStoresRepository;
import com.robinfood.paymentmethodsbc.repositories.PaymentMethodChannelsRepository;
import com.robinfood.paymentmethodsbc.sample.PaymentMethodSample;
import com.robinfood.paymentmethodsbc.services.impl.PaymentMethodServiceImpl;
import java.util.Arrays;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PaymentMethodServiceTest {
    @Mock
    private PaymentMethodChannelStoresRepository pmChannelStoresRepository;

    @Mock
    private PaymentMethodChannelsRepository pmChannelsRepository;

    @InjectMocks
    private PaymentMethodServiceImpl paymentMethodService;

    @Test
    public void test_getPaymentMethodsByStoreAndChannel_Ok()
        throws BaseException {
        when(
                pmChannelStoresRepository.findByStoreIdAndChannelIdAndOriginIdAndStatusAndDeletedAt(
                    anyLong(),
                    anyLong(),
                    anyLong(),
                    anyBoolean(),
                    any()
                )
            )
            .thenReturn(
                Arrays.asList(
                    PaymentMethodSample.getPaymentMethodChannelStore(1L, 1L),
                    PaymentMethodSample.getPaymentMethodChannelStore(2L, 1L)
                )
            );

        when(
                pmChannelsRepository.findByPaymentMethodIdAndChannelIdAndOriginIdAndStatusAndDeletedAt(
                    anyLong(),
                    anyLong(),
                    anyLong(),
                    anyBoolean(),
                    any()
                )
            )
            .thenReturn(
                Optional.of(
                    PaymentMethodSample.getPaymentMethodChannel(1L, 1L, "x.x")
                )
            );

        assertAll(
            () ->
                paymentMethodService.getPaymentMethodsByStoreAndChannelAndOrigin(1L, 1L, 1L)
        );
    }

    @Test
    public void test_getPaymentMethodsByStoreAndChannel_Ok_DefaultImage_when_null()
        throws BaseException {
        when(
                pmChannelStoresRepository.findByStoreIdAndChannelIdAndOriginIdAndStatusAndDeletedAt(
                    anyLong(),
                    anyLong(),
                    anyLong(),
                    anyBoolean(),
                    any()
                )
            )
            .thenReturn(
                Arrays.asList(
                    PaymentMethodSample.getPaymentMethodChannelStore(1L, 1L)
                )
            );

        when(
                pmChannelsRepository.findByPaymentMethodIdAndChannelIdAndOriginIdAndStatusAndDeletedAt(
                    anyLong(),
                    anyLong(),
                    anyLong(),
                    anyBoolean(),
                    any()
                )
            )
            .thenReturn(
                Optional.of(
                    PaymentMethodSample.getPaymentMethodChannel(1L, 1L, null)
                )
            );

        assertAll(
            () ->
                paymentMethodService.getPaymentMethodsByStoreAndChannelAndOrigin(1L, 1L, 1l)
        );
    }

    @Test
    public void test_getPaymentMethodsByStoreAndChannel_Ok_DefaultImage_when_blank()
        throws BaseException {
        when(
                pmChannelStoresRepository.findByStoreIdAndChannelIdAndOriginIdAndStatusAndDeletedAt(
                    anyLong(),
                    anyLong(),
                    anyLong(),
                    anyBoolean(),
                    any()
                )
            )
            .thenReturn(
                Arrays.asList(
                    PaymentMethodSample.getPaymentMethodChannelStore(1L, 1L)
                )
            );

        when(
                pmChannelsRepository.findByPaymentMethodIdAndChannelIdAndOriginIdAndStatusAndDeletedAt(
                    anyLong(),
                    anyLong(),
                    anyLong(),
                    anyBoolean(),
                    any()
                )
            )
            .thenReturn(
                Optional.of(
                    PaymentMethodSample.getPaymentMethodChannel(1L, 1L, "")
                )
            );

        assertAll(
            () ->
                paymentMethodService.getPaymentMethodsByStoreAndChannelAndOrigin(1L, 1L, 1L)
        );
    }

    @Test
    public void test_getPaymentMethodsByStoreAndChannel_Ok_Empty()
        throws BaseException {
        when(
                pmChannelStoresRepository.findByStoreIdAndChannelIdAndOriginIdAndStatusAndDeletedAt(
                    anyLong(),
                    anyLong(),
                    anyLong(),
                    anyBoolean(),
                    any()
                )
            )
            .thenReturn(
                Arrays.asList(
                    PaymentMethodSample.getPaymentMethodChannelStore(1L, 1L),
                    PaymentMethodSample.getPaymentMethodChannelStore(2L, 1L)
                )
            );

        when(
                pmChannelsRepository.findByPaymentMethodIdAndChannelIdAndOriginIdAndStatusAndDeletedAt(
                    anyLong(),
                    anyLong(),
                    anyLong(),
                    anyBoolean(),
                    any()
                )
            )
            .thenReturn(Optional.ofNullable(null));

        assertAll(
            () ->
                paymentMethodService.getPaymentMethodsByStoreAndChannelAndOrigin(1L, 1L, 1L)
        );
    }
}
