package com.robinfood.core.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBNamed;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@DynamoDBTable(tableName = "transactions_created")
public class TransactionCreatedEntity {

    @DynamoDBHashKey
    @DynamoDBAttribute
    @DynamoDBNamed("transaction_id")
    private Long transactionId;

    @DynamoDBAttribute
    @DynamoDBNamed("request_transaction")
    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.S)
    private String requestTransaction;

    @DynamoDBAttribute
    private long ttl;
}
