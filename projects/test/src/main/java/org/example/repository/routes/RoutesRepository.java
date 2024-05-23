package org.example.repository.routes;

import lombok.SneakyThrows;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.example.constants.QueryConstants;
import org.example.database.DatabaseManager;
import org.example.entities.RoutesEntity;

public class RoutesRepository extends DatabaseManager implements IRoutesRepository {

    private static RoutesRepository instance;

    public static RoutesRepository getInstance() {
        if (Objects.isNull(instance)) {
            instance = new RoutesRepository();
        }
        return instance;
    }

    @SneakyThrows
    @Override
    public RoutesEntity searchRouteByFlowIdAndChannelId(Long channelId, Long flowId) {

        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("channel_id", channelId);
        parameters.put("flow_id", flowId);
        return Optional.ofNullable(
                executeQuery(QueryConstants.SELECT_CHANNEL_ID_AND_FLOW_ID_RULES,
                        RoutesEntity.class, parameters)
        ).orElse(RoutesEntity.builder().build());
    }
}
