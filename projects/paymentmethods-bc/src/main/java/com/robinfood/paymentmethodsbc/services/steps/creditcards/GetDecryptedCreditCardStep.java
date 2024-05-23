package com.robinfood.paymentmethodsbc.services.steps.creditcards;

import com.robinfood.paymentmethodsbc.dto.steps.CreditCardUpdatePipeDTO;
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
public class GetDecryptedCreditCardStep implements StepActions {
    private final CreditCardsRepository creditCardsRepository;
    private final CipherUtility cipherUtility;

    public GetDecryptedCreditCardStep(
        CreditCardsRepository creditCardsRepository,
        CipherUtility cipherUtility
    ) {
        this.creditCardsRepository = creditCardsRepository;
        this.cipherUtility = cipherUtility;
    }

    @Override
    public void invoke(Object pipe)
        throws PaymentStepException, EntityNotFoundException {
        if (pipe instanceof CreditCardUpdatePipeDTO) {
            CreditCardUpdatePipeDTO creditCardPipeDTO = (CreditCardUpdatePipeDTO) pipe;

            CreditCard creditCard = getCreditCard(
                creditCardPipeDTO.getCreditCardNew().getId()
            );
            creditCardPipeDTO.setCreditCardNew(
                translateEncryption(creditCard)
            );

            return;
        }

        throw new PaymentStepException(
            StepType.GET_DECRYPTED_CREDIT_CARD,
            String.format(
                "No action has been taken on %s",
                getClass().getSimpleName()
            )
        );
    }

    private CreditCard getCreditCard(Long id) throws EntityNotFoundException {
        Optional<CreditCard> optionalCreditCard = creditCardsRepository.findById(
            id
        );

        optionalCreditCard.orElseThrow(()->
            new EntityNotFoundException(
                CreditCard.class.getSimpleName(),
                String.valueOf(id)
            )
        );

        return optionalCreditCard.get();
    }

    private CreditCard translateEncryption(CreditCard creditCard) {
        creditCard.setIsUpdated(1);
        creditCard.setNumber(
            cipherUtility.decryptTextWithPrivateKey(creditCard.getNumber())
        );
        creditCard.setCardHolderName(
            cipherUtility.decryptTextWithPrivateKey(
                creditCard.getCardHolderName()
            )
        );
        creditCard.setUserIdentificationNumber(
            cipherUtility.decryptTextWithPrivateKey(
                creditCard.getUserIdentificationNumber()
            )
        );
        creditCard.setUserAddress(
            cipherUtility.decryptTextWithPrivateKey(
                creditCard.getUserAddress()
            )
        );
        creditCard.setUserCity(
            cipherUtility.decryptTextWithPrivateKey(
                creditCard.getUserCity()
            )
        );
        creditCard.setExpirationMonth(
            cipherUtility.decryptTextWithPrivateKey(
                creditCard.getExpirationMonth()
            )
        );
        creditCard.setExpirationYear(
            cipherUtility.decryptTextWithPrivateKey(
                creditCard.getExpirationYear()
            )
        );
        creditCard.setUserEmail( 
            cipherUtility.decryptTextWithPrivateKey(
                creditCard.getUserEmail()
            )
        );

        return creditCard;
    }
}
