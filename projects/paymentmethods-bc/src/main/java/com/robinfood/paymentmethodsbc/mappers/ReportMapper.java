package com.robinfood.paymentmethodsbc.mappers;

import com.robinfood.paymentmethodsbc.dto.PaymentReportDTO;
import com.robinfood.paymentmethodsbc.dto.PaymentResponseDTO;
import com.robinfood.paymentmethodsbc.dto.StatusResponseDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO.PaymentMethodDTO.PaymentReferencePaymentMethod;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCITransactionStatusResponseDTO.TransactionStatusResponse;
import com.robinfood.paymentmethodsbc.dto.jms.JmsEntityRefundRequestDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsTransactionDetailDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsTransactionGenerateDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsTransactionStatusChangeDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsUpdateTransactionStatusDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsTransactionStatusChangeDTO.TransactionDetailDTO;
import com.robinfood.paymentmethodsbc.dto.steps.JmsTransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.TransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.enums.StatusEnum;
import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.BCI_VALIDATE_STATUS_ERROR;
import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.VALIDATE_STATUS;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.BCITransactionStatusResponseDTONullSafety.validateBCITransactionStatusResponseDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.BCITransactionStatusResponseDTONullSafety.validateTransactionStatusResponse;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.ModelsNullSafety.validateTransactionDetail;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.PaymentPipeDTONullSafe.getCountryId;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.PaymentPipeDTONullSafe.getCountryName;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.PaymentPipeDTONullSafe.getDataphoneCode;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.PaymentPipeDTONullSafe.getPaymentBci;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.PaymentPipeDTONullSafe.getPaymentGatewayId;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.PaymentPipeDTONullSafe.getPaymentMethodId;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.PaymentPipeDTONullSafe.getPaymentMethodIdList;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.PaymentPipeDTONullSafe.getPlatformId;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.PaymentPipeDTONullSafe.getPlatformName;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.PaymentPipeDTONullSafe.getStoreId;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.PaymentPipeDTONullSafe.getTerminalName;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.PaymentPipeDTONullSafe.getTerminalUuid;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.PaymentPipeDTONullSafe.getTotal;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.PaymentPipeDTONullSafe.getTransactionId;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.PaymentPipeDTONullSafe.getUserId;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.PaymentPipeDTONullSafe.getUserPhone;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.PaymentPipeDTONullSafe.getUuidFromPaymentPipeDTOTransaction;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.PaymentPipeDTONullSafe.validatePaymentPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.PaymentResponseDTONullSafety.getMessageBCI;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.PaymentResponseDTONullSafety.getPaymentGatewayLevelCategory;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.PaymentResponseDTONullSafety.getPaymentGatewayStatus;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.StatusResponseDTONullSafety.getPaymentGatewayLevelCategoryFromStatusResponseDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.StatusResponseDTONullSafety.getPaymentGatewayStatusFromStatusResponseDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.StatusResponseDTONullSafety.validateStatusResponseDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.TransactionStatusPipeDTONullSafety.getCountryIdFromTransactionStatusPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.TransactionStatusPipeDTONullSafety.getCountryNameFromTransactionStatusPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.TransactionStatusPipeDTONullSafety.getDataphoneCodeFromTransactionStatusPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.TransactionStatusPipeDTONullSafety.getPaymentBciFromTransactionStatusPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.TransactionStatusPipeDTONullSafety.getPaymentGatewayIdFromTransactionStatusPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.TransactionStatusPipeDTONullSafety.getPaymentMethodIdFromTransactionStatusPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.TransactionStatusPipeDTONullSafety.getPaymentMethodNameFromTransactionStatusPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.TransactionStatusPipeDTONullSafety.getPhoneNumberFromTransactionStatusPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.TransactionStatusPipeDTONullSafety.getPlatformIdFromTransactionStatusPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.TransactionStatusPipeDTONullSafety.getStoreIdFromTransactionStatusPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.TransactionStatusPipeDTONullSafety.getTerminalNameFromTransactionStatusPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.TransactionStatusPipeDTONullSafety.getTerminalUuidFromTransactionStatusPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.TransactionStatusPipeDTONullSafety.getTotalFromTransactionStatusPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.TransactionStatusPipeDTONullSafety.getTransactionIdFromTransactionStatusPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.TransactionStatusPipeDTONullSafety.getUserIdFromTransactionStatusPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.TransactionStatusPipeDTONullSafety.getUuidFromTransactionStatusPipeDTOTransaction;

import java.util.Optional;

