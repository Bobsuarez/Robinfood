package org.example.dtos.createeventflow.request;

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
public class EventRequestDTO {

    @NotBlank(message = "eventId is required")
    private String eventId;

    @NotNull(message = "flowId is required")
    private Long flowId;

    @NotBlank(message = "payload is required")
    private String payload;

    @NotBlank(message = "uuid is required")
    private String uuid;
}
