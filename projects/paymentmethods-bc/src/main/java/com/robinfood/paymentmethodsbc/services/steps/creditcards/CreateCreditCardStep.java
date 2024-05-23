package com.robinfood.paymentmethodsbc.services.steps.creditcards;

import static com.robinfood.paymentmethodsbc.constants.CreditCardConstants.DEFAULT_EMAIL;
import static com.robinfood.paymentmethodsbc.constants.CreditCardConstants.DEFAULT_UPDATE_VALUE;
import static com.robinfood.paymentmethodsbc.constants.CreditCardConstants.UPDATED;

import com.robinfood.paymentmethodsbc.dto.api.creditcards.CreateCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.dto.steps.CreditCardCreatePipeDTO;
import com.robinfood.paymentmethodsbc.enums.StepType;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.repositories.CreditCardsRepository;
import com.robinfood.paymentmethodsbc.services.steps.StepActions;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class CreateCreditCardStep implements StepActions {
    private final CreditCardsRepository creditCardsRepository;

    public CreateCreditCardStep(CreditCardsRepository creditCardsRepository) {
        this.creditCardsRepository = creditCardsRepository;
    }

    @Override
    public void invoke(Object pipe)
        throws PaymentStepException, EntityNotFoundException {
        if (pipe instanceof CreditCardCreatePipeDTO) {
            CreditCardCreatePipeDTO creditCardPipeDTO = (CreditCardCreatePipeDTO) pipe;

            creditCardPipeDTO
                .getCreditCard()
                .setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
            creditCardPipeDTO
                .getCreditCard()
                .setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));
            creditCardPipeDTO
                .getCreditCard()
                    .setIsUpdated(getUpdated(creditCardPipeDTO.getCreditCardRequestDTO()));

            creditCardPipeDTO.setCreditCard(
                creditCardsRepository.save(creditCardPipeDTO.getCreditCard())
            );

            return;
        }

        throw new PaymentStepException(
            StepType.CREATE_CREDIT_CARD,
            String.format(
                "No se ha realizado ninguna acción en %s",
                getClass().getSimpleName()
            )
        );
    }
    private static int getUpdated(CreateCreditCardRequestDTO createCreditCardRequestDTO) {
        return Optional.ofNullable(createCreditCardRequestDTO.getUserEmail())
            .filter(CreateCreditCardStep::isEmailOk)
            .map(value -> UPDATED)
            .orElse(DEFAULT_UPDATE_VALUE);
    }

    private static boolean isEmailOk(String email) {
        return !DEFAULT_EMAIL.equals(email);
    }
}
