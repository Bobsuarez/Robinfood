package com.robinfood.repository.flows;

import com.robinfood.constants.QueryConstants;
import com.robinfood.database.DatabaseManager;
import com.robinfood.entities.FlowsEntity;
import lombok.SneakyThrows;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

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
