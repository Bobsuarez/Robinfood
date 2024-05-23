package com.robinfood.configurations.samples;

import com.robinfood.configurations.models.Pos;
import com.robinfood.configurations.models.PosType;
import com.robinfood.configurations.models.Resolution;

import java.time.LocalDateTime;
import java.util.List;

public class PosSample {

    private static final Long TEST_LONG = 1L;

    private static final LocalDateTime CURRENT_DATE = LocalDateTime.MIN;

    private static final String TEST = "TEST";

    public static Pos getPos() {

        return Pos.builder()
                .code(TEST)
                .name(TEST)
                .posType(PosType.builder()
                        .id(TEST_LONG)
                        .build())
                .store(StoreSample.getStore())
                .status(TEST_LONG)
                .build();
    }

    public static Pos getPosWithResolutions() {

        return Pos.builder()
                .code(TEST)
                .name("caja")
                .posType(PosType.builder()
                        .id(TEST_LONG)
                        .build())
                .store(StoreSample.getStore())
                .id(1L)
                .resolutionList(
                        List.of(
                                Resolution.builder()
                                        .endDate(LocalDateTime.parse("2024-01-01T00:00:00"))
                                        .name("Caja 1")
                                        .finalNumber("100")
                                        .id(1L)
                                        .prefix("test fix")
                                        .status(1)
                                        .startDate(LocalDateTime.parse("2024-01-01T00:00:00"))
                                        .build()

                        )

                )
                .status(TEST_LONG)
                .build();
    }
}
