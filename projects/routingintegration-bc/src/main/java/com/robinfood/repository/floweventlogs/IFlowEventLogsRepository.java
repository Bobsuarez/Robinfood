package com.robinfood.repository.floweventlogs;

import com.robinfood.entities.FlowEventLogsEntity;

import java.math.BigInteger;

public interface IFlowEventLogsRepository {

    BigInteger save(FlowEventLogsEntity flowEventLogsEntity);

    FlowEventLogsEntity searchByEventIdAndFlowId(String eventId, Long flowId);
}
