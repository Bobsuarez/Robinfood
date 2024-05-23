package com.robinfood.paymentmethodsbc.utils;

import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.BCI_VALIDATE_STATUS;
import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.EVENT_KEY;
import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.PAYMENT_CREATION_ALERT_SUB_EVENT;
import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.PAYMENT_CREATION_EVENT;
import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.PAYMENT_CREATION_SUB_EVENT;
import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.PAYMENT_MESSAGE_EVENT;
import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.PAYMENT_MESSAGE_SUB_EVENT;
import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.PAYMENT_STATUS_ERROR;
import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.PAYMENT_STATUS_LOG;
import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.PAYMENT_UPDATE_STATUS_EVENT;
import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.SEND_ALERT;
import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.SEND_REPORT;
import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.STATUS_KEY;
import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.SUB_EVENT_KEY;
import static net.logstash.logback.argument.StructuredArguments.f;
import static net.logstash.logback.argument.StructuredArguments.v;

import com.robinfood.paymentmethodsbc.dto.PaymentReportDTO;
import com.robinfood.paymentmethodsbc.dto.PaymentResponseDTO;
import com.robinfood.paymentmethodsbc.dto.StatusResponseDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsEntityRefundRequestDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsTransactionDetailDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsTransactionGenerateDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsTransactionStatusChangeDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsUpdateTransactionStatusDTO;
import com.robinfood.paymentmethodsbc.dto.steps.JmsTransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.TransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.enums.PaymentStatusReportEnum;
import com.robinfood.paymentmethodsbc.mappers.ReportMapper;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class LoggerReportUtils {

    private LoggerReportUtils() {}

    public static void doPaymentLogger(
        String status,
        String message,
        PaymentPipeDTO transactionCreatePipeDTO
    ) {
        try {
            PaymentReportDTO report = ReportMapper.mapDoPaymentReportDTO(
                transactionCreatePipeDTO
            );
            report.setStatus(status);
            report.setMessage(message);
            report.setEventLog(PAYMENT_CREATION_EVENT);
            report.setSubEventLog(PAYMENT_CREATION_SUB_EVENT);
            log.info(message, f(report));
            log.info(SEND_REPORT, JsonUtils.convertToJson(report));
        } catch (Exception e) {
            log.error(
                e.getLocalizedMessage(),
                v(EVENT_KEY, PAYMENT_CREATION_EVENT),
                v(SUB_EVENT_KEY, PAYMENT_CREATION_SUB_EVENT),
                v(STATUS_KEY, PAYMENT_STATUS_LOG)
            );
            log.error("doPaymentLogger Exception 1:", e);
        }
    }

    public static void doPaymentLogger(
        String message,
        PaymentPipeDTO transactionCreatePipeDTO,
        PaymentResponseDTO response
    ) {
        try {
            PaymentReportDTO report = ReportMapper.mapDoPaymentReportDTO(
                transactionCreatePipeDTO,
                response
            );
            report.setMessage(message);
            report.setEventLog(PAYMENT_CREATION_EVENT);
            report.setSubEventLog(PAYMENT_CREATION_SUB_EVENT);
            log.info(message, f(report));
            log.info(SEND_REPORT, JsonUtils.convertToJson(report));
        } catch (Exception e) {
            log.error(
                e.getLocalizedMessage(),
                v(EVENT_KEY, PAYMENT_CREATION_EVENT),
                v(SUB_EVENT_KEY, PAYMENT_CREATION_SUB_EVENT),
                v(STATUS_KEY, PAYMENT_STATUS_LOG)
            );
            log.error("doPaymentLogger Exception 2:", e);
        }
    }

    public static void doValidateStatusLogger(
        String message,
        TransactionStatusPipeDTO transactionStatusPipeDTO,
        StatusResponseDTO response
    ) {
        try {
            PaymentReportDTO report = ReportMapper.mapDoValidateStatusReport(
                transactionStatusPipeDTO,
                response
            );
            report.setMessage(message);
            report.setEventLog(PAYMENT_CREATION_EVENT);
            report.setSubEventLog(PAYMENT_CREATION_SUB_EVENT);
            log.info(message, f(report));
            log.info(SEND_REPORT, JsonUtils.convertToJson(report));
        } catch (Exception e) {
            log.error(
                e.getLocalizedMessage(),
                v(EVENT_KEY, PAYMENT_CREATION_EVENT),
                v(SUB_EVENT_KEY, PAYMENT_CREATION_SUB_EVENT),
                v(STATUS_KEY, PAYMENT_STATUS_LOG)
            );
            log.error("doValidateStatusLogger Exception", e);
        }
    }

    public static void doCreateTransactionLogger(
        String status,
        String message,
        PaymentRequestDTO paymentRequestDTO,
        String response
    ) {
        try {
            PaymentReportDTO report = ReportMapper.mapPaymentRequestDTOReport(
                paymentRequestDTO,
                response
            );
            report.setStatus(status);
            report.setMessage(message);
            report.setEventLog(PAYMENT_MESSAGE_EVENT);
            report.setSubEventLog(PAYMENT_MESSAGE_SUB_EVENT);
            log.info(message, f(report));
            log.info(SEND_REPORT, JsonUtils.convertToJson(report));
        } catch (Exception e) {
            log.error(
                e.getLocalizedMessage(),
                v(EVENT_KEY, PAYMENT_MESSAGE_EVENT),
                v(SUB_EVENT_KEY, PAYMENT_MESSAGE_SUB_EVENT),
                v(STATUS_KEY, PAYMENT_STATUS_LOG)
            );
            log.error("doCreateTransactionLogger Exception:", e);
        }
    }

    public static void doGeneratePaymentLogger(
        String status,
        String message,
        JmsTransactionGenerateDTO jmsTransactionGenerateDTO,
        String response
    ) {
        try {
            PaymentReportDTO report = ReportMapper.mapJmsTransactionGenerateDTOReport(
                jmsTransactionGenerateDTO,
                response
            );
            report.setStatus(status);
            report.setMessage(message);
            report.setEventLog(PAYMENT_MESSAGE_EVENT);
            report.setSubEventLog(PAYMENT_CREATION_SUB_EVENT);
            log.info(message, f(report));
            log.info(SEND_REPORT, JsonUtils.convertToJson(report));
        } catch (Exception e) {
            log.error(
                e.getLocalizedMessage(),
                v(EVENT_KEY, PAYMENT_MESSAGE_EVENT),
                v(SUB_EVENT_KEY, PAYMENT_CREATION_SUB_EVENT),
                v(STATUS_KEY, PAYMENT_STATUS_LOG)
            );
            log.error("doGeneratePaymentLogger Exception:", e);
        }
    }

    public static void doNotifyStatusLogger(
        String status,
        String message,
        JmsTransactionStatusChangeDTO statusChangeDTO,
        JmsTransactionDetailDTO detail,
        String response
    ) {
        try {
            PaymentReportDTO report = ReportMapper.mapJmsTransactionStatusChangeDTOReport(
                statusChangeDTO,
                detail,
                response
            );
            report.setStatus(status);
            report.setMessage(message);
            report.setEventLog(PAYMENT_MESSAGE_EVENT);
            report.setSubEventLog(PAYMENT_CREATION_SUB_EVENT);
            log.info(message, f(report));
            log.info(SEND_REPORT, JsonUtils.convertToJson(report));
        } catch (Exception e) {
            log.error(
                e.getLocalizedMessage(),
                v(EVENT_KEY, PAYMENT_MESSAGE_EVENT),
                v(SUB_EVENT_KEY, PAYMENT_CREATION_SUB_EVENT),
                v(STATUS_KEY, PAYMENT_STATUS_LOG)
            );
            log.error("doNotifyStatusLogger Exception:", e);
        }
    }

    public static void doValidateStatusErrorLogger(
        String message,
        TransactionStatusPipeDTO transactionStatusPipeDTO,
        StatusResponseDTO response
    ) {
        try {
            PaymentReportDTO report = ReportMapper.mapDoValidateStatusErrorReport(
                transactionStatusPipeDTO,
                response
            );
            report.setMessage(message);
            report.setEventLog(PAYMENT_CREATION_EVENT);
            report.setSubEventLog(PAYMENT_CREATION_SUB_EVENT);
            log.info(message, f(report));
            log.info(SEND_REPORT, JsonUtils.convertToJson(report));
        } catch (Exception e) {
            log.error(
                e.getLocalizedMessage(),
                v(EVENT_KEY, PAYMENT_CREATION_EVENT),
                v(SUB_EVENT_KEY, PAYMENT_CREATION_SUB_EVENT),
                v(STATUS_KEY, PAYMENT_STATUS_LOG)
            );
            log.error("doValidateStatusErrorLogger Exception", e);
        }
    }

    public static void errorUpdateStatusTransactionLogger(
        String status,
        String message,
        String error
    ) {
        try {
            PaymentReportDTO report = ReportMapper.mapErrorToUpdateTransactionStatusJMS(
                status,
                message,
                error
            );
            report.setStatus(PAYMENT_STATUS_ERROR);
            report.setEventLog(PAYMENT_UPDATE_STATUS_EVENT);
            report.setSubEventLog(PAYMENT_CREATION_SUB_EVENT);
            log.info(message, f(report));
            log.info(SEND_REPORT, JsonUtils.convertToJson(report));
        } catch (Exception e) {
            log.error(
                e.getLocalizedMessage(),
                v(EVENT_KEY, PAYMENT_UPDATE_STATUS_EVENT),
                v(SUB_EVENT_KEY, PAYMENT_CREATION_SUB_EVENT),
                v(STATUS_KEY, PAYMENT_STATUS_LOG)
            );
            log.error("errorUpdateStatusTransactionLogger Exception:", e);
        }
    }

    public static void doValidateStatusAlertLogger(
        String alertMessage,
        String alertEventCode,
        TransactionStatusPipeDTO transactionStatusPipeDTO
    ) {
        StatusResponseDTO response = StatusResponseDTO
            .builder()
            .statusResult(
                transactionStatusPipeDTO.getTransactionStatusResultDTO()
            )
            .build();

        try {
            PaymentReportDTO report = ReportMapper.mapDoValidateStatusReport(
                transactionStatusPipeDTO,
                response
            );
            report.setMessage(alertMessage);
            report.setEventLog(PAYMENT_CREATION_EVENT);
            report.setSubEventLog(alertEventCode);
            log.info(alertMessage, f(report));
            log.info(SEND_ALERT, JsonUtils.convertToJson(report));
        } catch (Exception e) {
            log.error(
                e.getLocalizedMessage(),
                v(EVENT_KEY, PAYMENT_CREATION_EVENT),
                v(SUB_EVENT_KEY, PAYMENT_CREATION_ALERT_SUB_EVENT),
                v(STATUS_KEY, PAYMENT_STATUS_LOG)
            );
            log.error("doValidateStatusAlertLogger Exception", e);
        }
    }

    public static void doValidateStatusAlertLogger(
        String alertMessage,
        String alertEventCode,
        JmsTransactionStatusPipeDTO jsmTransactionStatusPipeDTO
    ) {
        try {
            PaymentReportDTO report = ReportMapper.mapDoValidateStatusReport(
                jsmTransactionStatusPipeDTO
            );
            report.setMessage(alertMessage);
            report.setEventLog(PAYMENT_CREATION_EVENT);
            report.setSubEventLog(alertEventCode);
            log.info(alertMessage, f(report));
            log.info(SEND_ALERT, JsonUtils.convertToJson(report));
        } catch (Exception e) {
            log.error(
                e.getLocalizedMessage(),
                v(EVENT_KEY, PAYMENT_CREATION_EVENT),
                v(SUB_EVENT_KEY, PAYMENT_CREATION_ALERT_SUB_EVENT),
                v(STATUS_KEY, PAYMENT_STATUS_LOG)
            );
            log.error("doValidateStatusAlertLogger Exception", e);
        }
    }

    public static void doUpdateTransactionLogger(
        String status,
        String message,
        JmsUpdateTransactionStatusDTO jmsUpdateTransactionStatusDTO,
        String response
    ) {
        try {
            PaymentReportDTO report = ReportMapper.mapJmsUpdateTransactionStatusDTOReport(
                jmsUpdateTransactionStatusDTO,
                response
            );
            report.setStatus(status);
            report.setMessage(message);
            report.setEventLog(PAYMENT_MESSAGE_EVENT);
            report.setSubEventLog(PAYMENT_MESSAGE_SUB_EVENT);
            log.info(message, f(report));
            log.info(SEND_REPORT, JsonUtils.convertToJson(report));
        } catch (Exception e) {
            log.error(
                e.getLocalizedMessage(),
                v(EVENT_KEY, PAYMENT_MESSAGE_EVENT),
                v(SUB_EVENT_KEY, PAYMENT_MESSAGE_SUB_EVENT),
                v(STATUS_KEY, PAYMENT_STATUS_LOG)
            );
            log.error("doUpdateTransactionLogger Exception:", e);
        }
    }

    public static void doRefundTransactionLogger(
        String status,
        String message,
        JmsEntityRefundRequestDTO refundRequestDTO,
        String response
    ) {
        try {
            PaymentReportDTO report = ReportMapper.mapJmsEntityRefundRequestDTODTOReport(
                refundRequestDTO,
                response
            );
            report.setStatus(status);
            report.setMessage(message);
            report.setEventLog(PAYMENT_MESSAGE_EVENT);
            report.setSubEventLog(PAYMENT_MESSAGE_SUB_EVENT);
            log.info(message, f(report));
            log.info(SEND_REPORT, JsonUtils.convertToJson(report));
        } catch (Exception e) {
            log.error(
                e.getLocalizedMessage(),
                v(EVENT_KEY, PAYMENT_MESSAGE_EVENT),
                v(SUB_EVENT_KEY, PAYMENT_MESSAGE_SUB_EVENT),
                v(STATUS_KEY, PAYMENT_STATUS_LOG)
            );
            log.error("doRefundTransactionLogger Exception:", e);
        }
    }

    public static void reportTransactionStatusPipeDTO(
        final Long currentTransactionStatusId,
        final TransactionStatusPipeDTO pipe
    ) {
    
        try{
            Long newTransactionStatusId = pipe
                .getTransactionStatusResultDTO()
                .getTransaction()
                .getTransactionStatus();

            if (
                isTransacctionStatusAllowedToReport(
                    currentTransactionStatusId,
                    newTransactionStatusId
                )
            ) {
                PaymentReportDTO report = ReportMapper.mapDoValidateStatusReport(
                    pipe
                );
                report.setMessage(BCI_VALIDATE_STATUS);
                report.setEventLog(PAYMENT_CREATION_EVENT);
                report.setSubEventLog(PAYMENT_CREATION_SUB_EVENT);
                log.info(BCI_VALIDATE_STATUS, f(report));
                log.info(SEND_REPORT, JsonUtils.convertToJson(report));
            }
        } catch (Exception e) {
            log.error(
                e.getLocalizedMessage(),
                v(EVENT_KEY, PAYMENT_CREATION_EVENT),
                v(SUB_EVENT_KEY, PAYMENT_CREATION_SUB_EVENT),
                v(STATUS_KEY, PAYMENT_STATUS_LOG)
            );
            log.error("reportTransactionStatusPipeDTO Exception", e);
        }
    }

    public static void reportJmsTransactionStatusPipeDTO(
        final Long currentTransactionStatusId,
        final JmsTransactionStatusPipeDTO pipe
    ) {
        try {

            Long newTransactionStatusId = pipe
                .getJmsUpdateTransactionStatusDTO()
                .getTransactionStatus();
            
            if (
                isTransacctionStatusAllowedToReport(
                    currentTransactionStatusId,
                    newTransactionStatusId
                )
            ) {
                PaymentReportDTO report = ReportMapper.mapDoValidateStatusReport(
                    pipe
                );
                report.setMessage(BCI_VALIDATE_STATUS);
                report.setEventLog(PAYMENT_CREATION_EVENT);
                report.setSubEventLog(PAYMENT_CREATION_SUB_EVENT);
                log.info(BCI_VALIDATE_STATUS, f(report));
                log.info(SEND_REPORT, JsonUtils.convertToJson(report));
            }
        } catch (Exception e) {
            log.error(
                e.getLocalizedMessage(),
                v(EVENT_KEY, PAYMENT_CREATION_EVENT),
                v(SUB_EVENT_KEY, PAYMENT_CREATION_ALERT_SUB_EVENT),
                v(STATUS_KEY, PAYMENT_STATUS_LOG)
            );
            log.error("reportJmsTransactionStatusPipeDTO Exception", e);
        }
    }

    public static boolean isTransacctionStatusAllowedToReport(
        Long currentTransactionStatusId,
        Long newTransactionStatusId
    ) {
        return Arrays
            .stream(PaymentStatusReportEnum.values())
            .filter(
                reportStatus ->
                    reportStatus
                        .getCurrentStatusId()
                        .equals(currentTransactionStatusId) &&
                    reportStatus.getNewStatusId().equals(newTransactionStatusId)
            )
            .findFirst()
            .map(PaymentStatusReportEnum::isReported)
            .orElse(false);
    }
}
