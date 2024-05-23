package com.robinfood.datamock;

import com.robinfood.entities.FlowEventLogsEntity;

public class FlowEventLogsEntityMock {

    public static FlowEventLogsEntity build() {
        return FlowEventLogsEntity.builder()
                .flow_id(1L)
                .event_id("1db0e433-6697-4050-8b8c-64cbb28227d3")
                .id(1L)
                .payload("{\"id\": \"I01\", \"name\": \"IVA\", \"percentage\": 19}")
                .build();
    }

}