import static com.robinfood.paymentmethodsbc.utils.nullsafety.JmsTransactionStatusPipeDTONullSafety.getCountryIdFromJmsTransactionStatusPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.JmsTransactionStatusPipeDTONullSafety.getCountryNameFromJmsTransactionStatusPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.JmsTransactionStatusPipeDTONullSafety.getDataphoneCodeFromJmsTransactionStatusPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.JmsTransactionStatusPipeDTONullSafety.getPaymentBciFromJmsTransactionStatusPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.JmsTransactionStatusPipeDTONullSafety.getPaymentGatewayIdFromJmsTransactionStatusPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.JmsTransactionStatusPipeDTONullSafety.getPaymentMethodNameFromJmsTransactionStatusPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.JmsTransactionStatusPipeDTONullSafety.getPhoneNumberFromJmsTransactionStatusPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.JmsTransactionStatusPipeDTONullSafety.getPlatformIdFromJmsTransactionStatusPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.JmsTransactionStatusPipeDTONullSafety.getStoreIdFromJmsTransactionStatusPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.JmsTransactionStatusPipeDTONullSafety.getTerminalNameFromJmsTransactionStatusPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.JmsTransactionStatusPipeDTONullSafety.getTerminalUuidFromJmsTransactionStatusPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.JmsTransactionStatusPipeDTONullSafety.getTotalFromJmsTransactionStatusPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.JmsTransactionStatusPipeDTONullSafety.getTransactionIdFromJmsTransactionStatusPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.JmsTransactionStatusPipeDTONullSafety.getUuidFromJmsTransactionStatusPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.JmsTransactionStatusPipeDTONullSafety.validateJmsUpdateTransactionStatusDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.JmsTransactionStatusPipeDTONullSafety.getUserIdFromJmsTransactionStatusPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.JmsTransactionStatusPipeDTONullSafety.getPaymentMethodIdFromJmsTransactionStatusPipeDTO;

public final class ReportMapper {

    private ReportMapper() {}

    public static PaymentReportDTO mapDoPaymentReportDTO(
        PaymentPipeDTO paymentPipeDTO
    ) {
        return PaymentReportDTO
            .builder()
            .uuid(getUuidFromPaymentPipeDTOTransaction(paymentPipeDTO))
            .country(getCountryName(paymentPipeDTO))
            .countryId(getCountryId(paymentPipeDTO))
            .storeId(getStoreId(paymentPipeDTO))
            .referencePaymentMethods(getPaymentMethodIdList(paymentPipeDTO))
            .userPhone(getUserPhone(paymentPipeDTO))
            .userId(getUserId(paymentPipeDTO))
            .total(getTotal(paymentPipeDTO))
            .generalPaymentMethodId(getPaymentMethodId(paymentPipeDTO))
            .platformId(getPlatformId(paymentPipeDTO))
            .platformName(getPlatformName(paymentPipeDTO))
            .paymentGatewayId(getPaymentGatewayId(paymentPipeDTO))
            .transactionId(getTransactionId(paymentPipeDTO))
            .transactionDataphoneTerminalCode(getDataphoneCode(paymentPipeDTO))
            .transactionTerminalUuid(getTerminalUuid(paymentPipeDTO))
            .transactionTerminalName(getTerminalName(paymentPipeDTO))
            .creditCardMaskedNumber(
                validateTransactionDetail(validatePaymentPipeDTO(paymentPipeDTO).getTransactionDetail())
                    .getCreditCardMaskedNumber()
            )
            .accountType(
                validateTransactionDetail(validatePaymentPipeDTO(paymentPipeDTO).getTransactionDetail())
                    .getAccountType()
            )
            .franchise(
                validateTransactionDetail(validatePaymentPipeDTO(paymentPipeDTO).getTransactionDetail())
                    .getFranchise()
            )
            .dataphoneReceiptNumber(
                validateTransactionDetail(validatePaymentPipeDTO(paymentPipeDTO).getTransactionDetail())
                    .getDataphoneReceiptNumber()
            )
            .installments(
                validateTransactionDetail(validatePaymentPipeDTO(paymentPipeDTO).getTransactionDetail())
                    .getInstallments()
            )
            .establishmentCode(
                validateTransactionDetail(validatePaymentPipeDTO(paymentPipeDTO).getTransactionDetail())
                    .getEstablishmentCode()
            )
            .paymentBci(getPaymentBci(paymentPipeDTO))
            .build();
    }

