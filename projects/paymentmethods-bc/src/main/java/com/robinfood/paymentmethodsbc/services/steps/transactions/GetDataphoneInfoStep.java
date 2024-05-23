package com.robinfood.paymentmethodsbc.services.steps.transactions;

import com.robinfood.paymentmethodsbc.annotations.BasicLog;
import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.enums.StepType;
import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.model.Terminal;
import com.robinfood.paymentmethodsbc.repositories.TerminalsRepository;
import com.robinfood.paymentmethodsbc.services.steps.StepActions;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

@Component
public class GetDataphoneInfoStep implements StepActions {
    private final TerminalsRepository terminalsRepository;

    public GetDataphoneInfoStep(TerminalsRepository terminalsRepository) {
        this.terminalsRepository = terminalsRepository;
    }

    @BasicLog
    @Override
    public void invoke(Object pipe) throws PaymentStepException, EntityNotFoundException {
        if (pipe instanceof PaymentPipeDTO) {
            invokeForTransactionPipeDTO((PaymentPipeDTO) pipe);
            return;
        }

        throw new PaymentStepException(
            StepType.GET_DATAPHONE_INFO,
            String.format(
                "No se ha realizado ninguna acción en %s",
                getClass().getSimpleName()
            )
        );
    }

    private void invokeForTransactionPipeDTO(
        final PaymentPipeDTO paymentPipeDTO
    ) throws PaymentStepException, EntityNotFoundException {

        paymentPipeDTO.setTerminal(getTerminalByUuid(paymentPipeDTO));
    }

    /**
     * Query a terminal in database by uuid
     * @param transactionPipeDTO {@linkplain PaymentPipeDTO}
     * @return {@linkplain Terminal}
     * @throws BaseException if not parameter exists
     * @throws EntityNotFoundException if no terminal exists in the database
     */
    private Terminal getTerminalByUuid(final PaymentPipeDTO transactionPipeDTO)
        throws PaymentStepException {
        final String terminalUuid = transactionPipeDTO.getTransactionRequestDTO().getPaymentMethod().getTerminalUuid();

        if (Strings.isBlank(terminalUuid)) {
            throw new PaymentStepException(StepType.GET_DATAPHONE_INFO, "Terminal uuid is required");
        }

        return terminalsRepository
            .findByUuidAndStatusAndDeletedAt(
                terminalUuid, 
                Terminal.STATUS_ENABLED, null)
            .orElseThrow(
                () -> new PaymentStepException(
                    StepType.GET_DATAPHONE_INFO, 
                    "Terminal not found: ".concat(terminalUuid)
                )
            );
    }

}
