package com.robinfood.localorderbc.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class OrderCommandExecutionRequestDTO {

    private Long commandId;

    private String commandName;

    private LocalDateTime executionEndedAt;

    private LocalDateTime executionStartedAt;

    private Long orderId;

    private String responseMessage;

    private Integer responseStatusCode;

    private Boolean reprocess;

    private String request;

}
