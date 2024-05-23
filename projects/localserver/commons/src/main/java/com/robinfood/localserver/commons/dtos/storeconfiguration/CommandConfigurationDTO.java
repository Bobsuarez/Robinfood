package com.robinfood.localserver.commons.dtos.storeconfiguration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class CommandConfigurationDTO {

    private String description;

    private Long id;

    private String name;

    private List<PoliciesReprocessDTO> policiesReprocess;

    private List<PreconditionCommandDTO> preconditionCommands;

    private ProcessFlowDTO processFlow;

    private Integer sequence;
}
