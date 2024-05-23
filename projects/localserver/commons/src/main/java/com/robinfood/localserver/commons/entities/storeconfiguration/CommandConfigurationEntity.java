package com.robinfood.localserver.commons.entities.storeconfiguration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class CommandConfigurationEntity {

    private String description;

    private Long id;

    private String name;

    private List<PoliciesReprocessEntity> policiesReprocess;

    private List<PreconditionCommandEntity> preconditionCommands;

    private ProcessFlowEntity processFlow;

    private Integer sequence;

}
