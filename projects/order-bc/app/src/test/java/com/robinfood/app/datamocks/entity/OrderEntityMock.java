package com.robinfood.app.datamocks.entity;

import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.helpers.DateHelper;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class OrderEntityMock {
    public OrderEntity getDataDefault() {
        return new OrderEntity(
                1L,
                1L,
                "MUY",
                1L,
                LocalDateTime.of(2021,10,11,10,10,10),
                "cop",
                1L,
                0.0,
                1,
                1L,
                null,
                null,
                1,
                DateHelper.getLocalDate("2021-05-24"),
                "",
                null,
                1L,
                "Cajas V2",
                true,
                1L,
                "00:00:00",
                1L,
                false,
                1L,
                1L,
                "muy 79",
                8900.0,
                8900.0,
                1L,
                BigDecimal.ZERO,
                8900.0,
                null,
                "50eaf34f-7252-46ef-9a69-2225b06e14be",
                LocalDateTime.of(2021, 10, 11, 10, 10, 10),
                1L,
                1L
        );
    }

    public List<OrderEntity> getDataDefaultList() {
        return Collections.singletonList(getDataDefault());
    }

    public List<OrderEntity> getDataDefaultListTwo() {
        return Collections.singletonList(
             new OrderEntity(
                    1L,
                    1L,
                    "Muy",
                    1L,
                    null,
                    "COP",
                    4L,
                    0.0,
                     2,
                    1L,
                    null,
                    null,
                     1,
                     null,
                     null,
                     null,
                     10L,
                     "Cajas V2",
                     true,
                     1L,
                     "00:00:00",
                     1L,
                     false,
                     2L,
                     1L,
                     "MUY 79",
                     8900.0,
                     0.0,
                     null,
                     BigDecimal.ZERO,
                     8900.0,
                     null,
                     "50eaf34f-7252-46ef-9a69-2225b06e14be",
                     null,
                     1L,
                     1L
             )
        );
    }

    public List<OrderEntity> getDataDefaultListThree() {
        return Collections.singletonList(
                new OrderEntity(
                        1L,
                        1L,
                        "Muy",
                        1L,
                        null,
                        "COP",
                        4L,
                        0.0,
                        2,
                        1L,
                        null,
                        null,
                        1,
                        null,
                        null,
                        null,
                        10L,
                        "Cajas V2",
                        false,
                        1L,
                        "00:00:00",
                        1L,
                        false,
                        1L,
                        1L,
                        "MUY 79",
                        8900.0,
                        0.0,
                        null,
                        BigDecimal.ZERO,
                        8900.0,
                        null,
                        "50eaf34f-7252-46ef-9a69-2225b06e14be",
                        null,
                        1L,
                        1L
                )
        );
    }
}
