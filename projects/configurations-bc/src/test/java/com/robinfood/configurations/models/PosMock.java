package com.robinfood.configurations.models;

import java.util.List;

public class PosMock {

    public static Pos build() {

        return Pos.builder()
                .code("ABC")
                .name("pos")
                .resolutionList(List.of(ResolutionMock.build()))
                .status(1L)
                .store(StoreMock.build())
                .build();
    }
}
