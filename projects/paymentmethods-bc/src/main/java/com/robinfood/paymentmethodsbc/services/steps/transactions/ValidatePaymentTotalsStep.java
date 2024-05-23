package com.robinfood.paymentmethodsbc.services.steps.transactions;

import com.robinfood.paymentmethodsbc.annotations.BasicLog;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO;
import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.enums.StepType;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.services.steps.StepActions;
import org.springframework.stereotype.Component;

@Component
public class ValidatePaymentTotalsStep implements StepActions {

    @BasicLog
    @Override
    public void invoke(Object pipe)
        throws PaymentStepException, EntityNotFoundException {
        if (pipe instanceof PaymentPipeDTO) {
            invokeForTransactionPipeDTO((PaymentPipeDTO) pipe);
            return;
        }

        throw new PaymentStepException(
            StepType.VALIDATE_TOTALS,
            String.format(
                "No se ha realizado ninguna acción en %s",
                getClass().getSimpleName()
            )
        );
    }

    private void invokeForTransactionPipeDTO(
        PaymentPipeDTO transactionPipeDTO
    )
        throws PaymentStepException {
        PaymentRequestDTO.PaymentDTO payment = transactionPipeDTO
            .getTransactionRequestDTO()
            .getPayment();

        int diff = payment
            .getSubtotal()
            .add(payment.getTax())
            .compareTo(payment.getTotal());

        if (diff != 0) {
            throw new PaymentStepException(
                StepType.VALIDATE_TOTALS,
                String.format(
                    "Los totales no coinciden, %s != (%s + %s)",
                    payment.getTotal(),
                    payment.getTax(),
                    payment.getSubtotal()
                )
            );
        }
    }
}
