package com.robinfood.paymentmethodsbc.services.steps.creditcards;

import com.robinfood.paymentmethodsbc.components.providers.BCIProvider;
import com.robinfood.paymentmethodsbc.dto.steps.CreditCardDeletePipeDTO;
import com.robinfood.paymentmethodsbc.enums.StepType;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentMethodsException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.model.CreditCard;
import com.robinfood.paymentmethodsbc.model.PaymentGatewayCreditCard;
import com.robinfood.paymentmethodsbc.repositories.PaymentGatewayCreditCardsRepository;
import com.robinfood.paymentmethodsbc.repositories.PaymentGatewaySettingsCachedRepository;
import com.robinfood.paymentmethodsbc.security.CipherUtility;
import com.robinfood.paymentmethodsbc.services.steps.StepActions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static com.robinfood.paymentmethodsbc.constants.AppConstants.ORCHESTRATOR_ID_SETTINGS_NAME;
import static com.robinfood.paymentmethodsbc.utils.Utilities.stringToLong;

@Slf4j
@Component
public class RemoveTokenCreditCardStep implements StepActions {
    private final CipherUtility cipherUtility;
    private final PaymentGatewaySettingsCachedRepository settingsCachedRepository;
    private final PaymentGatewayCreditCardsRepository paymentGatewayCreditCardsRepository;
    private final BCIProvider paymentGatewayProvider;

    public RemoveTokenCreditCardStep(
        CipherUtility cipherUtility,
        PaymentGatewaySettingsCachedRepository settingsCachedRepository,
        PaymentGatewayCreditCardsRepository paymentGatewayCreditCardsRepository,
        BCIProvider paymentGatewayProvider
    ) {
        this.cipherUtility = cipherUtility;
        this.settingsCachedRepository = settingsCachedRepository;
        this.paymentGatewayCreditCardsRepository = paymentGatewayCreditCardsRepository;
        this.paymentGatewayProvider = paymentGatewayProvider;
    }

    @Override
    public void invoke(Object pipe) throws PaymentStepException, EntityNotFoundException {

        if (pipe instanceof CreditCardDeletePipeDTO) {
            CreditCardDeletePipeDTO creditCardPipeDTO = (CreditCardDeletePipeDTO) pipe;
            creditCardTokenRemove(creditCardPipeDTO);
            return;
        }

        throw new PaymentStepException(
            StepType.REMOVE_TOKEN_CREDIT_CARD,
            String.format(
                "No action has been taken at %s", getClass().getSimpleName()
            )
        );
    }

    private String validateCreditCardToken(
        Long creditCardId,
        Long paymentGatewayId,
        CreditCardDeletePipeDTO creditCardPipeDTO
    ) {
        String decryptedToken = null;
        Optional<PaymentGatewayCreditCard> optional;
        PaymentGatewayCreditCard infoToken;
        try {
            optional = paymentGatewayCreditCardsRepository.findByCreditCardIdAndPaymentGatewayId(
                creditCardId, paymentGatewayId
            );
            if (optional.isEmpty()) {
                log.info(
                    "There is no credit card id {} and payment gateway id {}",
                    creditCardId, paymentGatewayId
                );
            } else {
                infoToken = optional.get();
                creditCardPipeDTO.getPaymentGatewayCreditCardsToDelete().add(infoToken);

                decryptedToken = cipherUtility.decryptTextWithPrivateKey(infoToken.getToken());
                if (Objects.isNull(decryptedToken)) {
                    log.info(
                        "Token obtained for credit card id {} and payment gateway id {} can not be decrypted",
                        creditCardId, paymentGatewayId
                    );
                }
            }
        } catch (Exception e) {
            log.error(
                String.format(
                    "Error validating detokenizing credit card in payment gateway %s", paymentGatewayId
                ), e
            );
        }
        return decryptedToken;
    }

    private void doCreditCardRemove(
        Long paymentGatewayId,
        CreditCard creditCard,
        Map<String, String> config,
        CreditCardDeletePipeDTO creditCardPipeDTO
    ) {
        try {
            String decryptedToken = validateCreditCardToken(
                creditCard.getId(), paymentGatewayId, creditCardPipeDTO
            );
            if (Objects.nonNull(decryptedToken)) {
                Map<String, String> orchConfig = settingsCachedRepository.getSettingsOrchestratorPaymentGateway(
                    creditCard.getCountry().getId(),
                    stringToLong(config.get(ORCHESTRATOR_ID_SETTINGS_NAME))
                );
                paymentGatewayProvider.doCreditCardRemove(
                    config, orchConfig, String.valueOf(creditCard.getUserId()), decryptedToken
                );
            }
        } catch (PaymentMethodsException e) {
            log.error(
                String.format(
                    "Error detokenizing credit card in payment gateway %s",
                    paymentGatewayId
                ), e
            );
        }
    }

    private void creditCardTokenRemove(CreditCardDeletePipeDTO creditCardPipeDTO) {
        CreditCard creditCard = creditCardPipeDTO.getCreditCard();

        Map<Long, Map<String, String>> paymentGatewaySettingsMap =
            settingsCachedRepository.getPaymentGatewaySettingsByCountryForTokenization(
                creditCard.getCountry().getId()
            );

        paymentGatewaySettingsMap.entrySet().stream().forEach(
            (Map.Entry<Long, Map<String, String>> entry) -> doCreditCardRemove(
                entry.getKey(), creditCard, entry.getValue(), creditCardPipeDTO
            )
        );
    }
}
