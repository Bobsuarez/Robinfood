package com.robinfood.ordereports.dtos.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentStatusDTO implements Serializable {

    private String createdAt;

    private FailedTransactionDTO failedTransaction;

    private Long id;

    private String name;

    private List<TraceDTO> trace;
}
