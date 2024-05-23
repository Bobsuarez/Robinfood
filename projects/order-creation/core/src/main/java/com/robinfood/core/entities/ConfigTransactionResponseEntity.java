package com.robinfood.core.entities;

import com.robinfood.core.entities.transactionresponseentities.TransactionResponseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfigTransactionResponseEntity {

    private TransactionResponseEntity transaction;

}
