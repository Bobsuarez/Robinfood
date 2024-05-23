package com.robinfood;

import com.robinfood.constants.QueryConstants;
import com.robinfood.database.DatabaseManager;
import com.robinfood.entities.FlowEventLogsEntity;

import java.util.LinkedHashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) {


        FlowEventLogsEntity flowEventLogsEntity = new FlowEventLogsEntity();
        flowEventLogsEntity.setEvent_id("2131218");
        flowEventLogsEntity.setFlow_id(1L);
        flowEventLogsEntity.setPayload("{\"type\":\"object\"}");

        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("event_id", flowEventLogsEntity.getEvent_id());
        parameters.put("flow_id", flowEventLogsEntity.getFlow_id());
        parameters.put("payload", flowEventLogsEntity.getPayload());

        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.executeInsert(QueryConstants.SAVE_EVENTS, parameters);

    }

}
