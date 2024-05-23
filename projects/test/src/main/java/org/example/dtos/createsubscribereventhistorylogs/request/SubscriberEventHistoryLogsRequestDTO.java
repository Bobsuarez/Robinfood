package org.example.dtos.createsubscribereventhistorylogs.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class SubscriberEventHistoryLogsRequestDTO {

    @NotNull(message = "flowEventLogId is required")
    private Long flowEventLogId;

    @NotNull(message = "eventDispatched is required")
    private Integer eventDispatched;

    @NotNull(message = "subscriberId is required")
    private Long subscriberId;

    @NotBlank(message = "uuid is required")
    private String uuid;
}
