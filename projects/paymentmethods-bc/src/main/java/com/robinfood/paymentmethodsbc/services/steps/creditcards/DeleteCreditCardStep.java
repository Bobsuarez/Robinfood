package com.robinfood.paymentmethodsbc.services.steps.creditcards;

import com.robinfood.paymentmethodsbc.dto.steps.CreditCardDeletePipeDTO;
import com.robinfood.paymentmethodsbc.enums.StepType;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.model.CreditCard;
import com.robinfood.paymentmethodsbc.model.PaymentGatewayCreditCard;
import com.robinfood.paymentmethodsbc.repositories.CreditCardsRepository;
import com.robinfood.paymentmethodsbc.repositories.PaymentGatewayCreditCardsRepository;
import com.robinfood.paymentmethodsbc.services.steps.StepActions;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import javax.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class DeleteCreditCardStep implements StepActions {
    private final CreditCardsRepository creditCardsRepository;
    private final PaymentGatewayCreditCardsRepository paymentGatewayCreditCardsRepository;

    public DeleteCreditCardStep(
        CreditCardsRepository creditCardsRepository,
        PaymentGatewayCreditCardsRepository paymentGatewayCreditCardsRepository
    ) {
        this.creditCardsRepository = creditCardsRepository;
        this.paymentGatewayCreditCardsRepository =
            paymentGatewayCreditCardsRepository;
    }

    @Override
    @Transactional
    public void invoke(Object pipe)
        throws PaymentStepException, EntityNotFoundException {
        if (pipe instanceof CreditCardDeletePipeDTO) {
            CreditCardDeletePipeDTO creditCardDeletePipeDTO = (CreditCardDeletePipeDTO) pipe;

            CreditCard creditCard = creditCardDeletePipeDTO.getCreditCard();
            LocalDateTime deletedAt = LocalDateTime.now(ZoneOffset.UTC);

            creditCard.setDeletedAt(deletedAt);
            creditCardsRepository.save(creditCard);

            creditCardDeletePipeDTO
                .getPaymentGatewayCreditCardsToDelete()
                .forEach(
                    (PaymentGatewayCreditCard paymentGatewayCreditCard) -> {
                        paymentGatewayCreditCard.setDeletedAt(deletedAt);
                        paymentGatewayCreditCardsRepository.save(
                            paymentGatewayCreditCard
                        );
                    }
                );

            return;
        }

        throw new PaymentStepException(
            StepType.DELETE_CREDIT_CARD,
            String.format(
                "No se ha realizado ninguna acción en %s",
                getClass().getSimpleName()
            )
        );
    }
}
