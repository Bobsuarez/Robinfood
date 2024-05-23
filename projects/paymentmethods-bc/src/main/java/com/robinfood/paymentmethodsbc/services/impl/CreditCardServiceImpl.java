package com.robinfood.paymentmethodsbc.services.impl;

import com.robinfood.paymentmethodsbc.dto.api.creditcards.CreateCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.dto.api.creditcards.UpdateCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.dto.steps.CreditCardCreatePipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.CreditCardDeletePipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.CreditCardUpdatePipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.CreditCardUserListPipeDTO;
import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import com.robinfood.paymentmethodsbc.exceptions.DecryptException;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.model.CreditCard;
import com.robinfood.paymentmethodsbc.model.GeneralPaymentMethod;
import com.robinfood.paymentmethodsbc.model.PaymentGateway;
import com.robinfood.paymentmethodsbc.repositories.GeneralPaymentMethodsRepository;
import com.robinfood.paymentmethodsbc.security.CipherUtility;
import com.robinfood.paymentmethodsbc.services.CreditCardService;
import com.robinfood.paymentmethodsbc.utils.StepsControl;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.robinfood.paymentmethodsbc.constants.CreditCardConstants.DEFAULT_EMAIL;
import static com.robinfood.paymentmethodsbc.services.steps.StepGroupDefinitions.CREATE_CREDITCARD_STEPS;
import static com.robinfood.paymentmethodsbc.services.steps.StepGroupDefinitions.UPDATE_CREDITCARD_STEPS;
import static com.robinfood.paymentmethodsbc.services.steps.StepGroupDefinitions.DELETE_CREDITCARD_STEPS;
import static com.robinfood.paymentmethodsbc.services.steps.StepGroupDefinitions.USERLIST_CREDITCARD_STEPS;
import static com.robinfood.paymentmethodsbc.utils.JsonUtils.convertToJson;

@Slf4j
@Service
public class CreditCardServiceImpl implements CreditCardService {
    private final CipherUtility cipherUtility;
    private final GeneralPaymentMethodsRepository generalPaymentMethodsRepository;

    public CreditCardServiceImpl(
        CipherUtility cipherUtility,
        GeneralPaymentMethodsRepository generalPaymentMethodsRepository
    ) {
        this.cipherUtility = cipherUtility;
        this.generalPaymentMethodsRepository = generalPaymentMethodsRepository;
    }

    @Override
    public List<CreditCard> findAllByUserAndCountryAndPaymentMethodId(
        Long userId,
        Long countryId,
        Long generalPaymentMethodId
    ) throws EntityNotFoundException, PaymentStepException, BaseException {
        log.info(
            "findAllByUserAndCountry() request data: userId={}, countryId={}, generalPaymentMethodId={}",
            userId, countryId, generalPaymentMethodId
        );

        GeneralPaymentMethod paymentMethod = getGeneralPaymentMethod(generalPaymentMethodId);

        CreditCardUserListPipeDTO pipe = CreditCardUserListPipeDTO
            .builder()
            .userId(userId)
            .countryId(countryId)
            .generalPaymentMethodId(generalPaymentMethodId)
            .paymentGateway(paymentMethod.getPaymentGateway())
            .build();

        StepsControl.validateSteps(pipe, USERLIST_CREDITCARD_STEPS);

        List<CreditCard> response = pipe.getCreditCardList();

        int size = 0;
        if (Objects.nonNull(response)) {
            size = response.size();
        }

        log.info(
            "User with id {} has {} credit cards in country with id {}",
            pipe.getUserId(), size, pipe.getCountryId()
        );

        log.info("findAllByUserAndCountry() response data: {}", response);

        return response;
    }

    @Override
    @Transactional
    public CreditCard create(
        CreateCreditCardRequestDTO creditCardRequestDTO
    ) throws BaseException, EntityNotFoundException, PaymentStepException {
        log.info("create() request data: {}", convertToJson(creditCardRequestDTO));
        decryptDTOAttributes(creditCardRequestDTO);

        log.info(
            "create() request data (decrypted and masked): {}", creditCardRequestDTO
        );

        CreditCardCreatePipeDTO pipe = CreditCardCreatePipeDTO
            .builder()
            .creditCardRequestDTO(creditCardRequestDTO)
            .build();

        StepsControl.validateSteps(pipe, CREATE_CREDITCARD_STEPS);

        if (pipe.getGeneratedTokens() == 0) {
            log.error("Credit card tokenization was not posible for any provider");
            throw new BaseException(
                "It is not possible to create the credit card, validate the information and try again."
            );
        }

        CreditCard response = pipe.getCreditCard();

        log.info("Credit card tokenization was posible for {} providers", pipe.getGeneratedTokens());

        log.info("create() response data: {}", response);

        return response;
    }

    @Override
    @Transactional
    public String delete(Long userId, Long creditCardId) throws PaymentStepException, EntityNotFoundException {
        log.info(
            "delete() request data: userId={}, creditCardId={}",
            userId, creditCardId
        );

        CreditCardDeletePipeDTO pipe = CreditCardDeletePipeDTO
            .builder()
            .userId(userId)
            .creditCardId(creditCardId)
            .paymentGatewayCreditCardsToDelete(new ArrayList<>())
            .build();

        StepsControl.validateSteps(pipe, DELETE_CREDITCARD_STEPS);

        String response = "Credit Card successfully deleted";

        log.info("delete() response data: {}", response);

        return response;
    }

