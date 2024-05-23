package com.robinfood.paymentmethodsbc.services.steps.creditcards;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.stereotype.Component;
import com.robinfood.paymentmethodsbc.dto.steps.CreditCardUpdatePipeDTO;
import com.robinfood.paymentmethodsbc.enums.StepType;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.model.CreditCard;
import com.robinfood.paymentmethodsbc.model.PaymentGateway;
import com.robinfood.paymentmethodsbc.model.PaymentGatewayCreditCard;
import com.robinfood.paymentmethodsbc.repositories.CreditCardsRepository;
import com.robinfood.paymentmethodsbc.repositories.PaymentGatewayCreditCardsRepository;
import com.robinfood.paymentmethodsbc.services.steps.StepActions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UpdateCreditCardStep implements StepActions {
    private final CreditCardsRepository creditCardsRepository;
    private final PaymentGatewayCreditCardsRepository paymentGatewayCreditCardsRepository;


    public UpdateCreditCardStep(
        CreditCardsRepository creditCardsRepository,
        PaymentGatewayCreditCardsRepository paymentGatewayCreditCardsRepository
    ) {
        this.creditCardsRepository = creditCardsRepository;
        this.paymentGatewayCreditCardsRepository = paymentGatewayCreditCardsRepository;
    }

    @Override
    public void invoke(Object pipe) throws PaymentStepException, EntityNotFoundException {
        if (pipe instanceof CreditCardUpdatePipeDTO) {
            CreditCardUpdatePipeDTO creditCardUpdatePipeDTO = (CreditCardUpdatePipeDTO) pipe;
            deleteCurrentCreditCard(creditCardUpdatePipeDTO);
            createNewCreditCard(creditCardUpdatePipeDTO);

            return ;
        }

        throw new PaymentStepException(
            StepType.UPDATE_CREDIT_CARD,
            String.format(
                "No action has been taken on %s",
                getClass().getSimpleName()
            )
        );
    }

    private void createNewCreditCard(CreditCardUpdatePipeDTO creditCardUpdatePipeDTO){
        CreditCard newCreditCard =  creditCardUpdatePipeDTO.getCreditCardNew();
        log.info(
            "UPDATE_CREADIT_CARD_DB Create new creditcard for USER_ID:{} Start", 
            newCreditCard.getUserId()
        );

        newCreditCard.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
        newCreditCard.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));
        newCreditCard.setIsUpdated(BigDecimal.ONE.intValue());
        creditCardsRepository.save(newCreditCard);

        paymentGatewayCreditCardsRepository.save(
            PaymentGatewayCreditCard.builder()
                .creditCard(newCreditCard)
                .paymentGateway(
                    PaymentGateway.builder()
                    .id(creditCardUpdatePipeDTO.getPaymentGatewayId())
                    .build()
                )
                .token(creditCardUpdatePipeDTO.getToken())
                .createdAt(LocalDateTime.now(ZoneOffset.UTC))
                .updatedAt(LocalDateTime.now(ZoneOffset.UTC))
            .build()
        );

        creditCardUpdatePipeDTO.setCreditCardNew(newCreditCard);
        log.info(
            "UPDATE_CREADIT_CARD_DB Create new creditcard End whit CREDIT_CARD_ID:{}",
            newCreditCard.getId()
        );

    }


    private void deleteCurrentCreditCard(CreditCardUpdatePipeDTO creditCardUpdatePipeDTO){
        CreditCard currentCreditCard =  creditCardUpdatePipeDTO.getCreditCard();

        log.info(
            "UPDATE_CREADIT_CARD_DB Delete current  creditcard for USER_ID:{} Start", 
            currentCreditCard.getUserId()
        );

        LocalDateTime deletedAt = LocalDateTime.now(ZoneOffset.UTC);

        currentCreditCard.setDeletedAt(deletedAt);
        creditCardsRepository.save(currentCreditCard);

        creditCardUpdatePipeDTO
            .getPaymentGatewayCreditCardsToDelete()
            .forEach(
                (PaymentGatewayCreditCard paymentGatewayCreditCard) -> {
                    paymentGatewayCreditCard.setDeletedAt(deletedAt);
                    paymentGatewayCreditCardsRepository.save(
                        paymentGatewayCreditCard
                    );
                }
            );
        
        log.info(
            "UPDATE_CREADIT_CARD_DB Delete current  creditcard for USER_ID:{} End", 
            currentCreditCard.getUserId()
        );
    }
    
}