    public static PaymentReportDTO mapDoPaymentReportDTO(
        PaymentPipeDTO paymentPipeDTO,
        PaymentResponseDTO response
    ) {
        return PaymentReportDTO
            .builder()
            .status(StatusEnum.getStatusCode(response.getTransactionStatus()).getStatusCode())
            .uuid(getUuidFromPaymentPipeDTOTransaction(paymentPipeDTO))
            .country(getCountryName(paymentPipeDTO))
            .countryId(getCountryId(paymentPipeDTO))
            .storeId(getStoreId(paymentPipeDTO))
            .referencePaymentMethods(getPaymentMethodIdList(paymentPipeDTO))
            .userPhone(getUserPhone(paymentPipeDTO))
            .userId(getUserId(paymentPipeDTO))
            .total(getTotal(paymentPipeDTO))
            .generalPaymentMethodId(getPaymentMethodId(paymentPipeDTO))
            .platformId(getPlatformId(paymentPipeDTO))
            .platformName(getPlatformName(paymentPipeDTO))
            .paymentGatewayId(getPaymentGatewayId(paymentPipeDTO))
            .transactionId(getTransactionId(paymentPipeDTO))
            .transactionDataphoneTerminalCode(getDataphoneCode(paymentPipeDTO))
            .transactionTerminalUuid(getTerminalUuid(paymentPipeDTO))
            .transactionTerminalName(getTerminalName(paymentPipeDTO))
            .creditCardMaskedNumber(response.getCreditCardMaskedNumber())
            .accountType(response.getAccountType())
            .franchise(response.getFranchise())
            .dataphoneReceiptNumber(response.getDataphoneReceiptNumber())
            .installments(response.getInstallments())
            .establishmentCode(response.getEstablishmentCode())
            .paymentBci(getPaymentBci(paymentPipeDTO))
            .paymentBciResponse(response.getBciResponseBody())
            .statusCode(getPaymentGatewayStatus(response))
            .statusCategory(getPaymentGatewayLevelCategory(response))
            .message(getMessageBCI(response))
            .build();
    }


