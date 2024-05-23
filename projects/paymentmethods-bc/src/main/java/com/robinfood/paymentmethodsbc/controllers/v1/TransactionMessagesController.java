package com.robinfood.paymentmethodsbc.controllers.v1;

import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.PAYMENT_MESSAGE_CREATION_RECEIVED;
import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.PAYMENT_MESSAGE_CREATION_RECEIVED_DESCRIPTION;
import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.PAYMENT_MESSAGE_REFUND_RECEIVED;
import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.PAYMENT_MESSAGE_REFUND_RECEIVED_DESCRIPTION;
import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.PAYMENT_MESSAGE_UPDATE_RECEIVED;
import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.PAYMENT_MESSAGE_UPDATE_RECEIVED_DESCRIPTION;
import static com.robinfood.paymentmethodsbc.utils.JsonUtils.convertToJson;
import static com.robinfood.paymentmethodsbc.utils.LoggerReportUtils.doCreateTransactionLogger;
import static com.robinfood.paymentmethodsbc.utils.LoggerReportUtils.doRefundTransactionLogger;
import static com.robinfood.paymentmethodsbc.utils.LoggerReportUtils.doUpdateTransactionLogger;
import static com.robinfood.paymentmethodsbc.utils.ResponseUtils.getResponseDTO;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.robinfood.paymentmethodsbc.annotations.BasicLog;
import com.robinfood.paymentmethodsbc.asynchronus.TransactionMessagesAsync;
import com.robinfood.paymentmethodsbc.controllers.v1.docs.TransactionMessagesDocs;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO;
import com.robinfood.paymentmethodsbc.dto.common.response.ResponseDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsEntityRefundRequestDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsUpdateTransactionStatusDTO;
import com.robinfood.paymentmethodsbc.enums.ResponseCode;
import com.robinfood.paymentmethodsbc.utils.TransactionLogger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
    path = "/api/v1/transactions/messages",
    produces = APPLICATION_JSON_VALUE
)
public class TransactionMessagesController implements TransactionMessagesDocs {

    private final TransactionMessagesAsync transactionMessagesAsync;

    public TransactionMessagesController(
        TransactionMessagesAsync transactionMessagesAsync
    ) {
        this.transactionMessagesAsync = transactionMessagesAsync;
    }

    @Override
    @PostMapping(path = "/create", consumes = APPLICATION_JSON_VALUE)
    @BasicLog
    public ResponseDTO<Object> createTransaction(
        PaymentRequestDTO paymentRequestDTO
    ) {
        doCreateTransactionLogger(
            PAYMENT_MESSAGE_CREATION_RECEIVED,
            PAYMENT_MESSAGE_CREATION_RECEIVED_DESCRIPTION,
            paymentRequestDTO,
            convertToJson(getResponseDTO(ResponseCode.CREATED))
        );
        TransactionLogger.invoke(paymentRequestDTO);
        transactionMessagesAsync.createTransactionAsync(paymentRequestDTO);
        return getResponseDTO(ResponseCode.CREATED);
    }

    @Override
    @PostMapping(path = "/update", consumes = APPLICATION_JSON_VALUE)
    @BasicLog
    public ResponseDTO<Object> updateTransaction(
        JmsUpdateTransactionStatusDTO updateTransactionStatusDTO
    ) {
        doUpdateTransactionLogger(
            PAYMENT_MESSAGE_UPDATE_RECEIVED,
            PAYMENT_MESSAGE_UPDATE_RECEIVED_DESCRIPTION,
            updateTransactionStatusDTO,
            convertToJson(getResponseDTO(ResponseCode.SUCCESS))
        );
        TransactionLogger.invoke(updateTransactionStatusDTO);
        transactionMessagesAsync.updateTransactionAsync(updateTransactionStatusDTO);
        return getResponseDTO(ResponseCode.SUCCESS);
    }

    @Override
    @PostMapping(path = "/refund", consumes = APPLICATION_JSON_VALUE)
    @BasicLog
    public ResponseDTO<Object> refundTransaction(
        JmsEntityRefundRequestDTO refundRequestDTO
    ) {
        doRefundTransactionLogger(
            PAYMENT_MESSAGE_REFUND_RECEIVED,
            PAYMENT_MESSAGE_REFUND_RECEIVED_DESCRIPTION,
            refundRequestDTO,
            convertToJson(getResponseDTO(ResponseCode.SUCCESS))
        );
        transactionMessagesAsync.refundTransactionAsync(refundRequestDTO);
        return getResponseDTO(ResponseCode.SUCCESS);
    }
}
