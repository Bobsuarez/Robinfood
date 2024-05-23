package com.robinfood.localserver.commons.dtos.electronicbill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ResponseElectronicBillingDTO {

    private LocalDateTime createdAt;

    private String id;

    private String message;
}
