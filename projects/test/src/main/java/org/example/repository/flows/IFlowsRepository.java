package org.example.repository.flows;

import org.example.entities.FlowsEntity;

public interface IFlowsRepository {

    FlowsEntity searchByFlowCode(String flowCode);
}
