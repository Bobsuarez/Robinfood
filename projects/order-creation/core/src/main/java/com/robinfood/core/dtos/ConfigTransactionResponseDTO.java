package com.robinfood.core.dtos;

import com.robinfood.core.dtos.transactionresponsedto.OrderResponseDTO;
import com.robinfood.core.dtos.transactionresponsedto.TransactionCreationResponseDTO;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfigTransactionResponseDTO implements Serializable {

    private static final long serialVersionUID = 7001192446257703475L;

    TransactionCreationResponseDTO transactionCreationResponse;

    public List<Long> getOrderIds() {
        return transactionCreationResponse.getTransaction()
            .getOrders().stream()
                .map(OrderResponseDTO::getId)
                .collect(Collectors.toList());
    }
}
