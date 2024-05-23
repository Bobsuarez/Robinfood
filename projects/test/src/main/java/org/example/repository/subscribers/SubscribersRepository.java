package org.example.repository.subscribers;

import lombok.SneakyThrows;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.example.constants.QueryConstants;
import org.example.database.DatabaseManager;
import org.example.entities.SubscribersEntity;

public class SubscribersRepository extends DatabaseManager implements ISubscribersRepository {

    private static SubscribersRepository instance;

    public static SubscribersRepository getInstance() {
        if (Objects.isNull(instance)) {
            instance = new SubscribersRepository();
        }
        return instance;
    }

    @Override
    @SneakyThrows
    public SubscribersEntity findById(Long subscriberId) {

        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("id", subscriberId);
        return Optional.ofNullable(executeQuery(
                QueryConstants.SUBSCRIBERS_SELECT_SUBSCRIBERS_ONE_WHERE_ID,
                SubscribersEntity.class,
                parameters
        )).orElse(SubscribersEntity.builder().build());
    }
}
