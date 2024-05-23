package com.robinfood.localserver.commons.entities.orderlocally;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class OrderCommandExecutionTraceResponseEntity {

    private Long commandId;

    private String commandName;

    private LocalDateTime executionEndedAt;

    private LocalDateTime executionStartedAt;

    private Long id;

    private Long orderId;

    private String responseMessage;

    private Integer responseStatusCode;

    private Boolean reprocess;

    private Integer reprocessAttempt;

    private String request;

}
