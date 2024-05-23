package com.robinfood.paymentmethodsbc.mappers;

import com.robinfood.paymentmethodsbc.dto.CreditCardTokenDTO;
import com.robinfood.paymentmethodsbc.dto.api.creditcards.CreateCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.dto.api.creditcards.CreditCardDetailDTO;
import com.robinfood.paymentmethodsbc.dto.bci.BCISettingsDTO;
import com.robinfood.paymentmethodsbc.dto.bci.creditcard.BCICreateCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.dto.bci.creditcard.BCIDeleteCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.dto.api.creditcards.UpdateCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.enums.CompanyCreditCard;
import com.robinfood.paymentmethodsbc.model.Country;
import com.robinfood.paymentmethodsbc.model.CreditCard;

import static com.robinfood.paymentmethodsbc.constants.CreditCardConstants.DEFAULT_EMAIL;
import static com.robinfood.paymentmethodsbc.constants.CreditCardConstants.DEFAULT_IDENTIFICATION_TYPE;
import static java.util.function.Predicate.not;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.Map;

public final class CreditCardMapper {

    private CreditCardMapper() {}

    public static CreditCard getCreditCard(
        CreateCreditCardRequestDTO createCreditCardRequestDTO
    ) {
        return CreditCard
            .builder()
            .id(createCreditCardRequestDTO.getId())
            .userId(createCreditCardRequestDTO.getUserId())
            .country(new Country(createCreditCardRequestDTO.getCountryId()))
            .number(createCreditCardRequestDTO.getNumber())
            .cardHolderName(
                new StringBuilder()
                    .append(createCreditCardRequestDTO.getCardHolderFirstName())
                    .append(" ")
                    .append(createCreditCardRequestDTO.getCardHolderLastName())
                    .toString()
            )
            .userIdentificationType(
                getIdentificationType(createCreditCardRequestDTO.getUserIdentificationType())
            )
            .userIdentificationNumber(
                getIdentificationNumber(createCreditCardRequestDTO)
            )
            .userEmail(getIEmail(createCreditCardRequestDTO.getUserEmail()))
            .expirationMonth(createCreditCardRequestDTO.getExpirationMonth())
            .expirationYear(createCreditCardRequestDTO.getExpirationYear())
            .userAddress(createCreditCardRequestDTO.getUserAddress())
            .userCity(createCreditCardRequestDTO.getUserCity())
            .build();
    }

    public static CreditCardDetailDTO getCreditCardDetailDTO(
        CreditCard creditCard
    ) {
        CreditCardDetailDTO.CreditCardTypeDTO type = null;
        if (creditCard.getCreditCardType() != null) {
            type =
                CreditCardDetailDTO
                    .CreditCardTypeDTO.builder()
                    .id(creditCard.getCreditCardType().getId())
                    .name(creditCard.getCreditCardType().getName())
                    .code(creditCard.getCreditCardType().getCode())
                    .build();
        }

        CreditCardDetailDTO.CountryDTO country = null;
        if (creditCard.getCountry() != null) {
            country =
                CreditCardDetailDTO
                    .CountryDTO.builder()
                    .id(creditCard.getCountry().getId())
                    .uuid(creditCard.getCountry().getUuid())
                    .name(creditCard.getCountry().getName())
                    .build();
        }

        return CreditCardDetailDTO
            .builder()
            .id(creditCard.getId())
            .userId(creditCard.getUserId())
            .creditCardType(type)
            .country(country)
            .number(creditCard.getNumber())
            .cardHolderName(creditCard.getCardHolderName())
            .userIdentificationType(creditCard.getUserIdentificationType())
            .userIdentificationNumber(creditCard.getUserIdentificationNumber())
            .userEmail(creditCard.getUserEmail())
            .userAddress(creditCard.getUserAddress())
            .userCity(creditCard.getUserCity())
            .isUpdated(isCreditCardUpdated(creditCard.getIsUpdated()))
            .createdAt(creditCard.getCreatedAt().atOffset(ZoneOffset.UTC))
            .updatedAt(creditCard.getUpdatedAt().atOffset(ZoneOffset.UTC))
            .build();
    }

