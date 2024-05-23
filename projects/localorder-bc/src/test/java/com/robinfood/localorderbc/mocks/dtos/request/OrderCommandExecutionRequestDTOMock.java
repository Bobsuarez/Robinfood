package com.robinfood.localorderbc.mocks.dtos.request;

import com.robinfood.localorderbc.dtos.request.OrderCommandExecutionRequestDTO;

import java.time.LocalDateTime;

public class OrderCommandExecutionRequestDTOMock {

    public OrderCommandExecutionRequestDTO orderCommandExecutionRequestDTO = OrderCommandExecutionRequestDTO
            .builder()
            .commandId(1L)
            .commandName("Command Test")
            .executionEndedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .executionStartedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .orderId(1L)
            .reprocess(false)
            .responseStatusCode(200)
            .request("[]")
            .responseMessage("Command Success")
            .build();

    public OrderCommandExecutionRequestDTO orderCommandExecutionTestWithoutPrintRequestDTO = OrderCommandExecutionRequestDTO
            .builder()
            .commandId(2L)
            .commandName("Command Test")
            .executionEndedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .executionStartedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .orderId(1L)
            .reprocess(false)
            .responseStatusCode(200)
            .request("[]")
            .responseMessage("Command Success")
            .build();

}
