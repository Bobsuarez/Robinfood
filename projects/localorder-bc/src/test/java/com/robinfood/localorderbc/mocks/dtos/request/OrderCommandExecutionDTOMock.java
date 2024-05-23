package com.robinfood.localorderbc.mocks.dtos.request;

import com.robinfood.localorderbc.dtos.OrderCommandExecutionDTO;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class OrderCommandExecutionDTOMock {

    public OrderCommandExecutionDTO orderCommandExecutionResultDTO = OrderCommandExecutionDTO
            .builder()
            .commandId(1L)
            .commandName("Command Test")
            .currentResponseStatusCode(200)
            .executionEndedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .executionStartedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .orderId(1L)
            .reprocess(false)
            .reprocessAttempt(1)
            .build();

    public OrderCommandExecutionDTO orderCommandExecutionResultRetriesNullDTO = OrderCommandExecutionDTO
            .builder()
            .commandId(1L)
            .commandName("Command Test")
            .currentResponseStatusCode(200)
            .executionEndedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .executionStartedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .orderId(1L)
            .reprocess(false)
            .reprocessAttempt(null)
            .build();


    public List<OrderCommandExecutionDTO> orderCommandExecutionDTOList = Arrays.asList(
            OrderCommandExecutionDTO
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
            OrderCommandExecutionDTO
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

    public OrderCommandExecutionDTO orderCommandExecutionWithoutPrintResultDTO = OrderCommandExecutionDTO
            .builder()
            .commandId(3L)
            .commandName("Command Test")
            .currentResponseStatusCode(200)
            .executionEndedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .executionStartedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .orderId(1L)
            .reprocess(false)
            .reprocessAttempt(1)
            .build();

}
