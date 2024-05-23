package com.robinfood.paymentmethodsbc.services.steps.common;

import com.robinfood.paymentmethodsbc.constants.AppConstants;
import com.robinfood.paymentmethodsbc.dto.api.creditcards.CreateCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.dto.steps.CreditCardCreatePipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.CreditCardDeletePipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.enums.CompanyCreditCard;
import com.robinfood.paymentmethodsbc.enums.StepType;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.mappers.CreditCardMapper;
import com.robinfood.paymentmethodsbc.model.CreditCard;
import com.robinfood.paymentmethodsbc.model.CreditCardType;
import com.robinfood.paymentmethodsbc.repositories.CreditCardTypesRepository;
import com.robinfood.paymentmethodsbc.repositories.CreditCardsRepository;
import com.robinfood.paymentmethodsbc.security.CipherUtility;
import com.robinfood.paymentmethodsbc.services.steps.StepActions;
import com.robinfood.paymentmethodsbc.utils.Utilities;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.robinfood.paymentmethodsbc.constants.CreditCardConstants.CC_ONLY_NUMBERS_REGEX;
import static com.robinfood.paymentmethodsbc.constants.CreditCardConstants.CC_VALID_CVV_REGEX;
import static com.robinfood.paymentmethodsbc.constants.CreditCardConstants.CC_VALID_MONTH_REGEX;
import static com.robinfood.paymentmethodsbc.constants.CreditCardConstants.CC_VALID_YEAR_REGEX;

@Slf4j
@Component
public class GetCreditCardStep implements StepActions {
    private final CipherUtility cipherUtility;
    private final CreditCardsRepository creditCardsRepository;
    private final CreditCardTypesRepository creditCardTypesRepository;

    public GetCreditCardStep(
        CipherUtility cipherUtility,
        CreditCardsRepository creditCardsRepository,
        CreditCardTypesRepository creditCardTypesRepository
    ) {
        this.cipherUtility = cipherUtility;
        this.creditCardsRepository = creditCardsRepository;
        this.creditCardTypesRepository = creditCardTypesRepository;
    }

    @Override
    public void invoke(Object pipe)
        throws PaymentStepException, EntityNotFoundException {
        if (pipe instanceof PaymentPipeDTO) {
            invokeForTransactionCreatePipeDTO((PaymentPipeDTO) pipe);
            return;
        }

        if (pipe instanceof CreditCardCreatePipeDTO) {
            invokeForCreditCardCreatePipeDTO((CreditCardCreatePipeDTO) pipe);
            return;
        }

        if (pipe instanceof CreditCardDeletePipeDTO) {
            invokeForCreditCardDeletePipeDTO((CreditCardDeletePipeDTO) pipe);
            return;
        }

        throw new PaymentStepException(
            StepType.GET_CREDIT_CARD,
            String.format(
                "No action has been taken on %s",
                getClass().getSimpleName()
            )
        );
    }

