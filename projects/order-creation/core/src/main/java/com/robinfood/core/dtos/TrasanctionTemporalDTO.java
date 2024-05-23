package com.robinfood.core.dtos;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
public class TrasanctionTemporalDTO  implements Serializable {
    private Long transactionId;

    private TransactionRequestDTO requestTransaction;
}