    // Funciones de gestión

    private void decryptDTOAttributes(
        CreateCreditCardRequestDTO creditCardRequestDTO
    ) {

        validateAndSetEmail(creditCardRequestDTO);

        String userIdNumber = Optional.ofNullable(
            creditCardRequestDTO.getUserIdentificationNumber()
        ).orElse(
            Optional.ofNullable(creditCardRequestDTO.getUserId()).orElse(-1L).toString()
        );

        userIdNumber = decryptAndValidateAttribute("userIdentificationNumber", userIdNumber);

        if (StringUtils.isBlank(userIdNumber)) {
            userIdNumber = Optional.ofNullable(creditCardRequestDTO.getUserId()).orElse(-1L).toString();

            log.info(String.format("Setting '%s' as userIdentificationNumber", userIdNumber));
        }

        creditCardRequestDTO.setUserIdentificationNumber(userIdNumber);

        String verificationCode = creditCardRequestDTO.getCardVerificationCode();
        if (StringUtils.isNotBlank(verificationCode)) {
            creditCardRequestDTO.setCardVerificationCode(
                decryptAndValidateAttribute(
                    "cardVerificationCode",
                    creditCardRequestDTO.getCardVerificationCode()
                )
            );
        }

        creditCardRequestDTO.setNumber(
            decryptAndValidateAttribute(
                "number", creditCardRequestDTO.getNumber()
            )
        );
        creditCardRequestDTO.setCardHolderFirstName(
            decryptAndValidateAttribute(
                "cardHolderFirstName", creditCardRequestDTO.getCardHolderFirstName()
            )
        );
        creditCardRequestDTO.setCardHolderLastName(
            decryptAndValidateAttribute(
                "cardHolderLastName", creditCardRequestDTO.getCardHolderLastName()
            )
        );
        creditCardRequestDTO.setExpirationYear(
            decryptAndValidateAttribute(
                "expirationYear", creditCardRequestDTO.getExpirationYear()
            )
        );
        creditCardRequestDTO.setExpirationMonth(
            decryptAndValidateAttribute(
                "expirationMonth", creditCardRequestDTO.getExpirationMonth()
            )
        );
        creditCardRequestDTO.setUserAddress(
            decryptAndValidateAttribute(
                "userAddress", creditCardRequestDTO.getUserAddress()
            )
        );
        creditCardRequestDTO.setUserCity(
            decryptAndValidateAttribute(
                "userCity", creditCardRequestDTO.getUserCity()
            )
        );

        creditCardRequestDTO.setDecrypted(true);
    }

    private void validateAndSetEmail(CreateCreditCardRequestDTO creditCardRequestDTO) {

        creditCardRequestDTO.setUserEmail(Optional.ofNullable(creditCardRequestDTO.getUserEmail())
            .map((String email) ->
                decryptAndValidateAttribute("userEmail", email)
            ).orElse(DEFAULT_EMAIL));
    }

    private String decryptAndValidateAttribute(String name, String value) {

        String decrypted = cipherUtility.decryptTextWithPrivateKey(value);
        return Optional.ofNullable(decrypted).orElseThrow(() -> new DecryptException(
            String.format("Validate field encryption %s", name)
        ));
    }

    private GeneralPaymentMethod getGeneralPaymentMethod(
        Long generalPaymentMethodId
    ) throws EntityNotFoundException, BaseException {
        Optional<GeneralPaymentMethod> optional = generalPaymentMethodsRepository.findByIdAndStatus(
            generalPaymentMethodId, GeneralPaymentMethod.STATUS_ENABLED
        );

        if (optional.isEmpty()) {
            throw new EntityNotFoundException(
                GeneralPaymentMethod.class.getSimpleName(),
                String.valueOf(generalPaymentMethodId)
            );
        }

        GeneralPaymentMethod paymentMethod = optional.get();

        PaymentGateway paymentGateway = paymentMethod.getPaymentGateway();

        if (Objects.isNull(paymentGateway)) {
            throw new BaseException(
                String.format(
                    "No valid gateway found for the payment method with id %s",
                    paymentMethod.getName()
                )
            );
        }

        return paymentMethod;
    }



    @Override
    @Transactional
    public CreditCard update(
        Long userId, 
        Long creditCardId, 
        UpdateCreditCardRequestDTO updateCreditCardRequestDTO
    ) throws PaymentStepException, EntityNotFoundException {
        
        log.info("UPDATE_CREDIT_CARD_REQUESTED : {}", convertToJson(updateCreditCardRequestDTO));
        updateCreditCardRequestDTO.setUserId(userId);
        CreditCardUpdatePipeDTO pipe = CreditCardUpdatePipeDTO
            .builder()
            .userId(userId)
            .creditCardId(creditCardId)
            .updateCardRequestDTO(updateCreditCardRequestDTO)
            .build();

        StepsControl.validateSteps(pipe, UPDATE_CREDITCARD_STEPS);

        CreditCard response = pipe.getCreditCardNew();
        log.info("UPDATE_CREDIT_CARD_SUCCESSFULLY Response data: {}", response);
        return response;
    }
}
