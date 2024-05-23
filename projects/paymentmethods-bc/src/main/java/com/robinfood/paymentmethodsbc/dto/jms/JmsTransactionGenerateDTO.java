package com.robinfood.paymentmethodsbc.dto.jms;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JmsTransactionGenerateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("transaction")
    private final PaymentRequestDTO transaction;

    @JsonProperty("retries")
    private final Long retries;
}
