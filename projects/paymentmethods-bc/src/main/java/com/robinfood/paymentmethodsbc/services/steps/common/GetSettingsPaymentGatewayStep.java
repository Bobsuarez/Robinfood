package com.robinfood.paymentmethodsbc.services.steps.common;

import com.robinfood.paymentmethodsbc.annotations.BasicLog;
import com.robinfood.paymentmethodsbc.constants.AppConstants;
import com.robinfood.paymentmethodsbc.dto.steps.JmsTransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.RefundPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.SettingsDTO;
import com.robinfood.paymentmethodsbc.dto.steps.TransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.enums.StepType;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.repositories.PaymentGatewaySettingsCachedRepository;
import com.robinfood.paymentmethodsbc.services.steps.StepActions;
import com.robinfood.paymentmethodsbc.utils.SafeCastUtils;
import com.robinfood.paymentmethodsbc.utils.TransactionLogger;
import com.robinfood.paymentmethodsbc.utils.Utilities;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component
public class GetSettingsPaymentGatewayStep implements StepActions {
    private final PaymentGatewaySettingsCachedRepository paymentGatewaySettingsCachedRepository;

    public GetSettingsPaymentGatewayStep(
        PaymentGatewaySettingsCachedRepository paymentGatewaySettingsCachedRepository
    ) {
        this.paymentGatewaySettingsCachedRepository = paymentGatewaySettingsCachedRepository;
    }

    @Override
    @BasicLog
    public void invoke(Object pipe) throws PaymentStepException, EntityNotFoundException {
        if (pipe instanceof RefundPipeDTO) {
            invokeForRefundPipeDTO((RefundPipeDTO) pipe);

        } else if (pipe instanceof PaymentPipeDTO) {
            invokeForTransactionPipeDTO((PaymentPipeDTO) pipe);

        } else if (pipe instanceof TransactionStatusPipeDTO) {
            invokeForTransactionStatusPipeDTO((TransactionStatusPipeDTO) pipe);

        }else if (pipe instanceof JmsTransactionStatusPipeDTO) {
            invokeForJmsTransactionStatusPipeDTO((JmsTransactionStatusPipeDTO) pipe);

        }else {
            throw new PaymentStepException(
                StepType.GET_PAYMENT_GATEWAY_CREDIT_CARD,
                String.format(
                    "No se ha realizado ninguna acción en %s",
                    getClass().getSimpleName()
                )
            );
        }
    }

    private void invokeForTransactionPipeDTO(final PaymentPipeDTO paymentDto) {

        TransactionLogger.invoke(paymentDto);
        Map<String, String> paymentGatewaySettings = getPaymentGatewaySettings(
            paymentDto.getTransactionRequestDTO().getCountryId(),
            paymentDto.getPaymentGateway().getId()
        );

        Map<String, String> orchSettings = getOrchestratorSettings(
            paymentDto.getTransactionRequestDTO().getCountryId(),
            paymentGatewaySettings
        );

        SettingsDTO settings = paymentDto.getSettings();
        if (Objects.isNull(settings)) {
            settings = new SettingsDTO();
        }

        settings.setGatewayConfig(paymentGatewaySettings);
        settings.setOrchConfig(orchSettings);
        paymentDto.setSettings(settings);
        TransactionLogger.clear();
    }

    private void invokeForRefundPipeDTO(final RefundPipeDTO refundPipeDTO) {
        Transaction transaction = refundPipeDTO.getOriginalTransaction();

        Map<String, String> paymentGatewaySettings = getPaymentGatewaySettings(
            transaction.getCountry().getId(),
            transaction.getPaymentGateway().getId()
        );

        Map<String, String> orchSettings = getOrchestratorSettings(
            transaction.getCountry().getId(),
            paymentGatewaySettings
        );

        SettingsDTO settings = refundPipeDTO.getSettings();
        if (Objects.isNull(settings)) {
            settings = new SettingsDTO();
        }

        settings.setGatewayConfig(paymentGatewaySettings);
        settings.setOrchConfig(orchSettings);

        String processRefundSetting = paymentGatewaySettings.get(AppConstants.PROCESS_REFUND_SETTINGS_NAME);

        refundPipeDTO.setBciProcessRefund(
            Objects.isNull(processRefundSetting) || SafeCastUtils.toBoolean(processRefundSetting)
        );


        refundPipeDTO.setSettings(settings);
    }

    private void invokeForTransactionStatusPipeDTO(final TransactionStatusPipeDTO transactionStatusPipeDTO) {
        Transaction transaction = transactionStatusPipeDTO.getTransaction();
        TransactionLogger.invoke(transaction);
        Map<String, String> paymentGatewaySettings = getPaymentGatewaySettings(
            transaction.getCountry().getId(),
            transaction.getPaymentGateway().getId()
        );

        Map<String, String> orchSettings = getOrchestratorSettings(
            transaction.getCountry().getId(),
            paymentGatewaySettings
        );

        SettingsDTO settings = transactionStatusPipeDTO.getSettings();
        if (Objects.isNull(settings)) {
            settings = new SettingsDTO();
        }

        settings.setGatewayConfig(paymentGatewaySettings);
        settings.setOrchConfig(orchSettings);
        transactionStatusPipeDTO.setSettings(settings);
        TransactionLogger.clear();
    }

    private void invokeForJmsTransactionStatusPipeDTO(final JmsTransactionStatusPipeDTO jmstransactionStatusPipeDTO) {
        Transaction transaction = jmstransactionStatusPipeDTO.getTransaction();
        TransactionLogger.invoke(transaction);
        Map<String, String> paymentGatewaySettings = getPaymentGatewaySettings(
            transaction.getCountry().getId(),
            transaction.getPaymentGateway().getId()
        );

        Map<String, String> orchSettings = getOrchestratorSettings(
            transaction.getCountry().getId(),
            paymentGatewaySettings
        );

        SettingsDTO settings = jmstransactionStatusPipeDTO.getSettings();
        if (Objects.isNull(settings)) {
            settings = new SettingsDTO();
        }

        settings.setGatewayConfig(paymentGatewaySettings);
        settings.setOrchConfig(orchSettings);
        jmstransactionStatusPipeDTO.setSettings(settings);
        TransactionLogger.clear();
    }

    private Map<String, String> getPaymentGatewaySettings(Long countryId, Long paymentGatewayId) {
        return paymentGatewaySettingsCachedRepository.getSettingsPaymentGateway(
            false,
            countryId,
            paymentGatewayId
        );
    }

    private Map<String, String> getOrchestratorSettings(Long countryId, Map<String, String> paymentGatewaySettings) {
        return paymentGatewaySettingsCachedRepository.getSettingsOrchestratorPaymentGateway(
            countryId,
            Utilities.stringToLong(paymentGatewaySettings.get(AppConstants.ORCHESTRATOR_ID_SETTINGS_NAME))
        );
    }
}
