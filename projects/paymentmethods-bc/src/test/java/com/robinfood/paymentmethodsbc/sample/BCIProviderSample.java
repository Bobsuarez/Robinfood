package com.robinfood.paymentmethodsbc.sample;

import com.robinfood.paymentmethodsbc.dto.CreditCardTokenDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO.OriginDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO.PaymentDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO.PaymentMethodDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO.UserDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.TransactionStatusUpdateRequestDTO;
import com.robinfood.paymentmethodsbc.dto.bci.creditcard.BCICreditCardResponseDTO;
import com.robinfood.paymentmethodsbc.dto.bci.creditcard.BCICreditCardResponseDTO.CreditCardDetailResult;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCICancelTransactionResponseDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCIPaymentResponseDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCIPaymentResponseDTO.TransactionGatewayResponse;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCIRefundResponseDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCIRefundResponseDTO.GatewayRefundResponse;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCITransactionStatusResponseDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCITransactionStatusResponseDTO.TransactionStatusResponse;
import com.robinfood.paymentmethodsbc.dto.common.response.ResponseDTO;
import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.SettingsDTO;
import com.robinfood.paymentmethodsbc.dto.steps.TransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.enums.CompanyCreditCard;
import com.robinfood.paymentmethodsbc.model.Country;
import com.robinfood.paymentmethodsbc.model.CreditCard;
import com.robinfood.paymentmethodsbc.model.CreditCardType;
import com.robinfood.paymentmethodsbc.model.Entity;
import com.robinfood.paymentmethodsbc.model.PaymentGateway;
import com.robinfood.paymentmethodsbc.model.Platform;
import com.robinfood.paymentmethodsbc.model.Terminal;
import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.model.TransactionDetail;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

public final class BCIProviderSample {

    private BCIProviderSample() {}

    public static SettingsDTO getSettingsDTO() {
        return SettingsDTO
            .builder()
            .gatewayConfig(Map.of("bciComponent", "redeban"))
            .orchConfig(Map.of("bciComponent", "redeban"))
            .terminalConfig(
                Map.of("establishmentId", "abc", "posReferenceId", "123324")
            )
            .build();
    }

    public static PaymentPipeDTO getPaymentPipeDTO() {
        return PaymentPipeDTO
            .builder()
            .country(Country.builder().id(5L).build())
            .entity(Entity.builder().id(1L).build())
            .notifyStatus(false)
            .paymentGateway(
                PaymentGateway
                    .builder()
                    .createdAt(LocalDateTime.now())
                    .description("description")
                    .id(1L)
                    .name("name")
                    .platform(Platform.builder()
                        .id(1L)
                        .name("test")
                        .build())
                    .build()
            )
            .settings(getSettingsDTO())
            .terminal(Terminal.builder().build())
            .transaction(
                Transaction
                    .builder()
                    .authorizationCode("authcode")
                    .uuid("uuid")
                    .entitySource(10000000L)
                    .id(1L)
                    .build()
            )
            .creditCard(CreditCard.builder()
                .number("123345567")
                .id(1L)
                .userAddress("casa")
                .cardHolderName("PRUEBA")
                .userCity("casa")
                .userIdentificationNumber("123456")
                .creditCardType(CreditCardType.builder()
                    .code("asd")
                    .id(1L)
                    .name("visa")
                    .build())
                .userIdentificationType(1)
                .userEmail("asd@test.com")
                .build())
            .transactionDetail(
                TransactionDetail
                    .builder()
                    .dataphoneCode("asd123")
                    .entityReference("entityReference")
                    .terminal(Terminal.builder()
                        .uuid("123uuid")
                        .name("terminalTest")
                        .build())
                    .build()
            )
            .transactionRequestDTO(
                PaymentRequestDTO
                    .builder()
                    .countryId(1L)
                    .user(
                        UserDTO
                            .builder()
                            .userId(1L)
                            .phoneCode("+00")
                            .phoneNumber("34839434893")
                            .firstName("john")
                            .lastName("doe")
                            .build()
                    )
                    .origin(
                        OriginDTO
                            .builder()
                            .channelId(4L)
                            .storeId(1L)
                            .userAgent("userAgent")
                            .ipAddress("0.0.0.0")
                            .build()
                    )
                    .payment(
                        PaymentDTO
                            .builder()
                            .total(new BigDecimal(1000))
                            .subtotal(new BigDecimal(1000))
                            .tax(new BigDecimal(0))
                            .build()
                    )
                    .paymentMethod(
                        PaymentMethodDTO
                            .builder()
                            .id(1L)
                            .installmentsNumber(1838923239L)
                            .referencePaymentMethods(null)
                            .terminalUuid("uuid")
                            .build()
                    )
                    .build()
            )
            .build();
    }