    public static PaymentReportDTO mapDoValidateStatusReport(
        TransactionStatusPipeDTO transactionStatusPipeDTO
    ) {

        TransactionStatusResponse transaction = validateBCITransactionStatusResponseDTO(
            transactionStatusPipeDTO.getTransactionStatusResultDTO()
        ).getTransaction();
  
        return PaymentReportDTO.builder()
            .uuid(getUuidFromTransactionStatusPipeDTOTransaction(transactionStatusPipeDTO))
            .status(
                StatusEnum.getStatusCode(
                    validateTransactionStatusResponse(transaction).getTransactionStatus()
                ).getStatusCode()
            )
            .country(getCountryNameFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .countryId(getCountryIdFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .storeId(getStoreIdFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .userPhone(getPhoneNumberFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .userId(getUserIdFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .total(getTotalFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .generalPaymentMethodId(getPaymentMethodIdFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .generalPaymentMethodName(getPaymentMethodNameFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .platformId(getPlatformIdFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .paymentGatewayId(getPaymentGatewayIdFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .transactionId(getTransactionIdFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .transactionDataphoneTerminalCode(getDataphoneCodeFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .transactionTerminalUuid(getTerminalUuidFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .transactionTerminalName(getTerminalNameFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .creditCardMaskedNumber(
                validateTransactionStatusResponse(transaction).getCreditCardMaskedNumber()
            )
            .accountType(
                validateTransactionStatusResponse(transaction).getAccountType()
            )
            .franchise(
                validateTransactionStatusResponse(transaction).getFranchise()
            )
            .dataphoneReceiptNumber(
                validateTransactionStatusResponse(transaction).getDataphoneReceiptNumber()
            )
            .installments(
                validateTransactionStatusResponse(transaction).getInstallments()
            )
            .establishmentCode(
                validateTransactionStatusResponse(transaction).getEstablishmentCode()
            )
            .paymentBci(getPaymentBciFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .statusCode(validateTransactionStatusResponse(transaction).getPaymentGatewayStatus())
            .statusCategory(validateTransactionStatusResponse(transaction).getPaymentGatewayLevelCategory())
            .message(VALIDATE_STATUS)
            .build();
    }


    public static PaymentReportDTO mapDoValidateStatusReport(
        TransactionStatusPipeDTO transactionStatusPipeDTO,
        StatusResponseDTO response
    ) {
        return PaymentReportDTO.builder()
            .uuid(getUuidFromTransactionStatusPipeDTOTransaction(transactionStatusPipeDTO))
            .status(
                StatusEnum.getStatusCode(
                    validateTransactionStatusResponse(
                        validateBCITransactionStatusResponseDTO(
                            validateStatusResponseDTO(response).getStatusResult()
                        ).getTransaction()
                    ).getTransactionStatus()
                ).getStatusCode()
            )
            .country(getCountryNameFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .countryId(getCountryIdFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .storeId(getStoreIdFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .userPhone(getPhoneNumberFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .userId(getUserIdFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .total(getTotalFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .generalPaymentMethodId(getPaymentMethodIdFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .generalPaymentMethodName(getPaymentMethodNameFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .platformId(getPlatformIdFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .paymentGatewayId(getPaymentGatewayIdFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .transactionId(getTransactionIdFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .transactionDataphoneTerminalCode(getDataphoneCodeFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .transactionTerminalUuid(getTerminalUuidFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .transactionTerminalName(getTerminalNameFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .creditCardMaskedNumber(
                validateTransactionStatusResponse(
                    validateBCITransactionStatusResponseDTO(
                        validateStatusResponseDTO(response).getStatusResult()
                    ).getTransaction()
                ).getCreditCardMaskedNumber()
            )
            .accountType(
                validateTransactionStatusResponse(
                    validateBCITransactionStatusResponseDTO(
                        validateStatusResponseDTO(response).getStatusResult()
                    ).getTransaction()
                ).getAccountType()
            )
            .franchise(
                validateTransactionStatusResponse(
                    validateBCITransactionStatusResponseDTO(
                        validateStatusResponseDTO(response).getStatusResult()
                    ).getTransaction()
                ).getFranchise()
            )
            .dataphoneReceiptNumber(
                validateTransactionStatusResponse(
                    validateBCITransactionStatusResponseDTO(
                        validateStatusResponseDTO(response).getStatusResult()
                    ).getTransaction()
                ).getDataphoneReceiptNumber()
            )
            .installments(
                validateTransactionStatusResponse(
                    validateBCITransactionStatusResponseDTO(
                        validateStatusResponseDTO(response).getStatusResult()
                    ).getTransaction()
                ).getInstallments()
            )
            .establishmentCode(
                validateTransactionStatusResponse(
                    validateBCITransactionStatusResponseDTO(
                        validateStatusResponseDTO(response).getStatusResult()
                    ).getTransaction()
                ).getEstablishmentCode()
            )
            .paymentBci(getPaymentBciFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .statusCode(getPaymentGatewayStatusFromStatusResponseDTO(response))
            .statusCategory(getPaymentGatewayLevelCategoryFromStatusResponseDTO(response))
            .message(VALIDATE_STATUS)
            .build();
    }

    public static PaymentReportDTO mapPaymentRequestDTOReport(
        PaymentRequestDTO paymentRequestDTO,
        String response
    ) {
        return PaymentReportDTO.builder()
            .uuid(paymentRequestDTO.getTransactionUuid())
            .status(StatusEnum.PENDING.getStatusCode())
            .countryId(paymentRequestDTO.getCountryId())
            .storeId(paymentRequestDTO.getOrigin().getStoreId())
            .userPhone(paymentRequestDTO.getUser().getPhoneNumber())
            .userId(paymentRequestDTO.getUser().getUserId())
            .total(paymentRequestDTO.getPayment().getTotal())
            .generalPaymentMethodId(paymentRequestDTO.getPaymentMethod().getId())
            .platformId(paymentRequestDTO.getOrigin().getChannelId())
            .paymentGatewayId(
                paymentRequestDTO.getPaymentMethod().getReferencePaymentMethods()
                    .stream().findFirst()
                    .orElse(PaymentReferencePaymentMethod.builder().id(-1).build()).getId()
            )
            .transactionId(paymentRequestDTO.getTransactionId())
            .transactionTerminalUuid(paymentRequestDTO.getPaymentMethod().getTerminalUuid())
            .installments(
                paymentRequestDTO.getPaymentMethod().getInstallmentsNumber().intValue()
            )
            .message(response)
            .build();
    }

    public static PaymentReportDTO mapJmsTransactionGenerateDTOReport(
        JmsTransactionGenerateDTO jmsTransactionGenerateDTO,
        String response
    ) {
        PaymentRequestDTO paymentRequestDTO = jmsTransactionGenerateDTO.getTransaction();
        return mapPaymentRequestDTOReport(paymentRequestDTO, response);
    }

    public static PaymentReportDTO mapJmsTransactionStatusChangeDTOReport(
        JmsTransactionStatusChangeDTO statusChangeDTO,
        JmsTransactionDetailDTO detail,
        String response
    ) {

        TransactionDetailDTO transactionDetailDTO = Optional.ofNullable(
            statusChangeDTO.getTransactionDetail()
        )
        .orElse(
            TransactionDetailDTO.builder().build()
        );

        return PaymentReportDTO.builder()
            .uuid(statusChangeDTO.getTransactionUuid())
            .status(StatusEnum.getStatusCode(statusChangeDTO.getTransactionStatus().getId()).getStatusCode())
            .countryId(detail.getCountryId())
            .total(statusChangeDTO.getPayment().getTotal())
            .paymentGatewayId(statusChangeDTO.getPaymentGateway().getId())
            .transactionId(statusChangeDTO.getTransactionId())
            .creditCardMaskedNumber(transactionDetailDTO.getCreditCardMaskedNumber())
            .dataphoneReceiptNumber(transactionDetailDTO.getDataphoneReceiptNumber())
            .establishmentCode(transactionDetailDTO.getEstablishmentCode())
            .franchise(transactionDetailDTO.getFranchise())
            .generalPaymentMethodId(transactionDetailDTO.getGeneralPaymentMethodId())
            .generalPaymentMethodName(transactionDetailDTO.getGeneralPaymentMethodName())
            .installments(transactionDetailDTO.getInstallments())
            .platformId(transactionDetailDTO.getPlatformId())
            .platformName(transactionDetailDTO.getPlatformName())
            .storeId(transactionDetailDTO.getStoreId())
            .userId(transactionDetailDTO.getUserId())
            .userPhone(transactionDetailDTO.getUserPhone())
            .transactionDataphoneTerminalCode(transactionDetailDTO.getTransactionDataphoneTerminalCode())
            .transactionDataphoneUniqueCode(transactionDetailDTO.getTransactionDataphoneUniqueCode())
            .transactionTerminalName(transactionDetailDTO.getTransactionTerminalName())
            .transactionTerminalUuid(transactionDetailDTO.getTransactionTerminalUuid())
            .statusCode(statusChangeDTO.getTransactionStatus().getName())
            .message(response)
            .build();
    }

    public static PaymentReportDTO mapDoValidateStatusErrorReport(
        TransactionStatusPipeDTO transactionStatusPipeDTO,
        StatusResponseDTO response
    ) {
        return PaymentReportDTO.builder()
            .uuid(getUuidFromTransactionStatusPipeDTOTransaction(transactionStatusPipeDTO))
            .status(
                StatusEnum.getStatusCode(
                    validateTransactionStatusResponse(
                        validateBCITransactionStatusResponseDTO(
                            validateStatusResponseDTO(response).getStatusResult()
                        ).getTransaction()
                    ).getTransactionStatus()
                ).getStatusCode()
            )
            .country(getCountryNameFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .countryId(getCountryIdFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .storeId(getStoreIdFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .userPhone(getPhoneNumberFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .userId(getUserIdFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .total(getTotalFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .generalPaymentMethodId(getPaymentMethodIdFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .generalPaymentMethodName(getPaymentMethodNameFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .platformId(getPlatformIdFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .paymentGatewayId(getPaymentGatewayIdFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .transactionId(getTransactionIdFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .transactionDataphoneTerminalCode(getDataphoneCodeFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .transactionTerminalUuid(getTerminalUuidFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .transactionTerminalName(getTerminalNameFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .creditCardMaskedNumber(
                validateTransactionStatusResponse(
                    validateBCITransactionStatusResponseDTO(
                        validateStatusResponseDTO(response).getStatusResult()
                    ).getTransaction()
                ).getCreditCardMaskedNumber()
            )
            .accountType(
                validateTransactionStatusResponse(
                    validateBCITransactionStatusResponseDTO(
                        validateStatusResponseDTO(response).getStatusResult()
                    ).getTransaction()
                ).getAccountType()
            )
            .franchise(
                validateTransactionStatusResponse(
                    validateBCITransactionStatusResponseDTO(
                        validateStatusResponseDTO(response).getStatusResult()
                    ).getTransaction()
                ).getFranchise()
            )
            .dataphoneReceiptNumber(
                validateTransactionStatusResponse(
                    validateBCITransactionStatusResponseDTO(
                        validateStatusResponseDTO(response).getStatusResult()
                    ).getTransaction()
                ).getDataphoneReceiptNumber()
            )
            .installments(
                validateTransactionStatusResponse(
                    validateBCITransactionStatusResponseDTO(
                        validateStatusResponseDTO(response).getStatusResult()
                    ).getTransaction()
                ).getInstallments()
            )
            .establishmentCode(
                validateTransactionStatusResponse(
                    validateBCITransactionStatusResponseDTO(
                        validateStatusResponseDTO(response).getStatusResult()
                    ).getTransaction()
                ).getEstablishmentCode()
            )
            .paymentBci(getPaymentBciFromTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .statusCode(getPaymentGatewayStatusFromStatusResponseDTO(response))
            .statusCategory(getPaymentGatewayLevelCategoryFromStatusResponseDTO(response))
            .message(BCI_VALIDATE_STATUS_ERROR)
            .build();
    }

    public static PaymentReportDTO mapErrorToUpdateTransactionStatusJMS(
        String status,
        String message,
        String error
    ) {
        return PaymentReportDTO.builder()
            .statusCode(status)
            .statusCategory(error)
            .message(message)
            .build();
    }

    public static PaymentReportDTO mapDoValidateStatusReport(
        JmsTransactionStatusPipeDTO transactionStatusPipeDTO
    ) {

        JmsUpdateTransactionStatusDTO transactionStatus 
            = validateJmsUpdateTransactionStatusDTO(transactionStatusPipeDTO);
            
        return PaymentReportDTO.builder()
            .status(
                StatusEnum.getStatusCode(
                    transactionStatusPipeDTO
                    .getTransaction()
                    .getTransactionStatus()
                    .getId()
                ).getStatusCode()
            )
            .uuid(getUuidFromJmsTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .country(getCountryNameFromJmsTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .countryId(getCountryIdFromJmsTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .storeId(getStoreIdFromJmsTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .userPhone(getPhoneNumberFromJmsTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .userId(getUserIdFromJmsTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .total(getTotalFromJmsTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .generalPaymentMethodId(getPaymentMethodIdFromJmsTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .generalPaymentMethodName(getPaymentMethodNameFromJmsTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .platformId(getPlatformIdFromJmsTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .paymentGatewayId(getPaymentGatewayIdFromJmsTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .transactionId(getTransactionIdFromJmsTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .transactionDataphoneTerminalCode(getDataphoneCodeFromJmsTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .transactionTerminalUuid(getTerminalUuidFromJmsTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .transactionTerminalName(getTerminalNameFromJmsTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .creditCardMaskedNumber(transactionStatus.getCreditCardMaskedNumber())
            .accountType(transactionStatus.getAccountType())
            .franchise(transactionStatus.getFranchise())
            .dataphoneReceiptNumber(transactionStatus.getDataphoneReceiptNumber())
            .installments(transactionStatus.getInstallments())
            .establishmentCode(transactionStatus.getEstablishmentCode())
            .paymentBci(getPaymentBciFromJmsTransactionStatusPipeDTO(transactionStatusPipeDTO))
            .message(VALIDATE_STATUS)
            .build();
    }

    public static PaymentReportDTO mapJmsUpdateTransactionStatusDTOReport(
        JmsUpdateTransactionStatusDTO jmsUpdateTransactionStatusDTO,
        String response
    ) {
        return PaymentReportDTO.builder()
            .uuid(jmsUpdateTransactionStatusDTO.getUuid())
            .total(jmsUpdateTransactionStatusDTO.getTransactionValue())
            .transactionDataphoneTerminalCode(jmsUpdateTransactionStatusDTO.getDataphoneCode())
            .creditCardMaskedNumber(jmsUpdateTransactionStatusDTO.getCreditCardMaskedNumber())
            .accountType(jmsUpdateTransactionStatusDTO.getAccountType())
            .franchise(jmsUpdateTransactionStatusDTO.getFranchise())
            .dataphoneReceiptNumber(jmsUpdateTransactionStatusDTO.getDataphoneReceiptNumber())
            .installments(jmsUpdateTransactionStatusDTO.getInstallments())
            .establishmentCode(jmsUpdateTransactionStatusDTO.getEstablishmentCode())
            .build();
    }

    public static PaymentReportDTO mapJmsEntityRefundRequestDTODTOReport(
        JmsEntityRefundRequestDTO refundRequestDTO,
        String response
    ) {
        return PaymentReportDTO.builder()
            .uuid(refundRequestDTO.getEntitySourceReference())
            .transactionId(refundRequestDTO.getEntityId())
            .build();
    }
}
