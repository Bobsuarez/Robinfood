package com.robinfood.configurations.dto.v1;

public class ResponseResolutionsWithPosDTOMock {

    public static ResponseResolutionsWithPosDTO build() {
        return ResponseResolutionsWithPosDTO.builder()
                .id(1L)
                .posId(1L)
                .build();
    }
}