    public static ResponseDTO<BCIPaymentResponseDTO> getBCIPaymentResponseDTO(
        int statusCode
    ) {
        BCIPaymentResponseDTO response = BCIPaymentResponseDTO
            .builder()
            .succeeded(false)
            .pending(false)
            .type(null)
            .gatewayResponse(
                TransactionGatewayResponse
                    .builder()
                    .authorizationCode("code")
                    .transactionCode("code")
                    .transactionReference("reference")
                    .errorCode(null)
                    .errorDescription(null)
                    .message("payment response")
                    .transactionCode("code")
                    .type("type")
                    .build()
            )
            .build();

        ResponseDTO<BCIPaymentResponseDTO> responseDTO = new ResponseDTO<BCIPaymentResponseDTO>();

        responseDTO.setCode(statusCode);
        responseDTO.setData(response);
        responseDTO.setMessage("payment response");

        return responseDTO;
    }

    public static ResponseDTO<BCIPaymentResponseDTO> getBCIPaymentResponseDTOWithAdditionalInformation(
        int statusCode
    ) {
        BCIPaymentResponseDTO response = BCIPaymentResponseDTO
            .builder()
            .succeeded(false)
            .pending(false)
            .type(null)
            .gatewayResponse(
                TransactionGatewayResponse
                    .builder()
                    .authorizationCode("code")
                    .transactionCode("code")
                    .transactionReference("reference")
                    .errorCode(null)
                    .errorDescription(null)
                    .message("payment response")
                    .transactionCode("code")
                    .type("type")
                    .dataphoneCode("AA000000")
                    .creditCardMaskedNumber("333377******1234")
                    .accountType("CR")
                    .franchise("VISA")
                    .dataphoneReceiptNumber("A12345Z")
                    .installments(1)
                    .establishmentCode("XXXXXX")
                    .build()
            )
            .build();

        ResponseDTO<BCIPaymentResponseDTO> responseDTO = new ResponseDTO<BCIPaymentResponseDTO>();

        responseDTO.setCode(statusCode);
        responseDTO.setData(response);
        responseDTO.setMessage("payment response");

        return responseDTO;
    }

    public static ResponseDTO<BCIPaymentResponseDTO> getBCIPaymentResponseDTOPending(
        int statusCode
    ) {
        BCIPaymentResponseDTO response = BCIPaymentResponseDTO
            .builder()
            .succeeded(false)
            .pending(true)
            .type(null)
            .gatewayResponse(
                TransactionGatewayResponse
                    .builder()
                    .authorizationCode("code")
                    .transactionCode("code")
                    .transactionReference("reference")
                    .errorCode(null)
                    .errorDescription(null)
                    .message("payment response")
                    .transactionCode("code")
                    .type("type")
                    .build()
            )
            .build();

        ResponseDTO<BCIPaymentResponseDTO> responseDTO = new ResponseDTO<BCIPaymentResponseDTO>();

        responseDTO.setCode(statusCode);
        responseDTO.setData(response);
        responseDTO.setMessage("payment response");

        return responseDTO;
    }

    public static ResponseDTO<BCIRefundResponseDTO> getBCIRefundResponseDTO(
        int statusCode
    ) {
        BCIRefundResponseDTO response = BCIRefundResponseDTO
            .builder()
            .succeeded(true)
            .gatewayResponse(
                GatewayRefundResponse
                    .builder()
                    .authorizationCode("code")
                    .transactionCode("code")
                    .transactionReference("reference")
                    .transactionCode("code")
                    .type("type")
                    .build()
            )
            .build();

        ResponseDTO<BCIRefundResponseDTO> responseDTO = new ResponseDTO<BCIRefundResponseDTO>();

        responseDTO.setCode(statusCode);
        responseDTO.setData(response);
        responseDTO.setMessage("payment response");
        return responseDTO;
    }

