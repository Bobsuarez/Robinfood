package com.robinfood.localserver.commons.dtos.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class PrintingRequestDTO {

    @Builder.Default
    private Boolean isReprint = Boolean.TRUE;

    @NotNull
    private Long orderId;
}
