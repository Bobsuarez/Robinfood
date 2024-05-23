package com.robinfood.localorderbc.mocks.entities;

import com.robinfood.localorderbc.entities.OrderCommandExecutionTraceLogsEntity;

import java.time.LocalDateTime;

public class OrderCommandExecutionTraceLogsEntityMock {

    public OrderCommandExecutionTraceLogsEntity orderCommandExecutionTraceLogsEntity = OrderCommandExecutionTraceLogsEntity
            .builder()
            .commandId(1L)
            .commandName("Command Test")
            .executionEndedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .executionEndedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .orderId(1L)
            .reprocess(false)
            .reprocessAttempt(1)
            .responseStatusCode(200)
            .request("[]")
            .responseMessage("Command Success")
            .build();


    public OrderCommandExecutionTraceLogsEntity orderCommandExecutionTraceLogsResultEntity = OrderCommandExecutionTraceLogsEntity
            .builder()
            .id(1L)
            .commandId(1L)
            .commandName("Command Test")
            .executionEndedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .executionEndedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .orderId(1L)
            .reprocess(false)
            .reprocessAttempt(1)
            .responseStatusCode(200)
            .request("[]")
            .responseMessage("Command Success")
            .build();

}
