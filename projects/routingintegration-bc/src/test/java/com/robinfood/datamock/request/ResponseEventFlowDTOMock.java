package com.robinfood.datamock.request;

import com.robinfood.dtos.geteventflow.response.ResponseEventFlowDTO;

public class ResponseEventFlowDTOMock {

    public static ResponseEventFlowDTO build() {
        return ResponseEventFlowDTO.builder()
                .flowId(1L)
                .eventId("eventId")
                .payload("payload")
                .id(1L)
                .build();
    }
}
