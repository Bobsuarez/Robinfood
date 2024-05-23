package com.robinfood.storeor.entities.CommandConfiguration;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PoliticsReprocessEntity {

    private String description;

    private Long id;

    private Integer maxReprocessAttempt;

    private Long reprocessTime;

}
