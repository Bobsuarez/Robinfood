package com.robinfood.repository.flows;

import com.robinfood.entities.FlowsEntity;

public interface IFlowsRepository {

    FlowsEntity searchByFlowCode(String flowCode);
}
