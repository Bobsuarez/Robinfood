package com.robinfood.paymentmethodsbc.services.steps.creditcards;

import com.robinfood.paymentmethodsbc.dto.steps.CreditCardCreatePipeDTO;
import com.robinfood.paymentmethodsbc.enums.StepType;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.model.CreditCard;
import com.robinfood.paymentmethodsbc.repositories.CreditCardsRepository;
import com.robinfood.paymentmethodsbc.security.CipherUtility;
import com.robinfood.paymentmethodsbc.services.steps.StepActions;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class GetEncryptedCreditCardStep implements StepActions {
    private final CreditCardsRepository creditCardsRepository;
    private final CipherUtility cipherUtility;

    public GetEncryptedCreditCardStep(
        CreditCardsRepository creditCardsRepository,
        CipherUtility cipherUtility
    ) {
        this.creditCardsRepository = creditCardsRepository;
        this.cipherUtility = cipherUtility;
    }

    @Override
    public void invoke(Object pipe)
        throws PaymentStepException, EntityNotFoundException {
        if (pipe instanceof CreditCardCreatePipeDTO) {
            CreditCardCreatePipeDTO creditCardPipeDTO = (CreditCardCreatePipeDTO) pipe;

            CreditCard creditCard = getCreditCard(
                creditCardPipeDTO.getCreditCard().getId()
            );
            if (creditCardPipeDTO.getGeneratedTokens() > 0) {
                creditCardPipeDTO.setCreditCard(
                    translateEncryption(creditCard)
                );
            } else {
                creditCardsRepository.delete(creditCard);
            }

            return;
        }

        throw new PaymentStepException(
            StepType.GET_ENCRYPTED_CREDIT_CARD,
            String.format(
                "No se ha realizado ninguna acción en %s",
                getClass().getSimpleName()
            )
        );
    }

    private CreditCard getCreditCard(Long id) throws EntityNotFoundException {
        Optional<CreditCard> optionalCreditCard = creditCardsRepository.findById(
            id
        );

        if (optionalCreditCard.isEmpty()) {
            throw new EntityNotFoundException(
                CreditCard.class.getSimpleName(),
                String.valueOf(id)
            );
        }

        return optionalCreditCard.get();
    }

    private CreditCard translateEncryption(CreditCard creditCard) {
        creditCard.setNumber(
            cipherUtility.translateEncryptionPrivate(creditCard.getNumber())
        );
        creditCard.setCardHolderName(
            cipherUtility.translateEncryptionPrivate(
                creditCard.getCardHolderName()
            )
        );
        creditCard.setUserIdentificationNumber(
            cipherUtility.translateEncryptionPrivate(
                creditCard.getUserIdentificationNumber()
            )
        );
        creditCard.setUserEmail(
            cipherUtility.translateEncryptionPrivate(
                creditCard.getUserEmail()
            )
        );
        creditCard.setExpirationMonth(
            cipherUtility.translateEncryptionPrivate(
                creditCard.getExpirationMonth()
            )
        );
        creditCard.setExpirationYear(
            cipherUtility.translateEncryptionPrivate(
                creditCard.getExpirationYear()
            )
        );
        creditCard.setUserAddress(
            cipherUtility.translateEncryptionPrivate(
                creditCard.getUserAddress()
            )
        );
        creditCard.setUserCity(
            cipherUtility.translateEncryptionPrivate(creditCard.getUserCity())
        );
        return creditCard;
    }
}
