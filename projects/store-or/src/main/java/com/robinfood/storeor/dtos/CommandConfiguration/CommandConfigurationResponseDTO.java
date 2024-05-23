package com.robinfood.storeor.dtos.CommandConfiguration;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class CommandConfigurationResponseDTO {

    private String description;

    private Long id;

    private String name;

    private List<PoliticsReprocessResponseDTO> policiesReprocess;

    private List<PreconditionCommandResponseDTO> preconditionCommands;

    private ProcessFlowDTO processFlow;

    private Integer sequence;

}
