package com.robinfood.paymentmethodsbc.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundRuntimeException;
import com.robinfood.paymentmethodsbc.exceptions.ResourceNotFoundException;
import com.robinfood.paymentmethodsbc.repositories.PaymentMethodStoreFlowChannelsCachedRepository;
import com.robinfood.paymentmethodsbc.repositories.TerminalPaymentMethodSettingsCachedRepository;
import com.robinfood.paymentmethodsbc.repositories.TerminalsRepository;
import com.robinfood.paymentmethodsbc.sample.PaymentMethodStoreFlowChannelSample;
import com.robinfood.paymentmethodsbc.sample.TerminalPaymentMethodSettingSample;
import com.robinfood.paymentmethodsbc.sample.TerminalSample;
import com.robinfood.paymentmethodsbc.services.impl.GeneralPaymentMethodServiceImpl;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GeneralPaymentMethodServiceTest {
    @Mock
    private PaymentMethodStoreFlowChannelsCachedRepository paymentMethodStoreFlowChannelsCachedRepository;

    @Mock
    private TerminalPaymentMethodSettingsCachedRepository terminalPaymentMethodSettingsCachedRepository;

    @Mock
    private TerminalsRepository terminalsRepository;

    @InjectMocks
    private GeneralPaymentMethodServiceImpl generalPaymentMethodService;

    @Test
    public void testFetPaymentMethodsByStoreChannelFlowAndTerminalWithTerminalOk() {
        when(
            terminalsRepository.findByUuidAndStatusAndDeletedAt(
                anyString(),
                anyBoolean(),
                any()
            )
        )
            .thenReturn(Optional.of(TerminalSample.getTerminal()));

        when(
                paymentMethodStoreFlowChannelsCachedRepository.findByStoreIdAndChannelIdAndFlowIdAndStatus(
                    anyLong(),
                    anyLong(),
                    anyLong(),
                    anyBoolean()
                )
            )
            .thenReturn(PaymentMethodStoreFlowChannelSample.getPaymentMethodStoreFlowChannelList());

        when(
            terminalPaymentMethodSettingsCachedRepository.findByTerminalIdAndPaymentGatewayIdAndTerminalParameterVisible(
                anyLong(),
                anyLong(),
                anyBoolean()
            )
        ).thenReturn(TerminalPaymentMethodSettingSample.getTerminalPaymentMethodSettingList());

        assertAll(
            () ->
                generalPaymentMethodService.getPaymentMethodsByStoreChannelFlowAndTerminal(1L, 1L, 1L, "abc")
        );

        verify(terminalsRepository, times(1))
            .findByUuidAndStatusAndDeletedAt(
                anyString(),
                anyBoolean(),
                any()
            );

        verify(paymentMethodStoreFlowChannelsCachedRepository, times(1))
            .findByStoreIdAndChannelIdAndFlowIdAndStatus(
                anyLong(),
                anyLong(),
                anyLong(),
                anyBoolean()
            );

        verify(terminalPaymentMethodSettingsCachedRepository,
            times(PaymentMethodStoreFlowChannelSample.getPaymentMethodStoreFlowChannelList().size()))
            .findByTerminalIdAndPaymentGatewayIdAndTerminalParameterVisible(
                anyLong(),
                anyLong(),
                anyBoolean()
            );
    }

    @Test
    public void testFetPaymentMethodsByStoreChannelFlowAndTerminalWithoutTerminalOk() {

        assertThrows(
            ResourceNotFoundException.class,
            () -> generalPaymentMethodService.getPaymentMethodsByStoreChannelFlowAndTerminal(
                1L, 1L, 1L, null
            ),
            "ResourceNotFoundException"
        );

        verify(terminalsRepository, times(0))
            .findByUuidAndStatusAndDeletedAt(anyString(), anyBoolean(), any());

        verify(terminalPaymentMethodSettingsCachedRepository, times(0))
            .findByTerminalIdAndPaymentGatewayIdAndTerminalParameterVisible(anyLong(), anyLong(), anyBoolean());
    }

    @Test
    public void testFetPaymentMethodsByStoreChannelFlowAndTerminalWhenTerminalNotFound() {

        assertThrows(
            ResourceNotFoundException.class,
            () ->
                generalPaymentMethodService.getPaymentMethodsByStoreChannelFlowAndTerminal(1L, 1L, 1L, "abc"),
            "ResourceNotFoundException"
        );

        verify(terminalPaymentMethodSettingsCachedRepository, times(0))
            .findByTerminalIdAndPaymentGatewayIdAndTerminalParameterVisible(anyLong(), anyLong(), anyBoolean());
    }

    @Test
    public void testFetPaymentMethodsByStoreChannelFlowAndTerminalWhenDataNotFound() {

        assertThrows(
            ResourceNotFoundException.class,
            () -> generalPaymentMethodService.getPaymentMethodsByStoreChannelFlowAndTerminal(
                1L, 1L, 1L, "abc"),
            "ResourceNotFoundException"
        );
    }

    @Test
    public void testGetPaymentMethodsByStoreChannelFlowAndTerminalWithTerminalOkThrowsException() {
        when(terminalsRepository.findByUuidAndStatusAndDeletedAt(anyString(), anyBoolean(), any()))
            .thenReturn(Optional.empty());

        assertThrows(
            ResourceNotFoundException.class,
            () -> generalPaymentMethodService.getPaymentMethodsByStoreChannelFlowAndTerminal(
                1L, 1L, 1L, "abc"),
            "ResourceNotFoundException"
        );

        verify(terminalsRepository, times(1))
            .findByUuidAndStatusAndDeletedAt(
                anyString(),
                anyBoolean(),
                any()
            );
    }

    @Test
    public void testGetPaymentMethodsByStoreChannelFlowAndTerminalWithTerminalOkThrowsExceptionWhenResultIsEmpty() {
        when(terminalsRepository.findByUuidAndStatusAndDeletedAt(anyString(), anyBoolean(), any()))
            .thenReturn(Optional.of(TerminalSample.getTerminal()));

        when(paymentMethodStoreFlowChannelsCachedRepository.findByStoreIdAndChannelIdAndFlowIdAndStatus(
                anyLong(), anyLong(), anyLong(), anyBoolean())
        ).thenReturn(List.of());

        assertThrows(
            ResourceNotFoundException.class,
            () -> generalPaymentMethodService.getPaymentMethodsByStoreChannelFlowAndTerminal(
                1L, 1L, 1L, "abc"),
            "ResourceNotFoundException"
        );

        verify(terminalsRepository, times(1))
            .findByUuidAndStatusAndDeletedAt(
                anyString(),
                anyBoolean(),
                any()
            );
    }
}
