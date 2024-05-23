package com.robinfood.paymentmethodsbc.sample;

import com.robinfood.paymentmethodsbc.dto.api.creditcards.CreateCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.dto.api.creditcards.UpdateCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.dto.steps.CreditCardCreatePipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.CreditCardDeletePipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.CreditCardUpdatePipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.CreditCardUserListPipeDTO;
import com.robinfood.paymentmethodsbc.model.CreditCard;
import com.robinfood.paymentmethodsbc.model.CreditCardType;
import com.robinfood.paymentmethodsbc.model.PaymentGatewayCreditCard;
import com.robinfood.paymentmethodsbc.security.CipherUtility;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Obtiene objetos de prueba para test unitarios
 * @author Hernán A. Ramírez O.
 */
public class CreditCardSamples {

    // Entities
    public static CreditCard getCreditCard(boolean encrypt) {
        CipherUtility cipherUtil = new CipherUtility();

        String number = "4111111111111111";
        String cardHolderName = "Card Holder Name";
        String userIdentificationNumber = "1111222333";
        String userAddress = "Calle siempre viva 123";
        String userCity = "Springfield";

        if (encrypt) {
            number = cipherUtil.encryptTextWithPublicKey(number);
            cardHolderName =
                cipherUtil.encryptTextWithPublicKey(cardHolderName);
            userIdentificationNumber =
                cipherUtil.encryptTextWithPublicKey(userIdentificationNumber);
            userAddress = cipherUtil.encryptTextWithPublicKey(userAddress);
            userCity = cipherUtil.encryptTextWithPublicKey(userCity);
        }
        return CreditCard
            .builder()
            .id(1L)
            .userId(1L)
            .creditCardType(getCreditCardType())
            .country(CountrySample.getCountry())
            .number(number)
            .cardHolderName(cardHolderName)
            .userIdentificationNumber(userIdentificationNumber)
            .userAddress(userAddress)
            .userCity(userCity)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
    }

    public static CreditCard getCreditCardWhitEmail(boolean encrypt) {
        CipherUtility cipherUtil = new CipherUtility();

        String number = "4111111111111111";
        String cardHolderName = "Card Holder Name";
        String userIdentificationNumber = "1111222333";
        String userAddress = "Calle siempre viva 123";
        String userCity = "Springfield";
        String userEmail = "email@example.com";

        if (encrypt) {
            number = cipherUtil.encryptTextWithPublicKey(number);
            cardHolderName =
                cipherUtil.encryptTextWithPublicKey(cardHolderName);
            userIdentificationNumber =
                cipherUtil.encryptTextWithPublicKey(userIdentificationNumber);
            userAddress = cipherUtil.encryptTextWithPublicKey(userAddress);
            userCity = cipherUtil.encryptTextWithPublicKey(userCity);
            userEmail = cipherUtil.encryptTextWithPublicKey(userEmail);
        }
        return CreditCard
            .builder()
            .id(1L)
            .userId(1L)
            .creditCardType(getCreditCardType())
            .country(CountrySample.getCountry())
            .number(number)
            .cardHolderName(cardHolderName)
            .userIdentificationNumber(userIdentificationNumber)
            .userAddress(userAddress)
            .userCity(userCity)
            .userEmail(userEmail)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
    }

    public static CreditCard getCreditCardWhitDifferentEmail(boolean encrypt) {
        CipherUtility cipherUtil = new CipherUtility();

        String number = "4111111111111111";
        String cardHolderName = "Card Holder Name";
        String userIdentificationNumber = "1111222333";
        String userAddress = "Calle siempre viva 123";
        String userCity = "Springfield";
        String userEmail = "differentemail@example.com";

        if (encrypt) {
            number = cipherUtil.encryptTextWithPublicKey(number);
            cardHolderName =
                cipherUtil.encryptTextWithPublicKey(cardHolderName);
            userIdentificationNumber =
                cipherUtil.encryptTextWithPublicKey(userIdentificationNumber);
            userAddress = cipherUtil.encryptTextWithPublicKey(userAddress);
            userCity = cipherUtil.encryptTextWithPublicKey(userCity);
            userEmail = cipherUtil.encryptTextWithPublicKey(userEmail);
        }
        return CreditCard
            .builder()
            .id(1L)
            .userId(1L)
            .creditCardType(getCreditCardType())
            .country(CountrySample.getCountry())
            .number(number)
            .cardHolderName(cardHolderName)
            .userIdentificationNumber(userIdentificationNumber)
            .userAddress(userAddress)
            .userCity(userCity)
            .userEmail(userEmail)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
    }

