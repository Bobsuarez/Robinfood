package com.robinfood.paymentmethodsbc.services.steps.creditcards;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Component;
import com.robinfood.paymentmethodsbc.dto.api.creditcards.UpdateCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.dto.steps.CreditCardUpdatePipeDTO;
import com.robinfood.paymentmethodsbc.enums.CompanyCreditCard;
import com.robinfood.paymentmethodsbc.enums.StepType;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.mappers.CreditCardMapper;
import com.robinfood.paymentmethodsbc.model.CreditCard;
import com.robinfood.paymentmethodsbc.model.CreditCardType;
import com.robinfood.paymentmethodsbc.model.PaymentGatewayCreditCard;
import com.robinfood.paymentmethodsbc.repositories.CreditCardTypesRepository;
import com.robinfood.paymentmethodsbc.repositories.CreditCardsRepository;
import com.robinfood.paymentmethodsbc.repositories.PaymentGatewayCreditCardsRepository;
import com.robinfood.paymentmethodsbc.repositories.PaymentGatewaySettingsCachedRepository;
import com.robinfood.paymentmethodsbc.services.steps.StepActions;
import com.robinfood.paymentmethodsbc.utils.CreditCardUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BuildUpdateCreditCardStep implements StepActions {

    private final CreditCardsRepository creditCardsRepository;
    private final PaymentGatewaySettingsCachedRepository settingsCachedRepository;
    private final PaymentGatewayCreditCardsRepository paymentGatewayCreditCardsRepository;
    private final CreditCardTypesRepository creditCardTypesRepository;

    private List<PaymentGatewayCreditCard> paymentGatewayCreditCardsToDelete;
    private Long currentPaymentGatewayId;

    public BuildUpdateCreditCardStep(
        CreditCardsRepository creditCardsRepository,
        PaymentGatewaySettingsCachedRepository settingsCachedRepository,
        PaymentGatewayCreditCardsRepository paymentGatewayCreditCardsRepository,
        CreditCardTypesRepository creditCardTypesRepository
    ){
        this.settingsCachedRepository = settingsCachedRepository;
        this.paymentGatewayCreditCardsRepository = paymentGatewayCreditCardsRepository;
        this.creditCardsRepository = creditCardsRepository;
        this.creditCardTypesRepository = creditCardTypesRepository;

    }

    @Override
    public void invoke(Object pipe) throws PaymentStepException, EntityNotFoundException {
        if (pipe instanceof CreditCardUpdatePipeDTO) {
            paymentGatewayCreditCardsToDelete = new ArrayList<>();
            currentPaymentGatewayId = 0L;
            CreditCardUpdatePipeDTO creditCardUpdatePipeDTO = (CreditCardUpdatePipeDTO) pipe;
            creditCardUpdatePipeDTO.setCreditCard(
                getCreditCard(creditCardUpdatePipeDTO)
            );
            UpdateCreditCardRequestDTO updateCreditCardRequestDTO = creditCardUpdatePipeDTO.getUpdateCardRequestDTO();
            validateCreditCard( updateCreditCardRequestDTO);

            CreditCard creditCardCurrent = creditCardUpdatePipeDTO.getCreditCard(); 
            CreditCardType creditCardType = getCreditCardTypeByNumber(
                updateCreditCardRequestDTO.getNumber()
            );            
            

            creditCardUpdatePipeDTO.setCreditCardNew(
                CreditCardMapper.getUpdatedCreditCard(
                    updateCreditCardRequestDTO
                )
            );
            creditCardUpdatePipeDTO.getCreditCardNew().setCreditCardType(creditCardType);

            Long countryId = creditCardCurrent.getCountry().getId();
            Long creditCardId = creditCardCurrent.getId();
            String currentCreditCardToken = getCurrentCreditCartToken(creditCardId, countryId);
            creditCardUpdatePipeDTO.setPaymentGatewayCreditCardsToDelete(paymentGatewayCreditCardsToDelete);
            creditCardUpdatePipeDTO.setToken(currentCreditCardToken);
            creditCardUpdatePipeDTO.setPaymentGatewayId(currentPaymentGatewayId);
            return;
        }

        throw new PaymentStepException(
            StepType.BUILD_UPDATE_CREDIT_CARD,
            String.format(
                "No action has been taken on %s",
                getClass().getSimpleName()
            )
        );
    }

    public CreditCard getCreditCard(
        CreditCardUpdatePipeDTO creditCardUpdatePipeDTO
    ) throws EntityNotFoundException {
        return getUserCreditCard(
            creditCardUpdatePipeDTO.getCreditCardId(),
            creditCardUpdatePipeDTO.getUserId()
        );
    }

    private CreditCard getUserCreditCard(
        Long id, 
        Long userId
    ) throws EntityNotFoundException {
        return creditCardsRepository.findByIdAndUserIdAndDeletedAt(
            id,
            userId,
            null
        ).orElseThrow(()->
            new EntityNotFoundException(
                CreditCard.class.getSimpleName(),
                String.valueOf(id)
            )
        );
    }

    private void validateCreditCard(UpdateCreditCardRequestDTO creditCardRequestDTO) throws PaymentStepException{
        String verificationCode = CreditCardUtils.decryptTextWithPrivateKey(
            creditCardRequestDTO.getCardVerificationCode()
        );

        String expirationMonth  = CreditCardUtils.decryptTextWithPrivateKey(
            creditCardRequestDTO.getExpirationMonth()
        );

        String expirationYear  = CreditCardUtils.decryptTextWithPrivateKey(
            creditCardRequestDTO.getExpirationYear()
        );

        CreditCardUtils.validCvv(verificationCode);
        CreditCardUtils.validExpirationDate(
            expirationMonth, 
            expirationYear
        );
    }

    private String getCurrentCreditCartToken(Long currentCreditCardId, Long countryId) throws PaymentStepException{
        Map<Long, Map<String, String>> paymentGatewaySettingsMap =
            settingsCachedRepository.getPaymentGatewaySettingsByCountryForTokenization(
                countryId
            );

        Long paymentGatewayId = paymentGatewaySettingsMap
            .entrySet()
            .stream()
            .findFirst()
            .orElseThrow(() ->
                new PaymentStepException(
                    StepType.BUILD_UPDATE_CREDIT_CARD,
                    String.format(
                        "Credit Card Payment Settings by Country ID %s not found",
                        countryId
                    )
                )
            )
            .getKey();

        return Optional.ofNullable(
            validateCreditCardToken(currentCreditCardId, paymentGatewayId)
        ).orElseThrow(()->
            new PaymentStepException(
                StepType.BUILD_UPDATE_CREDIT_CARD,
                String.format(
                    "Cant Get token for Credit Card ID %s", 
                    currentCreditCardId
                )
            )
        );
        
    }

    private String validateCreditCardToken(
        Long creditCardId,
        Long paymentGatewayId
    ) {
        String decryptedToken = null;
        Optional<PaymentGatewayCreditCard> optional;
        PaymentGatewayCreditCard infoToken;

        optional = paymentGatewayCreditCardsRepository.findByCreditCardIdAndPaymentGatewayId(
            creditCardId, 
            paymentGatewayId
        );

        if (optional.isEmpty()) {
            log.warn(
                "There is no credit card id {} and payment gateway id {}",
                creditCardId, paymentGatewayId
            );
            
        } else {
            infoToken = optional.get();
            paymentGatewayCreditCardsToDelete.add(infoToken);
            currentPaymentGatewayId = paymentGatewayId;
            decryptedToken = CreditCardUtils.decryptTextWithPrivateKey(infoToken.getToken());
        }
        return decryptedToken;
    }


    private CreditCardType getCreditCardTypeByNumber(
        String creditCardNumber
    ) throws EntityNotFoundException, PaymentStepException {
        creditCardNumber = CreditCardUtils.decryptTextWithPrivateKey(creditCardNumber);
        CompanyCreditCard companyCreditCard = CompanyCreditCard.getCreditCardCompanyForNumber(
            creditCardNumber
        );

        if (companyCreditCard == null) {
            throw new PaymentStepException(
                StepType.BUILD_UPDATE_CREDIT_CARD,
                "Valide el número de la tarjeta"
            );
        }

        Optional<CreditCardType> creditCardType = creditCardTypesRepository.findByCode(
            companyCreditCard.getName()
        );
        if (creditCardType.isEmpty()) {
            throw new EntityNotFoundException(
                CreditCardType.class.getSimpleName(),
                String.valueOf(companyCreditCard.getName())
            );
        }

        return creditCardType.get();
    }

}
