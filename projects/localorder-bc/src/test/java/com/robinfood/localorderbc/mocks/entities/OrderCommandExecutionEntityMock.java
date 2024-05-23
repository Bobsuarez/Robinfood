package com.robinfood.localorderbc.mocks.entities;

import com.robinfood.localorderbc.entities.OrderCommandExecutionEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class OrderCommandExecutionEntityMock {

    public OrderCommandExecutionEntity orderCommandExecutionEntity = OrderCommandExecutionEntity
            .builder()
            .commandId(1L)
            .commandName("Command Test")
            .currentResponseStatusCode(200)
            .executionEndedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .executionStartedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .orderId(1L)
            .reprocess(false)
            .reprocessAttempt(2)
            .createdAt(null)
            .updatedAt(null)
            .deletedAt(null)
            .build();


    public OrderCommandExecutionEntity OrderCommandExecutionResultEntity = OrderCommandExecutionEntity
            .builder()
            .commandId(1L)
            .commandName("Command Test")
            .currentResponseStatusCode(200)
            .executionEndedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .executionStartedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .orderId(1L)
            .reprocess(false)
            .reprocessAttempt(2)
            .createdAt(null)
            .updatedAt(null)
            .deletedAt(null)
            .build();

    public OrderCommandExecutionEntity orderCommandExecutionWithoutPrintResultEntity = OrderCommandExecutionEntity
            .builder()
            .commandId(2L)
            .commandName("Command Test")
            .currentResponseStatusCode(200)
            .executionEndedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .executionStartedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .orderId(1L)
            .reprocess(false)
            .reprocessAttempt(2)
            .createdAt(null)
            .updatedAt(null)
            .deletedAt(null)
            .build();

    public List<OrderCommandExecutionEntity> orderCommandExecutionEntityList = Arrays.asList(
            OrderCommandExecutionEntity
                    .builder()
                    .commandId(1L)
                    .commandName("Command Test")
                    .currentResponseStatusCode(200)
                    .executionEndedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .executionStartedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .orderId(1L)
                    .reprocess(true)
                    .reprocessAttempt(1)
                    .build(),
            OrderCommandExecutionEntity
                    .builder()
                    .commandId(2L)
                    .commandName("Command Test")
                    .currentResponseStatusCode(200)
                    .executionEndedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .executionStartedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .orderId(2L)
                    .reprocess(true)
                    .reprocessAttempt(1)
                    .build()

    );

    public OrderCommandExecutionEntity orderCommandExecutionWithoutPrintEntity = OrderCommandExecutionEntity
            .builder()
            .commandId(2L)
            .commandName("Command Test")
            .currentResponseStatusCode(200)
            .executionEndedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .executionStartedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .orderId(1L)
            .reprocess(false)
            .reprocessAttempt(2)
            .createdAt(null)
            .updatedAt(null)
            .deletedAt(null)
            .build();

}
