package com.robinfood.paymentmethodsbc.services.steps.creditcards;

import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Component;
import com.robinfood.paymentmethodsbc.components.providers.BCICreditCardProvider;
import com.robinfood.paymentmethodsbc.constants.AppConstants;
import com.robinfood.paymentmethodsbc.dto.CreditCardTokenDTO;
import com.robinfood.paymentmethodsbc.dto.api.creditcards.UpdateCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.dto.steps.CreditCardUpdatePipeDTO;
import com.robinfood.paymentmethodsbc.enums.StepType;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentMethodsException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.mappers.CreditCardMapper;
import com.robinfood.paymentmethodsbc.model.CreditCard;
import com.robinfood.paymentmethodsbc.repositories.PaymentGatewaySettingsCachedRepository;
import com.robinfood.paymentmethodsbc.services.steps.StepActions;
import com.robinfood.paymentmethodsbc.utils.CreditCardUtils;
import com.robinfood.paymentmethodsbc.utils.Utilities;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UpdateTokenizeCreditCardStep implements StepActions {
    private final PaymentGatewaySettingsCachedRepository settingsCachedRepository;
    private final BCICreditCardProvider creditCardProvider;

    public UpdateTokenizeCreditCardStep(
        PaymentGatewaySettingsCachedRepository settingsCachedRepository,
        BCICreditCardProvider creditCardProvider
    ) {
        this.settingsCachedRepository = settingsCachedRepository;
        this.creditCardProvider = creditCardProvider;
    }

    @Override
    public void invoke(Object pipe) throws PaymentStepException, EntityNotFoundException {
        if (pipe instanceof CreditCardUpdatePipeDTO) {

            CreditCardUpdatePipeDTO creditCardUpdatePipeDTO = (CreditCardUpdatePipeDTO) pipe;
            log.info(
                "Start UPDATE_TOKEN_CREDIT_CARD_STEP at BCI for USER_ID:{}", 
                creditCardUpdatePipeDTO.getUserId()
            );

            try {
                CreditCard decryptNewCreditCard = decryptCreditCard(
                    creditCardUpdatePipeDTO.getCreditCardNew()
                );

                CreditCardTokenDTO creditCardTokenDTO = getCreditCardTokenDTO(
                    creditCardUpdatePipeDTO.getUpdateCardRequestDTO(),
                    decryptNewCreditCard
                );

                String token = creditCardUpdateTokenize(
                    creditCardTokenDTO, 
                    decryptNewCreditCard, 
                    creditCardUpdatePipeDTO.getToken()
                );

                creditCardUpdatePipeDTO.setToken(token);

                log.info(
                    "Successfull UPDATE_TOKEN_CREDIT_CARD_STEP at BCI for USER_ID:{}", 
                    creditCardUpdatePipeDTO.getUserId()
                );
                
            } catch (Exception e) {
                log.error("{}",e);
                throw new PaymentStepException(
                    StepType.UPDATE_TOKENIZE_CREDIT_CARD,
                    String.format( 
                        "Error UPDATE_CREADIT_CARD - Error: %s",e.getMessage() 
                    )
                );
            }

            return ;
        
        }


        throw new PaymentStepException(
            StepType.UPDATE_TOKENIZE_CREDIT_CARD,
            String.format(
                "No action has been taken on %s",
                getClass().getSimpleName()
            )
        );
    }

    
    private CreditCard decryptCreditCard(CreditCard creditCard) {



        creditCard.setNumber(
            CreditCardUtils.decryptTextWithPrivateKey(creditCard.getNumber())
        );
        creditCard.setCardHolderName(
            CreditCardUtils.decryptTextWithPrivateKey(
                creditCard.getCardHolderName()
            )
        );
        creditCard.setUserIdentificationNumber(
            CreditCardUtils.decryptTextWithPrivateKey(
                creditCard.getUserIdentificationNumber()
            )
        );
        creditCard.setUserAddress(
            CreditCardUtils.decryptTextWithPrivateKey(creditCard.getUserAddress())
        );
        creditCard.setUserCity(
            CreditCardUtils.decryptTextWithPrivateKey(creditCard.getUserCity())
        );

        creditCard.setUserEmail(
            CreditCardUtils.decryptTextWithPrivateKey(creditCard.getUserEmail())
        );

        creditCard.setExpirationMonth(
            CreditCardUtils.decryptTextWithPrivateKey(creditCard.getExpirationMonth())
        );

        creditCard.setExpirationYear(
            CreditCardUtils.decryptTextWithPrivateKey(creditCard.getExpirationYear())
        );

        return creditCard;
    }


    private String creditCardUpdateTokenize(
        CreditCardTokenDTO creditCardTokenDTO,
        CreditCard creditCard,
        String currentToken
    ) throws PaymentMethodsException, PaymentStepException {
        Map<Long, Map<String, String>> map = settingsCachedRepository.getPaymentGatewaySettingsByCountryForTokenization(
            creditCard.getCountry().getId()
        );
        String token = null;
        for (Map.Entry<Long, Map<String, String>> entry : map.entrySet()) {
            Map<String, String> config = entry.getValue();
            Map<String, String> orchConfig = settingsCachedRepository.getSettingsOrchestratorPaymentGateway(
                creditCard.getCountry().getId(),
                Utilities.stringToLong(
                    config.get(AppConstants.ORCHESTRATOR_ID_SETTINGS_NAME)
                )
            );

            token = creditCardProvider.updateToken(
                config,
                orchConfig,
                creditCardTokenDTO,
                currentToken
            );
        }

        return Optional.ofNullable(token).orElseThrow(() -> 
            new PaymentStepException(
                StepType.UPDATE_TOKENIZE_CREDIT_CARD,
                "Error UPDATE_CREADIT_CARD - Can't generate token"
            )
        );        
    }


    private CreditCardTokenDTO getCreditCardTokenDTO(
        UpdateCreditCardRequestDTO updateCreditCardRequestDTO,
        CreditCard creditCard
    ){

        updateCreditCardRequestDTO.setCardHolderFirstName(
            CreditCardUtils.decryptTextWithPrivateKey(
                updateCreditCardRequestDTO.getCardHolderFirstName()
            )
        );
        updateCreditCardRequestDTO.setCardHolderLastName(
            CreditCardUtils.decryptTextWithPrivateKey(
                updateCreditCardRequestDTO.getCardHolderLastName()
            )
        );

        updateCreditCardRequestDTO.setCardVerificationCode(
            CreditCardUtils.decryptTextWithPrivateKey(
                updateCreditCardRequestDTO.getCardVerificationCode()
            )
        );

        return CreditCardMapper.getCreditCardTokenDTO(
                    updateCreditCardRequestDTO,
                    creditCard
        );
    }
}
