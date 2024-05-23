package org.example.repository.subscribereventhistorylogs;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.example.ObjectMapperSingletonUtil;
import org.example.constants.QueryConstants;
import org.example.database.DatabaseManager;
import org.example.entities.SubscriberEventHistoryLogsEntity;

@Slf4j
public class SubscriberEventHistoryLogsRepository extends DatabaseManager implements ISubscriberEventHistoryLogsRepository {

    private static SubscriberEventHistoryLogsRepository instance;

    public static SubscriberEventHistoryLogsRepository getInstance() {
        if (Objects.isNull(instance)) {
            instance = new SubscriberEventHistoryLogsRepository();
        }
        return instance;
    }

    @SneakyThrows
    @Override
    public SubscriberEventHistoryLogsEntity searchById(Long id) {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("id", id);
        return Optional.ofNullable(executeQuery(QueryConstants.SUBSCRIBERS_EVENT_HISTORY_SELECT_BY_ID,
                        SubscriberEventHistoryLogsEntity.class, parameters))
                .orElse(SubscriberEventHistoryLogsEntity.builder().build());
    }

    @SneakyThrows
    @Override
    public BigInteger save(SubscriberEventHistoryLogsEntity subscriberEventHistoryLogsEntity) {

        log.info(
                "Start repository with the following entity, SubscriberEventHistoryLogsEntity {}",
                ObjectMapperSingletonUtil.objectToJson(subscriberEventHistoryLogsEntity)
        );

        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("flow_event_log_id", subscriberEventHistoryLogsEntity.getFlow_event_log_id());
        parameters.put("event_dispatched", subscriberEventHistoryLogsEntity.getEvent_dispatched());
        parameters.put("subscriber_id", subscriberEventHistoryLogsEntity.getSubscriber_id());

        return executeInsert(QueryConstants.SUBSCRIBERS_EVENT_HISTORY_SAVE, parameters);
    }
}
