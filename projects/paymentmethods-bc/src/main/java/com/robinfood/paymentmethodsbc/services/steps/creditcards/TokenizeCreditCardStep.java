package com.robinfood.paymentmethodsbc.services.steps.creditcards;

import com.robinfood.paymentmethodsbc.components.providers.BCIProvider;
import com.robinfood.paymentmethodsbc.constants.AppConstants;
import com.robinfood.paymentmethodsbc.dto.CreditCardTokenDTO;
import com.robinfood.paymentmethodsbc.dto.steps.CreditCardCreatePipeDTO;
import com.robinfood.paymentmethodsbc.enums.StepType;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentMethodsException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.mappers.CreditCardMapper;
import com.robinfood.paymentmethodsbc.model.CreditCard;
import com.robinfood.paymentmethodsbc.model.PaymentGateway;
import com.robinfood.paymentmethodsbc.model.PaymentGatewayCreditCard;
import com.robinfood.paymentmethodsbc.repositories.PaymentGatewayCreditCardsRepository;
import com.robinfood.paymentmethodsbc.repositories.PaymentGatewaySettingsCachedRepository;
import com.robinfood.paymentmethodsbc.services.steps.StepActions;
import com.robinfood.paymentmethodsbc.utils.Utilities;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TokenizeCreditCardStep implements StepActions {
    private final PaymentGatewaySettingsCachedRepository settingsCachedRepository;
    private final PaymentGatewayCreditCardsRepository paymentGatewayCreditCardsRepository;
    private final BCIProvider paymentGatewayProvider;

    public TokenizeCreditCardStep(
        PaymentGatewaySettingsCachedRepository settingsCachedRepository,
        PaymentGatewayCreditCardsRepository paymentGatewayCreditCardsRepository,
        BCIProvider paymentGatewayProvider
    ) {
        this.settingsCachedRepository = settingsCachedRepository;
        this.paymentGatewayCreditCardsRepository =
            paymentGatewayCreditCardsRepository;
        this.paymentGatewayProvider = paymentGatewayProvider;
    }

    @Override
    public void invoke(Object pipe)
        throws PaymentStepException, EntityNotFoundException {
        if (pipe instanceof CreditCardCreatePipeDTO) {
            CreditCardCreatePipeDTO creditCardPipeDTO = (CreditCardCreatePipeDTO) pipe;

            int generatedTokens = creditCardTokenize(
                CreditCardMapper.getCreditCardTokenDTO(
                    creditCardPipeDTO.getCreditCardRequestDTO()
                ),
                creditCardPipeDTO.getCreditCard()
            );

            creditCardPipeDTO.setGeneratedTokens(generatedTokens);

            return;
        }

        throw new PaymentStepException(
            StepType.REMOVE_TOKEN_CREDIT_CARD,
            String.format(
                "No se ha realizado ninguna acción en %s",
                getClass().getSimpleName()
            )
        );
    }

    private int creditCardTokenize(
        CreditCardTokenDTO creditCardTokenDTO,
        CreditCard creditCard
    ) {
        Map<Long, Map<String, String>> map = settingsCachedRepository.getPaymentGatewaySettingsByCountryForTokenization(
            creditCard.getCountry().getId()
        );

        int successfullyTokenized = 0;
        for (Map.Entry<Long, Map<String, String>> entry : map.entrySet()) {
            String creditCardToken = null;
            try {
                Map<String, String> config = entry.getValue();

                Map<String, String> orchConfig = settingsCachedRepository.getSettingsOrchestratorPaymentGateway(
                    creditCard.getCountry().getId(),
                    Utilities.stringToLong(
                        config.get(AppConstants.ORCHESTRATOR_ID_SETTINGS_NAME)
                    )
                );

                creditCardToken =
                    paymentGatewayProvider.doCreditCardTokenization(
                        config,
                        orchConfig,
                        creditCardTokenDTO
                    );

                if (creditCardToken != null) {
                    paymentGatewayCreditCardsRepository.save(
                        PaymentGatewayCreditCard
                            .builder()
                            .paymentGateway(new PaymentGateway(entry.getKey()))
                            .creditCard(creditCard)
                            .token(creditCardToken)
                            .createdAt(LocalDateTime.now(ZoneOffset.UTC))
                            .updatedAt(LocalDateTime.now(ZoneOffset.UTC))
                            .build()
                    );
                    successfullyTokenized++;
                }
            } catch (PaymentMethodsException e) {
                log.error(
                    String.format(
                        "Error tokenizing credit card with payment gateway %s",
                        entry.getKey()
                    ),
                    e
                );
            }
        }
        return successfullyTokenized;
    }
}
