package com.robinfood.localserver.commons.entities.storeconfiguration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PoliciesReprocessEntity {

    private String description;

    private Long id;

    private Integer maxReprocessAttempt;

    private Long reprocessTime;

}