    public static CreditCardTokenDTO getCreditCardTokenDTO(
        CreateCreditCardRequestDTO creditCardDTO
    ) {
        CompanyCreditCard companyCreditCard = CompanyCreditCard.getCreditCardCompanyForNumber(
            creditCardDTO.getNumber()
        );

        return CreditCardTokenDTO
            .builder()
            .payerFirstName(creditCardDTO.getCardHolderFirstName())
            .payerLastName(creditCardDTO.getCardHolderLastName())
            .payerId(String.valueOf(creditCardDTO.getUserId()))
            .payerIdentificationType(creditCardDTO.getUserIdentificationType())
            .payerIdentification(creditCardDTO.getUserIdentificationNumber())
            .payerEmail(creditCardDTO.getUserEmail())
            .creditCardNumber(creditCardDTO.getNumber())
            .creditCardExpirationYear(creditCardDTO.getExpirationYear())
            .creditCardExpirationMonth(creditCardDTO.getExpirationMonth())
            .creditCardVerificationCode(creditCardDTO.getCardVerificationCode())
            .companyCreditCard(companyCreditCard)
            .build();
    }


    public static CreditCardTokenDTO getCreditCardTokenDTO(
        UpdateCreditCardRequestDTO creditCardDTO
    ) {
        CompanyCreditCard companyCreditCard = CompanyCreditCard.getCreditCardCompanyForNumber(
            creditCardDTO.getNumber()
        );

        return CreditCardTokenDTO
            .builder()
            .payerFirstName(creditCardDTO.getCardHolderFirstName())
            .payerLastName(creditCardDTO.getCardHolderLastName())
            .payerId(String.valueOf(creditCardDTO.getUserId()))
            .payerIdentificationType(creditCardDTO.getUserIdentificationType())
            .payerIdentification(creditCardDTO.getUserIdentificationNumber())
            .payerEmail(creditCardDTO.getUserEmail())
            .creditCardNumber(creditCardDTO.getNumber())
            .creditCardExpirationYear(creditCardDTO.getExpirationYear())
            .creditCardExpirationMonth(creditCardDTO.getExpirationMonth())
            .creditCardVerificationCode(creditCardDTO.getCardVerificationCode())
            .companyCreditCard(companyCreditCard)
            .build();
    }


    public static CreditCardTokenDTO getCreditCardTokenDTO(
        UpdateCreditCardRequestDTO updateCreditCardRequestDTO,
        CreditCard creditCard
    ) {
        CompanyCreditCard companyCreditCard = CompanyCreditCard.getCreditCardCompanyForNumber(
            creditCard.getNumber()
        );

        return CreditCardTokenDTO
            .builder()
            .payerFirstName(updateCreditCardRequestDTO.getCardHolderFirstName())
            .payerLastName(updateCreditCardRequestDTO.getCardHolderLastName())
            .payerId(String.valueOf(creditCard.getUserId()))
            .payerIdentificationType(creditCard.getUserIdentificationType())
            .payerIdentification(creditCard.getUserIdentificationNumber())
            .payerEmail(creditCard.getUserEmail())
            .creditCardNumber(creditCard.getNumber())
            .creditCardExpirationYear(creditCard.getExpirationYear())
            .creditCardExpirationMonth(creditCard.getExpirationMonth())
            .creditCardVerificationCode(updateCreditCardRequestDTO.getCardVerificationCode())
            .companyCreditCard(companyCreditCard)
            .build();
    }


