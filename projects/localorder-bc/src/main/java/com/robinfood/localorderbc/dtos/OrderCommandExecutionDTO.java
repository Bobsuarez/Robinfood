package com.robinfood.localorderbc.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class OrderCommandExecutionDTO {

    private Long commandId;

    private String commandName;

    private Integer currentResponseStatusCode;

    private LocalDateTime executionEndedAt;

    private LocalDateTime executionStartedAt;

    private Long orderId;

    private Boolean reprocess;

    private Integer reprocessAttempt;

}
