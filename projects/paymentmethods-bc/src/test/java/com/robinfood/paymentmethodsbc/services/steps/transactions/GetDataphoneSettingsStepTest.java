package com.robinfood.paymentmethodsbc.services.steps.transactions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.SettingsDTO;
import com.robinfood.paymentmethodsbc.dto.steps.TransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.model.Terminal;
import com.robinfood.paymentmethodsbc.repositories.TerminalPaymentMethodSettingsCachedRepository;
import com.robinfood.paymentmethodsbc.sample.TerminalSample;
import com.robinfood.paymentmethodsbc.sample.TransactionSamples;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetDataphoneSettingsStepTest {

    @Mock
    private TerminalPaymentMethodSettingsCachedRepository settingsCachedRepository;

    @InjectMocks
    private GetDataphoneSettingsStep getDataphoneSettingsStep;

    @Test
    public void testInvokeWhenSettingsFoundShouldBeOk() {
        PaymentPipeDTO pipe = TransactionSamples.getTransactionCreatePipeDTO();

        Map<String, String> settings = new HashMap<>();
        settings.put("posReferenceId", "reference");

        when(
            settingsCachedRepository.findAllSettingsTerminalPaymentMethod(
                anyLong(),
                anyLong()
            )
        )
            .thenReturn(settings);

        assertAll(() -> getDataphoneSettingsStep.invoke(pipe));
    }

    @Test
    public void testInvokeWhenSettingsNotFoundShouldBeOk() {
        PaymentPipeDTO pipe = TransactionSamples.getTransactionCreatePipeDTO();
        pipe.setSettings(null);

        Map<String, String> settings = new HashMap<>();
        settings.put("posReferenceId", "reference");

        when(settingsCachedRepository.findAllSettingsTerminalPaymentMethod(anyLong(), anyLong()))
            .thenReturn(settings);

        assertAll(() -> getDataphoneSettingsStep.invoke(pipe));
    }

    @Test
    public void testInvokeWhenSettingsInstantiatedShouldBeOk() {
        PaymentPipeDTO pipe = TransactionSamples.getTransactionCreatePipeDTO();
        pipe.setSettings(new SettingsDTO());

        Map<String, String> settings = new HashMap<>();
        settings.put("posReferenceId", "reference");

        when(
            settingsCachedRepository.findAllSettingsTerminalPaymentMethod(
                anyLong(),
                anyLong()
            )
        )
            .thenReturn(settings);

        assertAll(() -> getDataphoneSettingsStep.invoke(pipe));
    }

    @Test
    public void testInvokeForUpdateStatusWhenTerminalIsNullShouldBeOk() {
        TransactionStatusPipeDTO pipe = TransactionSamples.getTransactionStatusPipeDTO();
        pipe.getTransactionDetail().setTerminal(null);

        assertAll(() -> getDataphoneSettingsStep.invoke(pipe));
    }

    @Test
    public void testInvokeForUpdateStatusWhenSetDataShouldBeOk() {
        TransactionStatusPipeDTO pipe = TransactionSamples.getTransactionStatusPipeDTO();
        pipe.getTransactionDetail().setTerminal(TerminalSample.getTerminal());
        pipe.setSettings(new SettingsDTO());

        Map<String, String> settings = new HashMap<>();
        settings.put("posReferenceId", "reference");

        when(
            settingsCachedRepository.findAllSettingsTerminalPaymentMethod(
                anyLong(),
                anyLong()
            )
        )
            .thenReturn(settings);

        assertAll(() -> getDataphoneSettingsStep.invoke(pipe));
    }

    @Test
    public void testInvokeTerminalIdNotFoundShouldBeError() {
        PaymentPipeDTO pipe = TransactionSamples.getTransactionCreatePipeDTO();
        pipe.setTerminal(Terminal.builder().build());

        assertThrows(
            PaymentStepException.class,
            () -> getDataphoneSettingsStep.invoke(pipe)
        );
    }

    @Test
    public void testInvokeTerminalNotFoundShouldBeError() {
        PaymentPipeDTO pipe = TransactionSamples.getTransactionCreatePipeDTO();
        pipe.setTerminal(null);

        assertThrows(
            PaymentStepException.class,
            () -> getDataphoneSettingsStep.invoke(pipe)
        );
    }

    @Test
    public void testInvokeNoImplementedPipeShouldBeError() {
        assertThrows(
            PaymentStepException.class,
            () -> getDataphoneSettingsStep.invoke(new Object())
        );
    }

}