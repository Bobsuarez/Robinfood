package com.robinfood.paymentmethodsbc.services.steps.common;

import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.enums.StepType;
import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.model.PaymentGatewayCreditCard;
import com.robinfood.paymentmethodsbc.repositories.PaymentGatewayCreditCardsRepository;
import com.robinfood.paymentmethodsbc.security.CipherUtility;
import com.robinfood.paymentmethodsbc.services.steps.StepActions;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GetPaymentGatewayCreditCardStep implements StepActions {
    private final PaymentGatewayCreditCardsRepository paymentGatewayCreditCardsRepository;
    private final CipherUtility cipherUtility;

    public GetPaymentGatewayCreditCardStep(
        PaymentGatewayCreditCardsRepository paymentGatewayCreditCardsRepository,
        CipherUtility cipherUtility
    ) {
        this.paymentGatewayCreditCardsRepository =
            paymentGatewayCreditCardsRepository;
        this.cipherUtility = cipherUtility;
    }

    @Override
    public void invoke(Object pipe)
        throws PaymentStepException, EntityNotFoundException {
        if (pipe instanceof PaymentPipeDTO) {
            PaymentPipeDTO paymentPipeDTO = (PaymentPipeDTO) pipe;

            validateCreditCardRequired(paymentPipeDTO);

            paymentPipeDTO.setPaymentGatewayCreditCard(
                getPaymentGatewayCreditCard(
                    paymentPipeDTO.getCreditCard().getId(),
                    paymentPipeDTO.getPaymentGateway().getId()
                )
            );

            return;
        }

        throw new PaymentStepException(
            StepType.GET_PAYMENT_GATEWAY_CREDIT_CARD,
            String.format(
                "No se ha realizado ninguna acción en %s",
                getClass().getSimpleName()
            )
        );
    }

    /**
     * Validate required credit card information in the payment process.
     * @param transactionCreatePipeDTO {@linkplain PaymentPipeDTO}
     * @throws BaseException if the required information does not exist
     */
    private void validateCreditCardRequired(
        final PaymentPipeDTO transactionCreatePipeDTO
    ) throws PaymentStepException {
        if(transactionCreatePipeDTO
            .getTransactionRequestDTO()
            .getPaymentMethod()
            .getCreditCardId() == null
        ){
            log.error("No se recibe información de TC en transacción {}",
                transactionCreatePipeDTO.getEntity()
            );
            throw new PaymentStepException(
                StepType.GET_PAYMENT_GATEWAY_CREDIT_CARD,
                "credit card identifier is required"
            );
        }
    }

    private PaymentGatewayCreditCard getPaymentGatewayCreditCard(
        Long creditCardId,
        Long paymentGatewayId
    )
        throws PaymentStepException {
        Optional<PaymentGatewayCreditCard> optional;
        optional =
            paymentGatewayCreditCardsRepository.findByCreditCardIdAndPaymentGatewayId(
                creditCardId,
                paymentGatewayId
            );

        if (optional.isEmpty()) {
            throw new PaymentStepException(
                StepType.GET_PAYMENT_GATEWAY_CREDIT_CARD,
                String.format(
                    "La tarjeta de crédito con id %s no se encuentra tokenizada en el gateway con id %s",
                    creditCardId,
                    paymentGatewayId
                )
            );
        }

        PaymentGatewayCreditCard paymentGatewayCreditCard = optional.get();

        paymentGatewayCreditCard.setToken(
            cipherUtility.decryptTextWithPrivateKey(
                paymentGatewayCreditCard.getToken()
            )
        );

        return paymentGatewayCreditCard;
    }
}
