package com.robinfood.core.entities.transactionresponseentities;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseEntity {

    private long id;

    private String createdAt;

    private List<OrderResponseEntity> orders;

    private String uuid;
}
