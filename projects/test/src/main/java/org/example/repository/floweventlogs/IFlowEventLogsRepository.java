package org.example.repository.floweventlogs;

import org.example.entities.FlowEventLogsEntity;

import java.math.BigInteger;

public interface IFlowEventLogsRepository {

    BigInteger save(FlowEventLogsEntity flowEventLogsEntity);

    FlowEventLogsEntity searchByEventIdAndFlowId(String eventId, Long flowId);
}
