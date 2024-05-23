package com.robinfood.storeor.entities.CommandConfiguration;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class CommandConfigurationEntity {

    private String description;

    private Long id;

    private String name;

    private List<PoliticsReprocessEntity> policiesReprocess;

    private List<PreconditionCommandEntity> preconditionCommands;

    private ProcessFlowEntity processFlow;

    private Integer sequence;

}
