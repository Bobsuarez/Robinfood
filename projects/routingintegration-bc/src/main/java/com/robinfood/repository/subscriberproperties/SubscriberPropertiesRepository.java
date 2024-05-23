package com.robinfood.repository.subscriberproperties;

import com.robinfood.constants.QueryConstants;
import com.robinfood.database.DatabaseManager;
import com.robinfood.entities.SubscriberPropertiesEntity;
import lombok.SneakyThrows;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class SubscriberPropertiesRepository extends DatabaseManager implements ISubscriberPropertiesRepository {

    private static SubscriberPropertiesRepository instance;

    public static SubscriberPropertiesRepository getInstance() {
        if (Objects.isNull(instance)) {
            instance = new SubscriberPropertiesRepository();
        }
        return instance;
    }

    @Override
    @SneakyThrows
    public List<SubscriberPropertiesEntity> findBySubscriberId(Long subscriberId) {

        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("subscriber_id", subscriberId);
        return Optional.ofNullable(executeQueryList(
                QueryConstants.SUBSCRIBERS_SELECT_SUBSCRIBER_PROPERTIES_ONE_WHERE_SUBSCRIBER_ID,
                SubscriberPropertiesEntity.class,
                parameters
        )).orElse(Collections.emptyList());
    }
}
