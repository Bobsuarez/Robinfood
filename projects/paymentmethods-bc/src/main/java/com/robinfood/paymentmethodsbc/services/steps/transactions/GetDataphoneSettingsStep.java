package com.robinfood.paymentmethodsbc.services.steps.transactions;

import com.robinfood.paymentmethodsbc.annotations.BasicLog;
import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.SettingsDTO;
import com.robinfood.paymentmethodsbc.dto.steps.TransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.enums.StepType;
import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.model.Terminal;
import com.robinfood.paymentmethodsbc.repositories.TerminalPaymentMethodSettingsCachedRepository;
import com.robinfood.paymentmethodsbc.services.steps.StepActions;
import com.robinfood.paymentmethodsbc.utils.TransactionLogger;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class GetDataphoneSettingsStep implements StepActions {
    private static final String DATAPHONE_CODE_KEY = "posReferenceId";

    private final TerminalPaymentMethodSettingsCachedRepository settingsCachedRepository;

    public GetDataphoneSettingsStep(
        TerminalPaymentMethodSettingsCachedRepository settingsCachedRepository
    ) {
        this.settingsCachedRepository = settingsCachedRepository;
    }

    @BasicLog
    @Override
    public void invoke(Object pipe) throws PaymentStepException, EntityNotFoundException {
        if (pipe instanceof PaymentPipeDTO) {
            invokeForTransactionPipeDTO((PaymentPipeDTO) pipe);
            return;

        } else if (pipe instanceof TransactionStatusPipeDTO) {
            invokeForTransactionStatusPipeDTO((TransactionStatusPipeDTO) pipe);
            return;
        }

        throw new PaymentStepException(
            StepType.GET_DATAPHONE_SETTINGS,
            String.format(
                "No se ha realizado ninguna acción en %s",
                getClass().getSimpleName()
            )
        );
    }

    private void invokeForTransactionPipeDTO(
        final PaymentPipeDTO paymentPipeDTO
    ) throws PaymentStepException {

        final Terminal terminal = getTerminal(paymentPipeDTO);
        TransactionLogger.invoke(paymentPipeDTO);
        SettingsDTO settings = paymentPipeDTO.getSettings();
        if (settings == null) {
            settings = new SettingsDTO();
        }

        final Map<String, String> terminalSettings = getTerminalSettings(
            terminal,
            paymentPipeDTO.getPaymentGateway().getId()
        );

        settings.setTerminalConfig(terminalSettings);
        paymentPipeDTO.setSettings(settings);
        paymentPipeDTO.getTransactionDetail().setDataphoneCode(
            terminalSettings.get(DATAPHONE_CODE_KEY)
        );
        TransactionLogger.clear();
    }

    private void invokeForTransactionStatusPipeDTO(
        final TransactionStatusPipeDTO pipe
    ) {
        Terminal terminal = pipe.getTransactionDetail().getTerminal();
        
        if(Objects.nonNull(terminal)){
            TransactionLogger.invoke(pipe.getTransaction());
            SettingsDTO settings = Optional.of(pipe.getSettings()).orElse(new SettingsDTO());

            final Map<String, String> terminalSettings = getTerminalSettings(
                terminal,
                pipe.getTransaction().getPaymentGateway().getId()
            );

            settings.setTerminalConfig(terminalSettings);
            pipe.setSettings(settings);
            TransactionLogger.clear();
        }
    }

    /**
     * Get terminal in Object DTO
     * @param transactionPipeDTO {@linkplain PaymentPipeDTO}
     * @return {@linkplain Terminal}
     * @throws BaseException if not parameter exists
     */
    private Terminal getTerminal(
        final PaymentPipeDTO transactionPipeDTO
    ) throws PaymentStepException {

        Terminal terminal = transactionPipeDTO.getTerminal();

        if (terminal == null || terminal.getId() == null) {
            throw new PaymentStepException(
                StepType.GET_DATAPHONE_SETTINGS,
                "Terminal is required"
            );
        }

        return terminal;
    }

    private Map<String, String> getTerminalSettings(
        final Terminal terminalModel,
        final Long paymentGatewayId
    ) {

        return Optional.ofNullable(terminalModel)
            .stream()
            .map(terminal ->
                settingsCachedRepository.findAllSettingsTerminalPaymentMethod(
                    terminal.getId(),
                    paymentGatewayId
                )
            )
            .findFirst()
            .orElse(new HashMap<>());
    }
}
