package com.robinfood.paymentmethodsbc.sample;

import com.robinfood.paymentmethodsbc.dto.jms.JmsUpdateTransactionStatusDTO;

import java.math.BigDecimal;

public final class JmsUpdateTransactionStatusDTOSample {

    private JmsUpdateTransactionStatusDTOSample() {}

    public static JmsUpdateTransactionStatusDTO getJmsUpdateTransactionStatusDTO() {
        return JmsUpdateTransactionStatusDTO.builder()
            .key("id")
            .identificator("439069")
            .transactionStatus(8L)
            .uuid("441c8804-70d7-4cb9-957a-56fb8feca4ef")
            .message("Update Status by procces to Status 8")
            .transactionCode("")
            .dataphoneCode("TR00E037")
            .creditCardMaskedNumber("")
            .accountType("")
            .franchise("")
            .dataphoneReceiptNumber("")
            .installments(0)
            .establishmentCode("0018936575")
            .transactionValue(BigDecimal.ZERO)
            .transactionTaxValue(BigDecimal.ZERO)
            .build();
    }
}
