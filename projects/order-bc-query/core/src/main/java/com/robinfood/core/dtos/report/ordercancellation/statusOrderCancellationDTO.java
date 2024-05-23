package com.robinfood.core.dtos.report.ordercancellation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class statusOrderCancellationDTO {

    @JsonProperty("code")
    private String codeStatus;

    @JsonProperty("id")
    private Long statusId;
}
