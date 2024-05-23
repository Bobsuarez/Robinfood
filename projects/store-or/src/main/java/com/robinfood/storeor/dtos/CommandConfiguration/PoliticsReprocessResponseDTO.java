package com.robinfood.storeor.dtos.CommandConfiguration;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PoliticsReprocessResponseDTO {

    private String description;

    private Long id;

    private Integer maxReprocessAttempt;

    private Long reprocessTime;

}
