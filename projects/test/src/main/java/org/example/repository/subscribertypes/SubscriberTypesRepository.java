package org.example.repository.subscribertypes;

import lombok.SneakyThrows;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.example.constants.QueryConstants;
import org.example.database.DatabaseManager;
import org.example.entities.SubscriberTypesEntity;

public class SubscriberTypesRepository extends DatabaseManager implements ISubscriberTypesRepository {

    private static SubscriberTypesRepository instance;

    public static SubscriberTypesRepository getInstance() {
        if (Objects.isNull(instance)) {
            instance = new SubscriberTypesRepository();
        }
        return instance;
    }

    @Override
    @SneakyThrows
    public SubscriberTypesEntity findById(Long subscriberId) {

        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("id", subscriberId);
        return Optional.ofNullable(executeQuery(
                QueryConstants.SUBSCRIBERS_SELECT_SUBSCRIBER_TYPE_ONE_WHERE_ID,
                SubscriberTypesEntity.class,
                parameters
        )).orElse(SubscriberTypesEntity.builder().build());
    }
}
