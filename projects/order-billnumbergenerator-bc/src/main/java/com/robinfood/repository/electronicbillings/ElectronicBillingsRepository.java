package com.robinfood.repository.electronicbillings;

import com.robinfood.constants.QueryConstants;
import com.robinfood.database.DatabaseManager;
import com.robinfood.entities.OrderElectronicBillingsEntity;

import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ElectronicBillingsRepository extends DatabaseManager implements IElectronicBillingsRepository {

    private static ElectronicBillingsRepository instance;

    public static ElectronicBillingsRepository getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ElectronicBillingsRepository();
        }
        return instance;
    }

    @Override
    public BigInteger save(OrderElectronicBillingsEntity orderElectronicBillingsEntity) {

        Map<String, Object> parameters = new LinkedHashMap<>();

        parameters.put("order_id", orderElectronicBillingsEntity.getOrder_id());
        parameters.put("store_id", orderElectronicBillingsEntity.getStore_id());
        parameters.put("store_name", orderElectronicBillingsEntity.getStore_name());
        parameters.put("request_payload", orderElectronicBillingsEntity.getRequest_payload());
        parameters.put("response_payload", orderElectronicBillingsEntity.getResponse_payload());
        parameters.put("status_code", orderElectronicBillingsEntity.getStatus_code());

        return executeInsert(QueryConstants.SAVE_ELECTRONIC_BILLING, parameters);
    }
}