    public static CreditCardTokenDTO getCreditCardTokenDTO() {
        return CreditCardTokenDTO
            .builder()
            .payerId("payerID")
            .companyCreditCard(CompanyCreditCard.VISA)
            .payerFirstName("name")
            .payerLastName("last name")
            .payerIdentification("292080282082")
            .creditCardNumber("213213-12312-3213123-123123-213213")
            .creditCardExpirationMonth("01")
            .creditCardExpirationYear("2040")
            .creditCardVerificationCode("123")
            .build();
    }

    public static ResponseDTO<BCICreditCardResponseDTO> getBCICreditCardResponseDTO(
        int statusCode
    ) {
        BCICreditCardResponseDTO response = BCICreditCardResponseDTO
            .builder()
            .creditCard(
                CreditCardDetailResult
                    .builder()
                    .country("CO")
                    .status("Acepted")
                    .succeeded(true)
                    .token("token")
                    .type("TC")
                    .build()
            )
            .build();

        ResponseDTO<BCICreditCardResponseDTO> responseDTO = new ResponseDTO<BCICreditCardResponseDTO>();

        responseDTO.setCode(statusCode);
        responseDTO.setData(response);
        responseDTO.setMessage("response");
        return responseDTO;
    }

    public static TransactionStatusPipeDTO getTransactionStatusPipeDTO() {
        return TransactionStatusPipeDTO
            .builder()
            .notifyStatus(false)
            .settings(getSettingsDTO())
            .transaction(
                Transaction
                    .builder()
                    .authorizationCode("authcode")
                    .uuid("uuid")
                    .entitySource(10000000L)
                    .id(1L)
                    .build()
            )
            .transactionDetail(
                TransactionDetail
                    .builder()
                    .entityReference("entityReference")
                    .build()
            )
            .transactionStatusUpdateRequestDTO(
                TransactionStatusUpdateRequestDTO
                    .builder()
                    .identificator("id")
                    .key("key")
                    .notification("notification")
                    .type("type")
                    .build()
            )
            .transactionStatusResultDTO(
                BCITransactionStatusResponseDTO
                    .builder()
                    .transaction(
                        TransactionStatusResponse
                            .builder()
                            .accepted("acepted")
                            .authorizationCode("code")
                            .date("2020-01-01")
                            .event("event")
                            .reason("reason")
                            .reference("reference")
                            .success(true)
                            .transactionCode("code")
                            .transactionStatus(1L)
                            .build()
                    )
                    .build()
            )
            .build();
    }

    public static ResponseDTO<BCITransactionStatusResponseDTO> getBCITransactionStatusResponseDTO(
        int statusCode
    ) {
        BCITransactionStatusResponseDTO response = BCITransactionStatusResponseDTO
            .builder()
            .transaction(
                TransactionStatusResponse
                    .builder()
                    .accepted("acepted")
                    .authorizationCode("code")
                    .date("2020-01-01")
                    .event("event")
                    .reason("reason")
                    .reference("reference")
                    .success(true)
                    .transactionCode("code")
                    .transactionStatus(1L)
                    .build()
            )
            .build();

        ResponseDTO<BCITransactionStatusResponseDTO> responseDTO = new ResponseDTO<BCITransactionStatusResponseDTO>();

        responseDTO.setCode(statusCode);
        responseDTO.setData(response);
        responseDTO.setMessage("response");
        return responseDTO;
    }

    public static ResponseDTO<BCICancelTransactionResponseDTO> getBCICancelTransactionResponseDTO(
        int statusCode
    ) {
        BCICancelTransactionResponseDTO response = BCICancelTransactionResponseDTO
            .builder()
            .error(null)
            .msg("mensaje")
            .statusCode(200)
            .success("success")
            .build();

        ResponseDTO<BCICancelTransactionResponseDTO> responseDTO = new ResponseDTO<BCICancelTransactionResponseDTO>();

        responseDTO.setCode(statusCode);
        responseDTO.setData(response);
        responseDTO.setMessage("response");
        return responseDTO;
    }

    public static Map<String, String> getConfig() {
        return Map.of("bciComponent", "redeban");
    }
}
