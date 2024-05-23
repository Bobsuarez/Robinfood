package org.example.repository.flows;

import lombok.SneakyThrows;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.example.constants.QueryConstants;
import org.example.database.DatabaseManager;
import org.example.entities.FlowsEntity;

public class FlowsRepository extends DatabaseManager implements IFlowsRepository {

    private static FlowsRepository instance;

    public static FlowsRepository getInstance() {
        if (Objects.isNull(instance)) {
            instance = new FlowsRepository();
        }
        return instance;
    }

    @SneakyThrows
    @Override
    public FlowsEntity searchByFlowCode(String flowCode) {

        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("code", flowCode);
        return Optional.ofNullable(executeQuery(QueryConstants.FLOW_SELECT_CODE_FLOW, FlowsEntity.class, parameters))
                .orElse(FlowsEntity.builder().build());
    }
}
