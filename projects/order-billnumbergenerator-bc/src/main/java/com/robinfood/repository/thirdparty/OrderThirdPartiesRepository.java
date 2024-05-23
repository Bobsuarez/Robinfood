package com.robinfood.repository.thirdparty;

import com.robinfood.constants.QueryConstants;
import com.robinfood.database.DatabaseManager;
import com.robinfood.entities.OrderThirdPartiesEntity;

import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class OrderThirdPartiesRepository extends DatabaseManager implements IOrderThirdPartiesRepository {

    private static OrderThirdPartiesRepository instance;

    public static OrderThirdPartiesRepository getInstance() {
        if (Objects.isNull(instance)) {
            instance = new OrderThirdPartiesRepository();
        }
        return instance;
    }

    @Override
    public BigInteger save(OrderThirdPartiesEntity orderThirdPartiesEntity) {

        Map<String, Object> parameters = new LinkedHashMap<>();

        parameters.put("order_id", orderThirdPartiesEntity.getOrder_id());
        parameters.put("full_name", orderThirdPartiesEntity.getFull_name());
        parameters.put("email", orderThirdPartiesEntity.getEmail());
        parameters.put("document_type", orderThirdPartiesEntity.getDocument_type());
        parameters.put("document_number", orderThirdPartiesEntity.getDocument_number());
        parameters.put("phone", orderThirdPartiesEntity.getPhone());

        return executeInsert(QueryConstants.SAVE_THIRD, parameters);
    }
}