    public static CreditCard getCreditCardUpdated(boolean encrypt) {
        CipherUtility cipherUtil = new CipherUtility();

        String number = "4111111111111111";
        String cardHolderName = "Card Holder Name";
        String userIdentificationNumber = "1111222333";
        String userAddress = "Calle siempre viva 123";
        String userCity = "Springfield";

        if (encrypt) {
            number = cipherUtil.encryptTextWithPublicKey(number);
            cardHolderName =
                cipherUtil.encryptTextWithPublicKey(cardHolderName);
            userIdentificationNumber =
                cipherUtil.encryptTextWithPublicKey(userIdentificationNumber);
            userAddress = cipherUtil.encryptTextWithPublicKey(userAddress);
            userCity = cipherUtil.encryptTextWithPublicKey(userCity);
        }
        return CreditCard
            .builder()
            .id(1L)
            .userId(1L)
            .creditCardType(getCreditCardType())
            .country(CountrySample.getCountry())
            .number(number)
            .cardHolderName(cardHolderName)
            .userIdentificationNumber(userIdentificationNumber)
            .userAddress(userAddress)
            .userCity(userCity)
            .isUpdated(1)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
    }

    public static CreditCard getCreditCardNotUpdated(boolean encrypt) {
        CipherUtility cipherUtil = new CipherUtility();

        String number = "4111111111111111";
        String cardHolderName = "Card Holder Name";
        String userIdentificationNumber = "1111222333";
        String userAddress = "Calle siempre viva 123";
        String userCity = "Springfield";

        if (encrypt) {
            number = cipherUtil.encryptTextWithPublicKey(number);
            cardHolderName =
                cipherUtil.encryptTextWithPublicKey(cardHolderName);
            userIdentificationNumber =
                cipherUtil.encryptTextWithPublicKey(userIdentificationNumber);
            userAddress = cipherUtil.encryptTextWithPublicKey(userAddress);
            userCity = cipherUtil.encryptTextWithPublicKey(userCity);
        }
        return CreditCard
            .builder()
            .id(1L)
            .userId(1L)
            .creditCardType(getCreditCardType())
            .country(CountrySample.getCountry())
            .number(number)
            .cardHolderName(cardHolderName)
            .userIdentificationNumber(userIdentificationNumber)
            .userAddress(userAddress)
            .userCity(userCity)
            .isUpdated(0)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
    }

    public static CreditCardType getCreditCardType() {
        return CreditCardType
            .builder()
            .id(1L)
            .code("Code")
            .name("Name")
            .build();
    }

    public static List<CreditCard> getCreditCardList() {
        return List.of(getCreditCard(false));
    }

    // Request
    public static CreateCreditCardRequestDTO getCreditCardRequestDTO() {
        return CreateCreditCardRequestDTO
            .builder()
            .id(1L)
            .userId(1L)
            .countryId(1L)
            .number("4111111111111111")
            .cardHolderFirstName("John")
            .cardHolderLastName("Doe")
            .userIdentificationType(1)
            .userIdentificationNumber("111222333")
            .userEmail("email@example.com")
            .userAddress("Calle siempre viva 123")
            .userCity("Springfield")
            .expirationYear("2124")
            .expirationMonth("12")
            .cardVerificationCode("123")
            .build();
    }

    public static CreateCreditCardRequestDTO getCreditCardRequestDTOWithDifferentEmail() {
        return CreateCreditCardRequestDTO
            .builder()
            .id(1L)
            .userId(1L)
            .countryId(1L)
            .number("4111111111111111")
            .cardHolderFirstName("John")
            .cardHolderLastName("Doe")
            .userIdentificationType(1)
            .userIdentificationNumber("111222333")
            .userEmail("differentemail@example.com")
            .userAddress("Calle siempre viva 123")
            .userCity("Springfield")
            .expirationYear("2124")
            .expirationMonth("12")
            .cardVerificationCode("123")
            .build();
    }

    public static CreateCreditCardRequestDTO getCreditCardRequestDTOWhitOutEmail() {
        return CreateCreditCardRequestDTO
            .builder()
            .id(1L)
            .userId(1L)
            .countryId(1L)
            .number("4111111111111111")
            .cardHolderFirstName("John")
            .cardHolderLastName("Doe")
            .userIdentificationType(1)
            .userIdentificationNumber("111222333")
            .userAddress("Calle siempre viva 123")
            .userCity("Springfield")
            .expirationYear("2124")
            .expirationMonth("12")
            .cardVerificationCode("123")
            .build();
    }