    public static CreditCard getUpdatedCreditCard(
        UpdateCreditCardRequestDTO updateCreditCardRequestDTO
    ){
        CreditCard creditCardNew  = new CreditCard();

        creditCardNew.setCountry(
            Country.builder().id(
                updateCreditCardRequestDTO.getCountryId()
            ).build()
        );

        Optional.ofNullable(
            updateCreditCardRequestDTO.getNumber()
        ).filter(not(String::isEmpty)).ifPresent(
            (String holderFirstName) -> creditCardNew.setNumber(holderFirstName)
        );

        Optional.ofNullable(
            updateCreditCardRequestDTO.getCardHolderFirstName()
        ).filter(not(String::isEmpty)).ifPresent(
            (String holderFirstName) -> creditCardNew.setCardHolderName(holderFirstName)
        );

        Optional.ofNullable(
            updateCreditCardRequestDTO.getUserEmail()
        ).filter(not(String::isEmpty)).ifPresent(
            (String email) -> creditCardNew.setUserEmail(email)
        );

        Optional.ofNullable(
            updateCreditCardRequestDTO.getUserIdentificationNumber()
        ).filter(not(String::isEmpty)).ifPresent(
            (String idNumber) -> creditCardNew.setUserIdentificationNumber(idNumber)
        );

        Optional.ofNullable(
            updateCreditCardRequestDTO.getUserIdentificationType()
        ).ifPresent(
            (Integer idType) -> creditCardNew.setUserIdentificationType(idType)
        );

        Optional.ofNullable(
            updateCreditCardRequestDTO.getUserId()
        ).ifPresent(
            (Long userId) -> creditCardNew.setUserId(userId)
        );

        Optional.ofNullable(
            updateCreditCardRequestDTO.getUserAddress()
        ).filter(not(String::isEmpty)).ifPresent(
            (String address) -> creditCardNew.setUserAddress(address)
        );

        Optional.ofNullable(
            updateCreditCardRequestDTO.getUserCity()
        ).filter(not(String::isEmpty)).ifPresent(
            (String city) -> creditCardNew.setUserCity(city)
        );

        Optional.ofNullable(
            updateCreditCardRequestDTO.getExpirationMonth()
        ).filter(not(String::isEmpty)).ifPresent(
            (String month) -> creditCardNew.setExpirationMonth(month)
        );

        Optional.ofNullable(
            updateCreditCardRequestDTO.getExpirationYear()
        ).filter(not(String::isEmpty)).ifPresent(
            (String year) -> creditCardNew.setExpirationYear(year)
        );

        return creditCardNew;
    }


    public static BCISettingsDTO getBCISettingsDTO(
        Map<String, String> config,
        Map<String, String> orchConfig
    ) {
        return BCISettingsDTO
            .builder()
            .orchestrator(orchConfig)
            .gateway(config)
            .build();
    }

    public static BCIDeleteCreditCardRequestDTO getBCIDeleteCreditCardRequestDTO(
        Map<String, String> config,
        Map<String, String> orchConfig,
        String token,
        String userId
    ) {
        BCIDeleteCreditCardRequestDTO.CreditCardDetailsDTO creditCard = BCIDeleteCreditCardRequestDTO
            .CreditCardDetailsDTO.builder()
            .token(token)
            .userId(userId)
            .build();

        return BCIDeleteCreditCardRequestDTO
            .builder()
            .creditCard(creditCard)
            .settings(getBCISettingsDTO(config, orchConfig))
            .build();
    }

    public static BCICreateCreditCardRequestDTO getBCICreateCreditCardRequestDTO(
        Map<String, String> config,
        Map<String, String> orchConfig,
        CreditCardTokenDTO creditCardTokenDTO
    ) {
        BCICreateCreditCardRequestDTO.CreditCardDetailsDTO creditCard = BCICreateCreditCardRequestDTO
            .CreditCardDetailsDTO.builder()
            .userId(creditCardTokenDTO.getPayerId())
            .creditCardTypeCode(creditCardTokenDTO.getCompanyCreditCard().getName())
            .firstName(creditCardTokenDTO.getPayerFirstName())
            .lastName(creditCardTokenDTO.getPayerLastName())
            .identificationNumber(creditCardTokenDTO.getPayerIdentification())
            .number(creditCardTokenDTO.getCreditCardNumber())
            .month(Integer.valueOf(creditCardTokenDTO.getCreditCardExpirationMonth()))
            .year(Integer.valueOf(creditCardTokenDTO.getCreditCardExpirationYear()))
            .verificationValue(Integer.valueOf(creditCardTokenDTO.getCreditCardVerificationCode()))
            .build();

        return BCICreateCreditCardRequestDTO
            .builder()
            .creditCard(creditCard)
            .settings(getBCISettingsDTO(config, orchConfig))
            .build();
    }

    private static int getIdentificationType(int identificationType) {
        return Optional.ofNullable(identificationType).orElse(DEFAULT_IDENTIFICATION_TYPE);
    }

    private static String getIdentificationNumber(CreateCreditCardRequestDTO createCreditCardRequestDTO) {
        return Optional.ofNullable(createCreditCardRequestDTO.getUserIdentificationNumber())
            .orElse(createCreditCardRequestDTO.getUserId().toString());
    }

    private static String getIEmail(String email) {
        return Optional.ofNullable(email).orElse(DEFAULT_EMAIL);
    }

    private static boolean isCreditCardUpdated(int isUpdated){
        return isUpdated!=0;
    }

}
