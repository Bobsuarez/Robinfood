package com.robinfood.repository.floweventlogs;

import com.robinfood.constants.QueryConstants;
import com.robinfood.database.DatabaseManager;
import com.robinfood.entities.FlowEventLogsEntity;
import lombok.SneakyThrows;

import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class FlowEventLogsRepository extends DatabaseManager implements IFlowEventLogsRepository {

    private static FlowEventLogsRepository instance;

    public static FlowEventLogsRepository getInstance() {
        if (Objects.isNull(instance)) {
            instance = new FlowEventLogsRepository();
        }
        return instance;
    }

    @SneakyThrows
    @Override
    public FlowEventLogsEntity searchByEventIdAndFlowId(String eventId, Long flowId) {

        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("eventId", eventId);
        parameters.put("flowId", flowId);
        return Optional.ofNullable(executeQuery(QueryConstants.FLOW_SELECT_EVENT_AND_FLOW_ID,
                        FlowEventLogsEntity.class, parameters))
                .orElse(FlowEventLogsEntity.builder().build());
    }

    @SneakyThrows
    @Override
    public BigInteger save(FlowEventLogsEntity flowEventLogsEntity) {

        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("event_id", flowEventLogsEntity.getEvent_id());
        parameters.put("flow_id", flowEventLogsEntity.getFlow_id());
        parameters.put("payload", flowEventLogsEntity.getPayload());

        return executeInsert(QueryConstants.SAVE_EVENTS, parameters);
    }
}
