package com.robinfood.paymentmethodsbc.sample;

import com.robinfood.paymentmethodsbc.constants.AppConstants;
import com.robinfood.paymentmethodsbc.dto.PaymentResponseDTO;
import com.robinfood.paymentmethodsbc.model.CountryPaymentGateway;
import com.robinfood.paymentmethodsbc.model.CountryPaymentGatewaySetting;
import com.robinfood.paymentmethodsbc.model.PaymentGateway;
import com.robinfood.paymentmethodsbc.model.PaymentGatewayCreditCard;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentGatewaySamples {

    public static CountryPaymentGatewaySetting getCountryPaymentGatewaySetting() {
        return CountryPaymentGatewaySetting
            .builder()
            .id(1L)
            .name(AppConstants.ORCHESTRATOR_ID_SETTINGS_NAME)
            .value("2")
            .paymentGateway(new PaymentGateway(1L))
            .build();
    }

    public static Map<String, String> getPaymentGatewaySettings() {
        Map<String, String> item = new HashMap<>();
        item.put("", "");
        return item;
    }

    public static Map<Long, Map<String, String>> getAllCountryPaymentGatewaySettingMap() {
        Map<Long, Map<String, String>> settings = new HashMap<Long, Map<String, String>>();
        Map<String, String> item = new HashMap<>();
        item.put("", "");
        settings.put(1L, item);
        return settings;
    }

    public static Map<Long, Map<String, String>> getAllCountryPaymentGatewaySettingMap(String key, String value) {
        Map<Long, Map<String, String>> settings = new HashMap<Long, Map<String, String>>();
        Map<String, String> item = new HashMap<>();
        item.put(key, value);
        settings.put(1L, item);
        return settings;
    }

    public static List<CountryPaymentGatewaySetting> getCountryPaymentGatewaySettings() {
        return Arrays.asList(
            CountryPaymentGatewaySetting
                .builder()
                .id(1L)
                .name(AppConstants.ORCHESTRATOR_ID_SETTINGS_NAME)
                .value("2")
                .paymentGateway(new PaymentGateway(1L))
                .country(CountrySample.getCountry())
                .build(),
            CountryPaymentGatewaySetting
                .builder()
                .id(2L)
                .name("None")
                .value("")
                .country(CountrySample.getCountry())
                .paymentGateway(new PaymentGateway(2L))
                .build()
        );
    }

    public static PaymentResponseDTO getPaymentResponseDTO() {
        return PaymentResponseDTO
            .builder()
            .transactionStatus(1L)
            .authorizationCode("authorizationCode")
            .transactionCode("transationCode")
            .errorCode("errorCode")
            .errorDescription("errorDescription")
            .message("message")
            .build();
    }

    public static PaymentResponseDTO getPaymentResponseDTOWithErrorCodeNull() {
        return PaymentResponseDTO
            .builder()
            .transactionStatus(1L)
            .authorizationCode("authorizationCode")
            .transactionCode("transationCode")
            .errorDescription("errorDescription")
            .message("message")
            .build();
    }

    public static PaymentResponseDTO getPaymentErrorResponseDTO() {
        return PaymentResponseDTO
            .builder()
            .transactionStatus(1L)
            .authorizationCode("authorizationCode")
            .transactionCode("transationCode")
            .errorCode("errorCode")
            .errorDescription("errorDescription")
            .message("message")
            .paymentGatewayStatus("ERROR_BCI")
            .paymentGatewayLevelCategory("ERROR_LEVEL_BCI")
            .build();
    }
    public static PaymentResponseDTO getPaymentErrorResponse() {
        return PaymentResponseDTO
            .builder()
            .transactionStatus(3L)
            .authorizationCode("authorizationCode")
            .transactionCode("transationCode")
            .errorCode("errorCode")
            .errorDescription("errorDescription")
            .message("message")
            .paymentGatewayLevelCategory("ERROR_LEVEL_BCI")
            .build();
    }

    public static PaymentGateway getPaymentGateway() {
        return PaymentGateway
            .builder()
            .id(4L)
            .name("Payment Gateway sample")
            .description("Payment Gateway description sample")
            .platform(PlatformSample.getPlatform())
            .build();
    }

    public static PaymentGateway getPaymentValidateGateway() {
        return PaymentGateway
            .builder()
            .id(4L)
            .name("Payment Gateway sample")
            .description("Payment Gateway description sample")
            .platform(PlatformSample.getPlatform())
            .build();
    }
    public static PaymentGateway getPaymentValidateDifferentGateway() {
        return PaymentGateway
            .builder()
            .id(6L)
            .name("Payment Different Gateway sample")
            .description("Payment Gateway description sample")
            .platform(PlatformSample.getValidatePlatform())
            .build();
    }

    public static PaymentGatewayCreditCard getPaymentGatewayCreditCard() {
        return PaymentGatewayCreditCard
            .builder()
            .id(1L)
            .creditCard(CreditCardSamples.getCreditCard(false))
            .paymentGateway(getPaymentGateway())
            .token("xyz-123-abc")
            .build();
    }

    public static CountryPaymentGateway getCountryPaymentGateway() {
        return CountryPaymentGateway
            .builder()
            .id(1L)
            .paymentGateway(getPaymentGateway())
            .build();
    }
}