    public void invokeForTransactionCreatePipeDTO(
        PaymentPipeDTO transactionCreatePipeDTO
    ) throws PaymentStepException, EntityNotFoundException {

        validateCreditCardRequired(transactionCreatePipeDTO);

        CreditCard creditCard = getUserCreditCard(
            transactionCreatePipeDTO
                .getTransactionRequestDTO()
                .getPaymentMethod()
                .getCreditCardId(),
            transactionCreatePipeDTO
                .getTransactionRequestDTO()
                .getUser()
                .getUserId()
        );
        Long creditCardCountryId = creditCard.getCountry().getId();
        if (
            creditCardCountryId.compareTo(
                transactionCreatePipeDTO
                    .getTransactionRequestDTO()
                    .getCountryId()
            ) !=
            0
        ) {
            String errorMessage = String.format(
                "La tarjeta de crédito con countryId %s no puede ser usada para una transacción con countryId %s",
                creditCardCountryId,
                transactionCreatePipeDTO
                    .getTransactionRequestDTO()
                    .getCountryId()
            );

            log.error(errorMessage);
            throw new PaymentStepException(StepType.GET_CREDIT_CARD, errorMessage);
        }

        transactionCreatePipeDTO.setCreditCard(decryptCreditCard(creditCard));
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
                StepType.GET_CREDIT_CARD,
                "credit card identifier is required"
            );
        }
    }

    public void invokeForCreditCardCreatePipeDTO(
        CreditCardCreatePipeDTO creditCardCreatePipeDTO
    ) throws PaymentStepException, EntityNotFoundException {
        validateIfNumberExistsByUserIdAndNumber(
            creditCardCreatePipeDTO.getCreditCardRequestDTO().getUserId(),
            creditCardCreatePipeDTO.getCreditCardRequestDTO().getCountryId(),
            Utilities.getStringLastChars(
                creditCardCreatePipeDTO.getCreditCardRequestDTO().getNumber(),
                AppConstants.CREDIT_CARD_LAST_NUMBER_SIZE
            )
        );

        validateTypeAndExpiration(
            creditCardCreatePipeDTO.getCreditCardRequestDTO()
        );

        validateCardVerificationCode(
            creditCardCreatePipeDTO.getCreditCardRequestDTO()
        );

        CreditCard creditCard = CreditCardMapper.getCreditCard(
            creditCardCreatePipeDTO.getCreditCardRequestDTO()
        );

        CreditCardType creditCardType = getCreditCardTypeByNumber(
            creditCard.getNumber()
        );

        creditCard.setCreditCardType(creditCardType);

        creditCardCreatePipeDTO.setCreditCard(creditCard);
    }

    public void invokeForCreditCardDeletePipeDTO(
        CreditCardDeletePipeDTO creditCardDeletePipeDTO
    ) throws EntityNotFoundException {
        creditCardDeletePipeDTO.setCreditCard(
            getUserCreditCard(
                creditCardDeletePipeDTO.getCreditCardId(),
                creditCardDeletePipeDTO.getUserId()
            )
        );
    }

    private CreditCard getUserCreditCard(Long id, Long userId)
        throws EntityNotFoundException {
        Optional<CreditCard> optional = creditCardsRepository.findByIdAndUserIdAndDeletedAt(
            id, userId, null
        );

        if (optional.isEmpty()) {
            log.error(
                "Credit card with id {} and user_id {} was not found", id, userId
            );
            throw new EntityNotFoundException(
                CreditCard.class.getSimpleName(),
                String.valueOf(id)
            );
        }

        return optional.get();
    }

    private CreditCardType getCreditCardTypeByNumber(
        String creditCardNumber
    ) throws EntityNotFoundException, PaymentStepException {
        CompanyCreditCard companyCreditCard = CompanyCreditCard.getCreditCardCompanyForNumber(
            creditCardNumber
        );

        if (companyCreditCard == null) {
            throw new PaymentStepException(
                StepType.GET_CREDIT_CARD,
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

    private void validateCardVerificationCode(
        CreateCreditCardRequestDTO creditCardRequestDTO
    ) throws PaymentStepException {
        String verificationCode = creditCardRequestDTO.getCardVerificationCode();

        if (!CC_VALID_CVV_REGEX.matcher(verificationCode).matches()) {
            throw new PaymentStepException(
                StepType.GET_CREDIT_CARD,
                "El código de verificación debe ser un número de 3 o 4 dígitos"
            );
        }
    }

    private void validateTypeAndExpiration(
        CreateCreditCardRequestDTO creditCardRequestDTO
    )
        throws PaymentStepException {
        if (
            !CC_ONLY_NUMBERS_REGEX
                .matcher(creditCardRequestDTO.getNumber())
                .matches()
        ) {
            throw new PaymentStepException(
                StepType.GET_CREDIT_CARD,
                "El numero de tarjeta de crédito solo puede contener caracteres numéricos"
            );
        }

        if (
            !CC_VALID_MONTH_REGEX
                .matcher(creditCardRequestDTO.getExpirationMonth())
                .matches()
        ) {
            throw new PaymentStepException(
                StepType.GET_CREDIT_CARD,
                "El mes de expiración debe ser un número entre 1 y 12"
            );
        }

        if (
            !CC_VALID_YEAR_REGEX
                .matcher(creditCardRequestDTO.getExpirationYear())
                .matches()
        ) {
            throw new PaymentStepException(
                StepType.GET_CREDIT_CARD,
                "El año de expiración debe ser un número un valor de 4 caracteres numéricos"
            );
        }

        int diffMonths = YearMonth
            .from(
                LocalDate.of(
                    Integer.valueOf(creditCardRequestDTO.getExpirationYear()),
                    Integer.valueOf(creditCardRequestDTO.getExpirationMonth()),
                    1
                )
            )
            .compareTo(YearMonth.now());

        if (diffMonths < 0) {
            throw new PaymentStepException(
                StepType.GET_CREDIT_CARD,
                "La tarjeta de crédito se encuentra expirada"
            );
        }
    }

    void validateIfNumberExistsByUserIdAndNumber(
        Long userId,
        Long countryId,
        String number
    ) throws PaymentStepException {
        List<CreditCard> list = creditCardsRepository.findByUserIdAndCountryIdAndDeletedAt(
            userId, countryId, null
        );
        for (CreditCard creditCard : list) {
            String decrypted = cipherUtility.decryptTextWithPrivateKey(
                creditCard.getNumber()
            );
            if (number.equals(decrypted)) {
                throw new PaymentStepException(
                    StepType.GET_CREDIT_CARD,
                    String.format(
                        "Ya existe una tarjeta terminada en '%s' para este usuario",
                        number
                    )
                );
            }
        }
    }

    private CreditCard decryptCreditCard(CreditCard creditCard) {
        creditCard.setNumber(
            cipherUtility.decryptTextWithPrivateKey(creditCard.getNumber())
        );
        creditCard.setCardHolderName(
            cipherUtility.decryptTextWithPrivateKey(creditCard.getCardHolderName())
        );
        creditCard.setUserIdentificationNumber(
            cipherUtility.decryptTextWithPrivateKey(creditCard.getUserIdentificationNumber())
        );
        creditCard.setUserAddress(
            cipherUtility.decryptTextWithPrivateKey(creditCard.getUserAddress())
        );
        creditCard.setUserCity(
            cipherUtility.decryptTextWithPrivateKey(creditCard.getUserCity())
        );
        creditCard.setUserEmail(
            cipherUtility.decryptTextWithPrivateKey(creditCard.getUserEmail())
        );
        
        return creditCard;
    }
}
