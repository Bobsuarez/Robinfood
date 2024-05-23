package com.robinfood.localserver.commons.dtos.storeconfiguration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PoliciesReprocessDTO {

    private String description;

    private Long id;

    private Integer maxReprocessAttempt;

    private Long reprocessTime;
}
