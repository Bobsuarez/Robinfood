package com.robinfood.core.dtos.transactionresponsedto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCreationResponseDTO implements Serializable {

    private static final long serialVersionUID = 7930367839867743020L;

    private TransactionResponseDTO transaction;
}
