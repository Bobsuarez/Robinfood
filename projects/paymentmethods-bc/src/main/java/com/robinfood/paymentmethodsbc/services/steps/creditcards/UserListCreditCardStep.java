package com.robinfood.paymentmethodsbc.services.steps.creditcards;

import com.robinfood.paymentmethodsbc.dto.steps.CreditCardUserListPipeDTO;
import com.robinfood.paymentmethodsbc.enums.StepType;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.model.CreditCard;
import com.robinfood.paymentmethodsbc.repositories.CreditCardsRepository;
import com.robinfood.paymentmethodsbc.repositories.PaymentGatewayCreditCardsRepository;
import com.robinfood.paymentmethodsbc.security.CipherUtility;
import com.robinfood.paymentmethodsbc.services.steps.StepActions;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserListCreditCardStep implements StepActions {
    private final PaymentGatewayCreditCardsRepository paymentGatewayCreditCardsRepository;
    private final CreditCardsRepository creditCardsRepository;
    private final CipherUtility cipherUtility;

    public UserListCreditCardStep(
        PaymentGatewayCreditCardsRepository paymentGatewayCreditCardsRepository,
        CreditCardsRepository creditCardsRepository,
        CipherUtility cipherUtility
    ) {
        this.paymentGatewayCreditCardsRepository =
            paymentGatewayCreditCardsRepository;

        this.creditCardsRepository = creditCardsRepository;
        this.cipherUtility = cipherUtility;
    }

    @Override
    public void invoke(Object pipe)
        throws PaymentStepException, EntityNotFoundException {
        if (pipe instanceof CreditCardUserListPipeDTO) {
            CreditCardUserListPipeDTO creditCardUserListPipeDTO = (CreditCardUserListPipeDTO) pipe;

            List<CreditCard> creditCards = creditCardsRepository.findByUserIdAndCountryIdAndDeletedAt(
                creditCardUserListPipeDTO.getUserId(),
                creditCardUserListPipeDTO.getCountryId(),
                null
            );

            List<Long> paymentGatewayCreditCards = paymentGatewayCreditCardsRepository
                .findByCreditCardIdAndPaymentGatewayIdAndDeletedAtIsNull(
                    creditCards
                        .stream()
                        .map(item -> item.getId())
                        .collect(Collectors.toList()),
                    creditCardUserListPipeDTO.getPaymentGateway().getId()
                )
                .stream()
                .map(item -> item.getCreditCard().getId())
                .collect(Collectors.toList());

            creditCardUserListPipeDTO.setCreditCardList(
                creditCards
                    .stream()
                    .filter(
                        item -> paymentGatewayCreditCards.contains(item.getId())
                    )
                    .map(this::translateEncryption)
                    .collect(Collectors.toList())
            );

            return;
        }

        throw new PaymentStepException(
            StepType.USER_LIST_CREDIT_CARD,
            String.format(
                "No se ha realizado ninguna acción en %s",
                getClass().getSimpleName()
            )
        );
    }

    private CreditCard translateEncryption(CreditCard creditCard) {
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
        creditCard.setUserEmail(
            cipherUtility.decryptTextWithPrivateKey(
                creditCard.getUserEmail()
            )
        );
        creditCard.setUserAddress(
            cipherUtility.decryptTextWithPrivateKey(creditCard.getUserAddress())
        );
        creditCard.setUserCity(
            cipherUtility.decryptTextWithPrivateKey(creditCard.getUserCity())
        );
        return creditCard;
    }
}
