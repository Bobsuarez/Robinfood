package com.robinfood.paymentmethodsbc.publishers;

import com.robinfood.paymentmethodsbc.dto.common.response.ResponseDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsTransactionDetailDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsTransactionGenerateDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsTransactionStatusChangeDTO;
import com.robinfood.paymentmethodsbc.exceptions.PaymentMethodsException;

public interface TransactionMessagesPublisher {

    ResponseDTO<Object> sendGenerateMessage(
        JmsTransactionGenerateDTO message
    ) throws PaymentMethodsException;

    ResponseDTO<Object> notifyChangeStatusTransaction(
        JmsTransactionStatusChangeDTO message,
        JmsTransactionDetailDTO detail
    ) throws PaymentMethodsException;
}
