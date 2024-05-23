package com.robinfood.paymentmethodsbc.services.steps.common;

import com.robinfood.paymentmethodsbc.constants.AppConstants;
import com.robinfood.paymentmethodsbc.dto.steps.JmsTransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.RefundPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.TransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.repositories.PaymentGatewaySettingsCachedRepository;
import com.robinfood.paymentmethodsbc.sample.TransactionSamples;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetSettingsPaymentGatewayStepTest {

    @Mock
    private PaymentGatewaySettingsCachedRepository paymentGatewaySettingsCachedRepository;

    @InjectMocks
    private GetSettingsPaymentGatewayStep getSettingsPaymentGatewayStep;


    @Test
    void testInvokePaymentPipeDTOShouldBeOk() {
        PaymentPipeDTO pipe = TransactionSamples.getTransactionCreatePipeDTO();

        when(
            paymentGatewaySettingsCachedRepository.getSettingsPaymentGateway(
                anyBoolean(),
                anyLong(),
                anyLong()
            )
        )
            .thenReturn(new HashMap<>());

        when(
            paymentGatewaySettingsCachedRepository.getSettingsOrchestratorPaymentGateway(
                anyLong(),
                any()
            )
        )
            .thenReturn(new HashMap<>());

        assertAll(() -> getSettingsPaymentGatewayStep.invoke(pipe));
    }

    @Test
    void testInvokePaymentPipeDTOWithSettingsNullShouldBeOk() {
        PaymentPipeDTO pipe = TransactionSamples.getTransactionCreatePipeDTO();
        pipe.setSettings(null);

        when(
            paymentGatewaySettingsCachedRepository.getSettingsPaymentGateway(
                anyBoolean(),
                anyLong(),
                anyLong()
            )
        )
            .thenReturn(new HashMap<>());

        when(
            paymentGatewaySettingsCachedRepository.getSettingsOrchestratorPaymentGateway(
                anyLong(),
                any()
            )
        )
            .thenReturn(new HashMap<>());

        assertAll(() -> getSettingsPaymentGatewayStep.invoke(pipe));
    }

    @Test
    void testInvokeForRefundPipeDTOShouldBeOk() {
        RefundPipeDTO pipe = TransactionSamples.getRefundPipeDTO();

        Map<String, String> settings = new HashMap<>();
        settings.put(AppConstants.PROCESS_REFUND_SETTINGS_NAME, "false");

        when(
            paymentGatewaySettingsCachedRepository.getSettingsPaymentGateway(
                anyBoolean(),
                anyLong(),
                anyLong()
            )
        )
            .thenReturn(settings);

        when(
            paymentGatewaySettingsCachedRepository.getSettingsOrchestratorPaymentGateway(
                anyLong(),
                any()
            )
        )
            .thenReturn(new HashMap<>());

        assertAll(() -> getSettingsPaymentGatewayStep.invoke(pipe));
    }

    @Test
    void testInvokeForRefundPipeDTOShouldBeOkWhenSettingBciProcessTrue() {
        RefundPipeDTO pipe = TransactionSamples.getRefundPipeDTO();

        Map<String, String> settings = new HashMap<>();
        settings.put(AppConstants.PROCESS_REFUND_SETTINGS_NAME, "true");

        when(
            paymentGatewaySettingsCachedRepository.getSettingsPaymentGateway(
                anyBoolean(),
                anyLong(),
                anyLong()
            )
        )
            .thenReturn(settings);

        when(
            paymentGatewaySettingsCachedRepository.getSettingsOrchestratorPaymentGateway(
                anyLong(),
                any()
            )
        )
            .thenReturn(new HashMap<>());

        assertAll(() -> getSettingsPaymentGatewayStep.invoke(pipe));
    }

    @Test
    void testInvokeForRefundPipeDTOWithSettingsNullShouldBeOk() {
        RefundPipeDTO pipe = TransactionSamples.getRefundPipeDTO();
        pipe.setSettings(null);

        when(
            paymentGatewaySettingsCachedRepository.getSettingsPaymentGateway(
                anyBoolean(),
                anyLong(),
                anyLong()
            )
        )
            .thenReturn(new HashMap<>());

        when(
            paymentGatewaySettingsCachedRepository.getSettingsOrchestratorPaymentGateway(
                anyLong(),
                any()
            )
        )
            .thenReturn(new HashMap<>());

        assertAll(() -> getSettingsPaymentGatewayStep.invoke(pipe));
    }

    @Test
    void testInvokeTransactionStatusPipeDTOShouldBeOk() {
        TransactionStatusPipeDTO pipe = TransactionSamples.getTransactionStatusPipeDTO();

        when(
            paymentGatewaySettingsCachedRepository.getSettingsPaymentGateway(
                anyBoolean(),
                anyLong(),
                anyLong()
            )
        )
            .thenReturn(new HashMap<>());

        when(
            paymentGatewaySettingsCachedRepository.getSettingsOrchestratorPaymentGateway(
                anyLong(),
                any()
            )
        )
            .thenReturn(new HashMap<>());

        assertAll(() -> getSettingsPaymentGatewayStep.invoke(pipe));
    }

    @Test
    void testInvokeTransactionStatusPipeDTOWithSettingsNullShouldBeOk() {
        TransactionStatusPipeDTO pipe = TransactionSamples.getTransactionStatusPipeDTO();
        pipe.setSettings(null);

        when(
            paymentGatewaySettingsCachedRepository.getSettingsPaymentGateway(
                anyBoolean(),
                anyLong(),
                anyLong()
            )
        )
            .thenReturn(new HashMap<>());

        when(
            paymentGatewaySettingsCachedRepository.getSettingsOrchestratorPaymentGateway(
                anyLong(),
                any()
            )
        )
            .thenReturn(new HashMap<>());

        assertAll(() -> getSettingsPaymentGatewayStep.invoke(pipe));
    }

    @Test
    void testInvokeJmsTransactionStatusPipeDTOShouldBeOk() {
        JmsTransactionStatusPipeDTO pipe = TransactionSamples.getJmsTransactionStatusPipeDTOWhitSettings();

        when(
            paymentGatewaySettingsCachedRepository.getSettingsPaymentGateway(
                anyBoolean(),
                anyLong(),
                anyLong()
            )
        )
            .thenReturn(new HashMap<>());

        when(
            paymentGatewaySettingsCachedRepository.getSettingsOrchestratorPaymentGateway(
                anyLong(),
                any()
            )
        )
            .thenReturn(new HashMap<>());

        assertAll(() -> getSettingsPaymentGatewayStep.invoke(pipe));
    }

    @Test
    void testInvokeJmsTransactionStatusPipeDTOWhitSettingsNullShouldBeOk() {
        JmsTransactionStatusPipeDTO pipe = TransactionSamples.getJmsTransactionStatusPipeDTOWhitSettings();
        pipe.setSettings(null);
        when(
            paymentGatewaySettingsCachedRepository.getSettingsPaymentGateway(
                anyBoolean(),
                anyLong(),
                anyLong()
            )
        )
            .thenReturn(new HashMap<>());

        when(
            paymentGatewaySettingsCachedRepository.getSettingsOrchestratorPaymentGateway(
                anyLong(),
                any()
            )
        )
            .thenReturn(new HashMap<>());

        assertAll(() -> getSettingsPaymentGatewayStep.invoke(pipe));
    }

    @Test
    void testInvokeNoImplementedPipeShouldBeError() {
        assertThrows(
            PaymentStepException.class,
            () -> getSettingsPaymentGatewayStep.invoke(new Object())
        );
    }
}