    public static UpdateCreditCardRequestDTO getUpdateCreditCardRequestDTO() {
        return UpdateCreditCardRequestDTO
            .builder()
            .id(1L)
            .countryId(1L)
            .number("4111111111111111")
            .cardHolderFirstName("John")
            .cardHolderLastName("Doe")
            .userIdentificationType(1)
            .userIdentificationNumber("111222333")
            .userEmail("differentexample@test.com")
            .userAddress("Calle siempre viva 123")
            .userCity("Springfield")
            .expirationYear("2124")
            .expirationMonth("12")
            .cardVerificationCode("123")
            .build();
    }


    // Pipes
    public static CreditCardCreatePipeDTO getCreditCardCreatePipeDTO() {
        return CreditCardCreatePipeDTO
            .builder()
            .creditCard(getCreditCard(false))
            .creditCardRequestDTO(getCreditCardRequestDTO())
            .generatedTokens(1)
            .build();
    }

    public static CreditCardCreatePipeDTO getCreditCardCreatePipeDTOWhitOutEmail() {
        return CreditCardCreatePipeDTO
            .builder()
            .creditCard(getCreditCard(false))
            .creditCardRequestDTO(getCreditCardRequestDTOWhitOutEmail())
            .generatedTokens(1)
            .build();
    }

    public static CreditCardCreatePipeDTO getCreditCardCreatePipeDTOWhitDefaultEmail() {
        return CreditCardCreatePipeDTO
            .builder()
            .creditCard(getCreditCardWhitEmail(false))
            .creditCardRequestDTO(getCreditCardRequestDTO())
            .generatedTokens(1)
            .build();
    }

    public static CreditCardCreatePipeDTO getCreditCardCreatePipeDTOWhitDifferentEmail() {
        return CreditCardCreatePipeDTO
            .builder()
            .creditCard(getCreditCardWhitDifferentEmail(false))
            .creditCardRequestDTO(getCreditCardRequestDTOWithDifferentEmail())
            .generatedTokens(1)
            .build();
    }

    public static CreditCardCreatePipeDTO getCreditCardCreatePipeDTOUpdated() {
        return CreditCardCreatePipeDTO
            .builder()
            .creditCard(getCreditCardUpdated(false))
            .creditCardRequestDTO(getCreditCardRequestDTO())
            .generatedTokens(1)
            .build();
    }

    public static CreditCardCreatePipeDTO getCreditCardCreatePipeDTONotUpdated() {
        return CreditCardCreatePipeDTO
            .builder()
            .creditCard(getCreditCardNotUpdated(false))
            .creditCardRequestDTO(getCreditCardRequestDTO())
            .generatedTokens(1)
            .build();
    }


    public static CreditCardUpdatePipeDTO getCreditCardUpdatePipeDTO() {
        return CreditCardUpdatePipeDTO
            .builder()
            .creditCard(getCreditCard(false))
            .creditCardId(1L)
            .updateCardRequestDTO(getUpdateCreditCardRequestDTO())
            .build();
    }

    public static CreditCardUpdatePipeDTO getCreditCardUpdatePipeDTO(CreditCard creditCard) {
        return CreditCardUpdatePipeDTO
            .builder()
            .creditCard(creditCard)
            .creditCardId(1L)
            .userId(1L)
            .updateCardRequestDTO(getUpdateCreditCardRequestDTO())
            .build();
    }


    public static CreditCardUpdatePipeDTO getCreditCardUpdatedPipeDTO(
        CreditCard creditCardNew

    ) {
        return CreditCardUpdatePipeDTO
            .builder()
            .creditCard(getCreditCard(true))
            .creditCardId(1L)
            .userId(1L)
            .creditCardNew(creditCardNew)
            .updateCardRequestDTO(getUpdateCreditCardRequestDTO())
            .token("TOKEN_ID")
            .paymentGatewayCreditCardsToDelete(List.of(
                PaymentGatewayCreditCard.builder()
                .id(1L)
                .build()

            ))
            .paymentGatewayId(1L)
            .build();
    }

    public static CreditCardDeletePipeDTO getCreditCardDeletePipeDTO(
        boolean emptyList
    ) {
        List<PaymentGatewayCreditCard> list = new ArrayList<>();
        if (!emptyList) {
            list =
                Arrays.asList(
                    PaymentGatewaySamples.getPaymentGatewayCreditCard()
                );
        }
        return CreditCardDeletePipeDTO
            .builder()
            .creditCardId(1L)
            .userId(1L)
            .creditCard(getCreditCard(false))
            .paymentGatewayCreditCardsToDelete(list)
            .build();
    }

    public static CreditCardUserListPipeDTO getCreditCardUserListPipeDTO() {
        return CreditCardUserListPipeDTO
            .builder()
            .userId(1L)
            .countryId(1L)
            .creditCardList(getCreditCardList())
            .paymentGateway(PaymentGatewaySamples.getPaymentGateway())
            .build();
    }
}
